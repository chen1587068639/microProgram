package com.chen.common.entity;

import lombok.Data;

@Data
public class RequestErrorEntity {
    private String ip;
    private String url;
    private String httpMethod;
    private String classMethod;
    private Object requestParams;
    private RuntimeException exception;
}
