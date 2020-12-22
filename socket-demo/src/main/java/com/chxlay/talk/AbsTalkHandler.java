package com.chxlay.talk;

import com.chxlay.common.exception.MyException;
import com.chxlay.common.result.ResultEnum;
import com.chxlay.container.EntityData;
import com.chxlay.container.UserChannelRel;
import com.chxlay.entity.CallBackTalk;
import com.chxlay.entity.CallTalk;
import com.chxlay.feign.RemotePersonService;
import com.chxlay.feign.RemoteReleaseService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息会话拦截处理:责任链模式 HeadTalkHandler ->TeacherTalk ->StudentTalk->TailTalkHandler)
 *
 * @author Alay
 * @date 2020-12-09 17:21
 * @project Braineex
 */
public abstract class AbsTalkHandler {
	@Autowired
	protected RemotePersonService personService;
	@Autowired
	protected RemoteReleaseService releaseService;
	/**
	 * 创建一个通道组(不考虑多段登录,故只拿回只用Group处理用户通道)
	 */
	protected volatile static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	/**
	 * 教师通道管理 userId <-> {userId,followChannel}
	 */
	protected volatile static ConcurrentHashMap<String, UserChannelRel<String, ChannelId>> teacherChannelMap = new ConcurrentHashMap<>(1 << 5);
	/**
	 * 学生通道管理 userId <-> {userId,followChannel}
	 */
	protected volatile static ConcurrentHashMap<String, UserChannelRel<String, ChannelId>> studentChannelMap = new ConcurrentHashMap<>(1 << 5);
	/**
	 * 关联用户连接管道和 userId 的关系：Map< channelId , Map<userId , role> >
	 */
	protected volatile static ConcurrentHashMap<ChannelId, EntityData<String, String>> channelUserMap = new ConcurrentHashMap<>(1 << 5);
	/**
	 * 云教室进入教室的用户
	 */
	protected volatile static ConcurrentHashMap<String, String> classRoom = new ConcurrentHashMap<>(1 << 5);
	/**
	 * 父类异常(不允许子类调用父类方法,但不强制子类重写,采用异常抛出)
	 */
	protected final MyException EXCEPTION = new MyException(ResultEnum.NOT_ALLOWED);

	/**
	 * 分配用户连接渠道
	 *
	 * @param ctx
	 * @param userId
	 * @param uri
	 * @throws MyException
	 */
	public void onlineAdapter(ChannelHandlerContext ctx, String userId, String uri) throws MyException {
		throw EXCEPTION;
	}

	/**
	 * 执行下一个 chain
	 *
	 * @return
	 */
	public AbsTalkHandler next() {
		return null;
	}

	/**
	 * 上线通知
	 *
	 * @param ctx
	 * @param userId
	 */
	public void online(ChannelHandlerContext ctx, String userId) throws MyException {
		return;
	}

	/**
	 * 离线
	 *
	 * @param ctx
	 */
	public void offline(ChannelHandlerContext ctx) throws MyException {
		if (this.next() != null) {
			this.next().offline(ctx);
		}
	}

	/**
	 * 接收处理Socket消息的并处理
	 *
	 * @param ctx
	 * @param callTalk
	 */
	public void messageReceived(ChannelHandlerContext ctx, CallTalk callTalk) {
		ctx.flush();
		return;
	}

	/**
	 * 推送消息的处理方法
	 *
	 * @param ctx
	 * @param userId
	 * @throws MyException
	 */
	public void pushNotice(ChannelHandlerContext ctx, String userId, CallBackTalk callBackTalk) throws MyException {
		if (null != ctx) {
			ctx.channel().writeAndFlush(callBackTalk);
		} else if (this.next() != null) {
			this.next().pushNotice(null, userId, callBackTalk);
		}
	}

	public void pushNotice(UserChannelRel<String, ChannelId> channelRel, CallBackTalk callBackTalk) throws MyException {
		if (null != channelRel) {
			// 当前用户角色是当前调用者的
			ChannelId channelId = channelRel.channelId();
			Channel channel = channelGroup.find(channelId);
			channel.writeAndFlush(callBackTalk);
		}
	}

}