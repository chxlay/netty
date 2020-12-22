package com.chxlay.entity;

import lombok.Data;

/**
 * @author Alay
 * @date 2020-12-22 13:16
 * @project netty
 */
@Data
public class Answer {
    private String id;
    private String userId;
    private String teacherUid;
    private String state;
}