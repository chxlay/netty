package com.chxlay.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * 师生对话传输内容请求参数对象
 *
 * @author Alay
 * @date 2020-12-10 10:08
 * @project Braineex
 */
@Data
public class CallTalk {
    /**
     * 学生的userId
     */
    private String studentUid;
    /**
     * 学生的userId
     */
    private Set<String> followTeachers;
    /**
     * 老师的UserId
     */
    private String teacherUid;
    /**
     * 空闲状态
     */
    private Boolean isFree = true;
    /**
     * 在线状态
     */
    private Boolean isOnline = true;

    /**
     * 答疑状态：等待中 WAIT、备课中 PREPARE、备课结束 READY、已付款PAID、欠款ARREARS
     */
    private String state;

    /**
     * 一对一对接的答疑题目的Id
     */
    private String answerId;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 答疑开始计时时间戳
     */
    private String action;

    public CallTalk answerId(String answerId) {
        this.answerId = answerId;
        return this;
    }

    public CallTalk action(String action) {
        this.action = action;
        return this;
    }
}