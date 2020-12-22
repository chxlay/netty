package com.chxlay.talk;

import com.chxlay.common.constants.CommonConstants;
import com.chxlay.common.exception.MyException;
import com.chxlay.common.result.R;
import com.chxlay.constants.SecurityConstants;
import com.chxlay.constants.SocketConstants;
import com.chxlay.container.EntityData;
import com.chxlay.container.UserChannelRel;
import com.chxlay.entity.Answer;
import com.chxlay.entity.CallBackTalk;
import com.chxlay.entity.CallTalk;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 消息会话拦截处理:责任链模式 HeadTalkHandler ->TeacherTalk ->StudentTalk->TailTalkHandler)
 *
 * @author Alay
 * @date 2020-12-10 12:01
 * @project Braineex
 */
@Component
public class HeadTalkHandler extends AbsTalkHandler {
    @Autowired
    private AbsTalkHandler teacherTalk;
    @Autowired
    private AbsTalkHandler studentTalk;

    @Override
    public AbsTalkHandler next() {
        return teacherTalk;
    }

    @Override
    public void onlineAdapter(ChannelHandlerContext ctx, String userId, String uri) throws MyException {
        // 上线的用户添加入连接通道组中
        channelGroup.add(ctx.channel());
        // 给用户建立 channelId 与 userId 的关系,并分配当前的角色
        if (uri.startsWith(SocketConstants.TEACHER_CHANNEL)) {
            // 建立 channelId 与 userId 的关系,并分配当前的角色
            EntityData<String, String> userRole = new EntityData<String, String>().key(userId).value(SocketConstants.ROLE_TEACHER);
            channelUserMap.put(ctx.channel().id(), userRole);
            teacherTalk.online(ctx, userId);
        } else if (uri.startsWith(SocketConstants.STUDENT_CHANNEL)) {
            // 建立 channelId 与 userId 的关系,并分配当前的角色
            EntityData<String, String> userRole = new EntityData<String, String>().key(userId).value(SocketConstants.ROLE_STUDENT);
            channelUserMap.put(ctx.channel().id(), userRole);
            studentTalk.online(ctx, userId);
        } else {
            throw EXCEPTION;
        }
    }

    @Override
    public void offline(ChannelHandlerContext ctx) throws MyException {
        EntityData<String, String> userRole = channelUserMap.get(ctx.channel().id());
        String answerId = classRoom.get(userRole.getKey());
        if (null != answerId) {
            // 正在上课的人员掉线
            Answer answer = releaseService.getAnswer(answerId, SecurityConstants.FROM_IN).getData();
            // 用户掉线直接进行结算答疑
            CallTalk callTalk = new CallTalk().answerId(answer.getId()).action(SocketConstants.ACTION_FINISH);
            CallBackTalk callBackTalk = this.doTeach(answer, callTalk);
            // 获取掉线人员通道发送信息给另一方
            UserChannelRel<String, ChannelId> studentChannelRel = studentChannelMap.get(answer.getUserId());
            if (ctx.channel().id() == studentChannelRel.channelId()) {
                // 掉线的人员是学生,发送通知给老师
                UserChannelRel<String, ChannelId> teacherChannelRel = teacherChannelMap.get(answer.getTeacherUid());
                ChannelId channelId = teacherChannelRel.channelId();
                Channel channel = channelGroup.find(channelId);
                channel.writeAndFlush(callBackTalk.msg("学生下线了"));
            } else {
                // 掉线的是老师
                ChannelId channelId = studentChannelRel.channelId();
                Channel channel = channelGroup.find(channelId);
                channel.writeAndFlush(callBackTalk.msg("老师下线了"));
            }
        }
        this.next().offline(ctx);
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, CallTalk callTalk) {
        // 如果参数中含有云课堂的断开与连接的动作,需要相应的做处理
        if (null != callTalk.getAction()) {
            // 执行教学
            CallBackTalk callBackTalk = this.doTeach(null, callTalk);
            ctx.channel().write(callBackTalk);
        }
        this.next().messageReceived(ctx, callTalk);
    }

    /**
     * 任教
     *
     * @param answer
     * @param callTalk
     * @return
     */
    private CallBackTalk doTeach(Answer answer, CallTalk callTalk) {
        CallBackTalk callBack = null;
        if (null == answer) {
            answer = releaseService.getAnswer(callTalk.getAnswerId(), SecurityConstants.FROM_IN).getData();
        }
        if (SocketConstants.ACTION_START.equals(callTalk.getAction())) {
            // 开始上课,将上课人员(老师或者学生加入上课教室人员行列中)
            classRoom.put(answer.getUserId(), answer.getId());
            classRoom.put(answer.getTeacherUid(), answer.getId());
        }
        // 如果答疑当前状态与准备修改的状态一直,则无需处理,重复的更新动作(老师和学生共用一份Answer)
        if (Objects.equals(answer.getState(), callTalk.getAction())) {
            return null;
        }
        // 执行修改
        R<Boolean> booleanR = releaseService.changeConnect(callTalk, SecurityConstants.FROM_IN);
        if (SocketConstants.ACTION_FINISH.equals(callTalk.getAction()) && CommonConstants.CODE_OK.equals((booleanR.getCode()))) {
            // 教室人员行列移除用户
            classRoom.remove(answer.getUserId());
            classRoom.remove(answer.getTeacherUid());
            // 结束授课成功,开始结算
            R<Boolean> settle = releaseService.settle(callTalk.getAnswerId(), SecurityConstants.FROM_IN);
            callBack = CallBackTalk.builder().msg(settle.getMsg()).code(settle.getCode());
        }
        return callBack;
    }

}