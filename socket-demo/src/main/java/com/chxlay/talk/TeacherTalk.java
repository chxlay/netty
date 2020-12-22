package com.chxlay.talk;

import com.chxlay.common.exception.MyException;
import com.chxlay.common.result.R;
import com.chxlay.constants.SecurityConstants;
import com.chxlay.constants.SocketConstants;
import com.chxlay.container.EntityData;
import com.chxlay.container.UserChannelRel;
import com.chxlay.entity.CallBackTalk;
import com.chxlay.entity.CallTalk;
import com.chxlay.entity.SimpleInfo;
import com.chxlay.entity.TeacherEsInfo;
import com.chxlay.feign.RemoteSearchService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * 老师端消息会话拦截处理:责任链模式 HeadTalkHandler ->TeacherTalk ->StudentTalk->TailTalkHandler)
 *
 * @author Alay
 * @date 2020-12-09 17:25
 * @project Braineex
 */
@Component("teacherTalk")
public class TeacherTalk extends AbsTalkHandler {

    @Autowired
    private StudentTalk next;
    @Autowired
    private RemoteSearchService searchService;


    @Override
    public AbsTalkHandler next() {
        return next;
    }

    @Override
    public void online(ChannelHandlerContext ctx, String userId) {
        SimpleInfo simpleInfo = personService.simpleInfos(Arrays.asList(userId), SecurityConstants.FROM_IN).getData().get(0);
        Boolean isTeacher = simpleInfo.getIsAuthTeacher();
        if (isTeacher) {
            UserChannelRel<String, ChannelId> channelRel = teacherChannelMap.get(userId);
            if (null == channelRel) {
                // 看老师还没有人关注
                channelRel = new UserChannelRel<>(ctx.channel().id(), SocketConstants.TEACHER_SIZE);
                teacherChannelMap.put(userId, channelRel);
            } else {
                // 该老师有关注者,构建返回给客学生的数据
                CallBackTalk callBackTalk = CallBackTalk.builder().teacherUid(userId).isOnline(true).isFree(true).isCalled(false);

                // 将关注着我的学生关联关系中的 我的 连接信息进行更新,并通知所有关注我的学生
                Iterator<Map.Entry<String, ChannelId>> iterator = channelRel.iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, ChannelId> entry = iterator.next();
                    String studentUid = entry.getKey();
                    // 关注着我的学生,将其关联我的 通道Id 更新为最新的
                    UserChannelRel<String, ChannelId> studentRel = studentChannelMap.get(studentUid);
                    studentRel.addChannel(userId, ctx.channel().id());

                    // 发送通知高数学生,老师上线了
                    Channel channel = channelGroup.find(entry.getValue());
                    // 每一个关注老师的学生逐一通知
                    channel.writeAndFlush(callBackTalk);
                }
                // 更新老师自己的信息中的连接通道信息
                channelRel.setChannelId(ctx.channel().id());
            }
            // 修改ES 中老师的信息
            TeacherEsInfo esInfo = new TeacherEsInfo().userId(userId).isOnline(true).isFree(true);
            searchService.updateTeacher(esInfo, SecurityConstants.FROM_IN);
        }
    }

    @Override
    public void offline(ChannelHandlerContext ctx) throws MyException {
        // 获得下线的用户 userId
        EntityData<String, String> userRole = channelUserMap.get(ctx.channel().id());
        if (SocketConstants.ROLE_TEACHER.equals(userRole.getValue())) {
            String userId = userRole.getKey();
            // 从老师组里找出该老师的通道信息
            UserChannelRel<String, ChannelId> channelRel = teacherChannelMap.get(userId);
            if (null != channelRel) {
                if (channelRel.isEmpty()) {
                    // 没有人关注着该老师,老师下线则可以清除相关的数据了
                    channelUserMap.remove(userId);
                    teacherChannelMap.remove(userId);
                } else {
                    // 有学生关注着老师
                    CallBackTalk callBackTalk = CallBackTalk.builder().teacherUid(userId).isOnline(false);
                    // 该老师有用户关注着,通知关注者,老师下线了
                    Iterator<Map.Entry<String, ChannelId>> iterator = channelRel.iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<String, ChannelId> entry = iterator.next();
                        // 取出每个关注者的连接
                        Channel channel = channelGroup.find(entry.getValue());
                        // 每一个关注老师的学生逐一通知
                        channel.writeAndFlush(callBackTalk);
                    }
                }
            }
            // 修改ES 中老师的信息
            TeacherEsInfo esInfo = new TeacherEsInfo().userId(userId).isOnline(false);
            searchService.updateTeacher(esInfo, SecurityConstants.FROM_IN);
        }
        this.next().offline(ctx);
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, CallTalk callTalk) {
        EntityData<String, String> userRole = channelUserMap.get(ctx.channel().id());
        String userId = userRole.getKey();
        if (SocketConstants.ROLE_TEACHER.equals(userRole.getValue())) {
            // 回推老师的信息对象
            CallBackTalk callBackTalk = CallBackTalk.builder().answerId(callTalk.getAnswerId()).state(callTalk.getState());
            // 教师通道登录的用户
            if (null != callTalk.getIsFree()) {
                // 教师主动修改了忙碌状态
                // 修改ES 中老师的信息
                TeacherEsInfo esInfo = new TeacherEsInfo().userId(userId).isFree(callTalk.getIsFree());
                searchService.updateTeacher(esInfo, SecurityConstants.FROM_IN);
            }
            if (null != callTalk.getState() && !"PAID".equals(callTalk.getState())) {
                // 返回信息对象

                // 老师更改了审题状态,通知学生
                String studentUid = callTalk.getStudentUid();
                UserChannelRel<String, ChannelId> studentChannelRel = studentChannelMap.get(studentUid);
                if (null == studentChannelRel) {
                    // 学生下线、掉线,将信息返回给老师端
                    callBackTalk.msg(SocketConstants.MSG_STUDENT_OFFLINE);
                } else {
                    // 回馈学生信息
                    Channel studentChannel = channelGroup.find(studentChannelRel.channelId());
                    studentChannel.writeAndFlush(callBackTalk);
                }
                // 远程调用修改答疑题目的变动

                R<Boolean> booleanR = releaseService.changeAnswer(null, SecurityConstants.FROM_IN);
                // 返回信息推送回老师端
                callBackTalk.data(booleanR.getData()).msg(booleanR.getMsg()).code(booleanR.getCode());
                ctx.channel().writeAndFlush(callBackTalk);
            }
        }
        this.next.messageReceived(ctx, callTalk);
    }

    @Override
    public void pushNotice(ChannelHandlerContext ctx, String userId, CallBackTalk callBackTalk) throws MyException {
        UserChannelRel<String, ChannelId> teacherChannelRel = teacherChannelMap.get(userId);
        super.pushNotice(teacherChannelRel, callBackTalk);
        super.pushNotice(null, userId, callBackTalk);
    }


}