package com.chen.common.entity;

import lombok.Data;

@Data
public class RequestEntity {

    /**
     * 客户端ip
     */
    private String ip;

    /**
     * 客户端请求url
     */
    private String url;

    /**
     * http请求方法
     */
    private String httpMethod;

    /**
     * 请求方法
     */
    private String classMethod;

    /**
     * 请求参数
     */
    private Object requestParams;

    /**
     * 返回参数
     */
    private Object result;

    /**
     * 请求执行耗时
     */
    private Long timeCost;
}
