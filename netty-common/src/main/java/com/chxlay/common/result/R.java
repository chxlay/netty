package com.chxlay.common.result;

import lombok.Getter;

import java.io.Serializable;

/**
 * 返回值
 *
 * @author Alay
 * @date 2020-12-22 12:48
 * @project netty
 */
@Getter
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;


    private String code;
    private boolean success;
    private String msg;
    private T data;

    public R code(String code) {
        this.code = code;
        return this;
    }

    public R success(boolean success) {
        this.success = success;
        return this;
    }

    public R msg(String msg) {
        this.msg = msg;
        return this;
    }

    public R data(T data) {
        this.data = data;
        return this;
    }


    public T getData() {
        return this.data;
    }
}