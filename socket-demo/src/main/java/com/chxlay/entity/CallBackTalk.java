package com.chxlay.entity;

import lombok.Getter;

/**
 * 师生对话传输内容响应参数对象
 *
 * @author Alay
 * @date 2020-12-10 15:51
 * @project Braineex
 */
@Getter
public class CallBackTalk<T> {
	/**
	 * 老师UserId
	 */
	private String teacherUid;
	/**
	 * 答疑题目ID
	 */
	private String answerId;
	/**
	 * 在线状态
	 */
	private Boolean isOnline;
	/**
	 * 老师是否空闲
	 */
	private Boolean isFree;
	/**
	 * 老师是否空闲
	 */
	private Boolean isCalled;
	/**
	 * 答疑状态：等待中 WAIT、备课中 PREPARE、备课结束 READY、已付款PAID、欠款ARREARS
	 */
	private String state;

	private String code;

	private String msg;

	private T data;

	/**
	 * 返回动作指令
	 */
	private String action;

	private CallBackTalk() {
	}

	public static CallBackTalk builder() {
		return new CallBackTalk();
	}

	public CallBackTalk answerId(String answerId) {
		this.answerId = answerId;
		return this;
	}

	public CallBackTalk teacherUid(String userId) {
		this.teacherUid = userId;
		return this;
	}

	public CallBackTalk isCalled(Boolean isCalled) {
		this.isCalled = isCalled;
		return this;
	}

	public CallBackTalk isFree(Boolean isFree) {
		this.isFree = isFree;
		return this;
	}

	public CallBackTalk isOnline(Boolean isOnline) {
		this.isOnline = isOnline;
		return this;
	}

	public CallBackTalk state(String state) {
		this.state = state;
		return this;
	}

	public CallBackTalk data(T data) {
		this.data = data;
		return this;
	}

	public CallBackTalk code(String code) {
		this.code = code;
		return this;
	}

	public CallBackTalk msg(String msg) {
		this.msg = msg;
		return this;
	}

	public CallBackTalk action(String action) {
		this.action = action;
		return this;
	}


}