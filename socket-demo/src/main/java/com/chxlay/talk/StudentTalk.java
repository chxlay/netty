package com.chxlay.talk;

import com.chxlay.common.exception.MyException;
import com.chxlay.common.util.DataUtil;
import com.chxlay.constants.SocketConstants;
import com.chxlay.container.EntityData;
import com.chxlay.container.UserChannelRel;
import com.chxlay.entity.CallBackTalk;
import com.chxlay.entity.CallTalk;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 学生端消息会话拦截处理:责任链模式 HeadTalkHandler ->TeacherTalk ->StudentTalk->TailTalkHandler)
 *
 * @author Alay
 * @date 2020-12-09 17:26
 * @project Braineex
 */
@Component
public class StudentTalk extends AbsTalkHandler {

	@Autowired
	private TailTalkHandler next;

	@Override
	public AbsTalkHandler next() {
		return next;
	}

	@Override
	public void online(ChannelHandlerContext ctx, String userId) throws MyException {
		// 给学生创建关注老师的容器
		UserChannelRel<String, ChannelId> studentChannelRel = new UserChannelRel<>(ctx.channel().id(), SocketConstants.STUDENT_SIZE);
		studentChannelMap.put(userId, studentChannelRel);
		super.online(ctx, userId);
	}

	@Override
	public void offline(ChannelHandlerContext ctx) throws MyException {
		// 学生下线了,移除其关注的老师之间的关系
		EntityData<String, String> userRole = channelUserMap.get(ctx.channel().id());
		// 角色是学生的才进行学生的处理逻辑
		if (SocketConstants.ROLE_STUDENT.equals(userRole.getValue())) {
			String studentUid = userRole.getKey();
			UserChannelRel<String, ChannelId> studentChannelRel = studentChannelMap.get(studentUid);
			// 学生上线的时候回给其分配一个空的容器,存储其关注的老师的关系,不需要判断 null,只要判断空就行
			if (!studentChannelRel.isEmpty()) {
				// 遍历学生关注的没一个老师,然后移除双方的关联关系
				Iterator<Map.Entry<String, ChannelId>> iterator = studentChannelRel.iterator();
				while (iterator.hasNext()) {
					Map.Entry<String, ChannelId> entry = iterator.next();
					// 逐一去除老师的被关注的该学生的数据
					String teacherUid = entry.getKey();
					UserChannelRel<String, ChannelId> teacherChannelRel = teacherChannelMap.get(teacherUid);
					teacherChannelRel.removeChannel(studentUid);
				}
			}
			// 移除学生的所有通道数据
			studentChannelMap.remove(studentUid);
		}
		this.next().offline(ctx);
	}

	/**
	 * 学生端处理发送来的消息的
	 *
	 * @param ctx
	 * @param callTalk
	 */
	@Override
	public void messageReceived(ChannelHandlerContext ctx, CallTalk callTalk) {
		Set<String> teachersUid = callTalk.getFollowTeachers();
		// 增加学生关注的老师,加入关注的老师
		if (DataUtil.collEffective(teachersUid)) {
			String studentUid = callTalk.getStudentUid();
			// 学生端的关联老师的信息
			UserChannelRel<String, ChannelId> studentChannelRel = studentChannelMap.get(studentUid);
			// 学生需要关注的老师逐一加入关注的队列中,不需要 null 判断,因为学生上线的时候就给其分配了一个空的容器
			for (String teacherUid : teachersUid) {
				// 取出老师的关注关系数据
				UserChannelRel<String, ChannelId> teacherChannelRel = teacherChannelMap.get(teacherUid);
				if (null == teacherChannelRel) {
					// 老师不在线且第一次被学生关注,暂时还没有老师的 连接信息,用 null 占位
					teacherChannelRel = new UserChannelRel<>(null, SocketConstants.TEACHER_SIZE);
					teacherChannelMap.put(teacherUid, teacherChannelRel);
				}
				// 老师关注数据中加入被关注人员学生的信息
				teacherChannelRel.addChannel(studentUid, ctx.channel().id());

				// 将老师的连接信息 添加到学生的关注教师列表中,若老师没有上线,此处为 null 占位
				studentChannelRel.addChannel(teacherUid, teacherChannelRel.getCareChannel(teacherUid));
			}
		}
		this.next.messageReceived(ctx, callTalk);
	}


	@Override
	public void pushNotice(ChannelHandlerContext ctx, String userId, CallBackTalk callBackTalk) throws MyException {
		UserChannelRel<String, ChannelId> studentChannelRel = studentChannelMap.get(userId);
		super.pushNotice(studentChannelRel, callBackTalk);
		super.pushNotice(null, userId, callBackTalk);
	}
}