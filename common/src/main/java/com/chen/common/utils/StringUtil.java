package com.chen.common.utils;

public class StringUtil {

    /**
     * 手机号脱敏
     */
    String   phoneNumber = "1523716".replaceAll("(\\w{3})\\w*(\\w{4})", "$1****$2");
}
