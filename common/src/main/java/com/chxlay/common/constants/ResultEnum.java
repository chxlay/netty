package com.chxlay.common.constants;

import lombok.Getter;

/**
 * 返回值枚举
 *
 * @author Alay
 * @date 2020-12-02 11:19
 * @project netty
 */
@Getter
public enum ResultEnum {

    /**
     * Token错误
     */
    ERROR_TOKEN(false, 401, "Token失效");

    private Boolean success;

    private Integer code;

    private String message;

    ResultEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}