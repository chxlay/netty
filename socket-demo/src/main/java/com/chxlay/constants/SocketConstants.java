package com.chxlay.constants;

/**
 * @author Alay
 * @date 2020-12-10 12:16
 * @project Braineex
 */
public interface SocketConstants {

	/**
	 * 教师连接通道
	 */
	String TEACHER_CHANNEL = "/socket/teacher";
	/**
	 * 学生连接通道
	 */
	String STUDENT_CHANNEL = "/socket/student";

	String ROLE_TEACHER = "T";
	String ROLE_STUDENT = "S";
	/**
	 * 老师允许被关注的数量(为了不让Map扩容,根据Map的扩容机制反推出)，实际大小 95
	 */
	int TEACHER_SIZE = (int) ((1 << 7) * 0.75f) - 1;
	/**
	 * 学生允许关注的数量(为了不让Map扩容,根据Map的扩容机制反推出),实际大小 47
	 */
	int STUDENT_SIZE = (int) ((1 << 6) * 0.75f) - 1;

	String MSG_TEACHER_OFFLINE = "老师已经下线";

	String MSG_STUDENT_OFFLINE = "学生已经下线";

	/**
	 * 云课堂开始动作
	 */
	String ACTION_START = "START";
	/**
	 * 云课堂结束动作
	 */
	String ACTION_FINISH = "FINISH";
	/**
	 * 云课堂中途暂停动作
	 */
	String ACTION_STOP = "STOP";

	/**
	 * 云课堂切断
	 */
	String ACTION_CLOSE = "CLOSE_CONNECTION";

}