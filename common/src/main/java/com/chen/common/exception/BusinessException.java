package com.chen.common.exception;


/**
 * 自定义异常：业务异常
 */
public class BusinessException extends RuntimeException{

    public BusinessException(String message) {
        super(message);
    }
}
