package com.chxlay.common.result;

import lombok.Getter;

/**
 * @author Alay
 * @date 2020-12-22 12:59
 * @project netty
 */
@Getter
public enum ResultEnum {
    /**
     * 操作不允许
     */
    NOT_ALLOWED(false, "ERR_1001", "操作不允许");


    private boolean success;
    private String code;
    private String msg;

    ResultEnum(boolean success, String code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }
}