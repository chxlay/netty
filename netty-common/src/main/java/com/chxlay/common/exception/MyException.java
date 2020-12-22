package com.chxlay.common.exception;

import com.chxlay.common.result.ResultEnum;

/**
 * @author Alay
 * @date 2020-12-22 13:06
 * @project netty
 */
public class MyException extends Exception {

    private String code = "OK_0001";

    /**
     * 接受状态码和消息
     *
     * @param code
     * @param message
     */
    public MyException(String code, String message){
        super(message);
        this.code = code;
    }

    /**
     * 接收枚举类型
     *
     * @param resultCodeEnum
     */
    public MyException(ResultEnum resultCodeEnum) {
        super(resultCodeEnum.getMsg());
        this.code = resultCodeEnum.getCode();
    }
}