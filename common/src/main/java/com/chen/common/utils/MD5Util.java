package com.chen.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class MD5Util {
    // 全局数组
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f","g","h","j","?","~" };

    /**
     * MessageDigest 类为应用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法
     */
    public static String getMD5(String string){
        byte[] bytes = string.getBytes();
        try {
            //使用MD5加密
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(bytes);
            return byteToString(digest);
        } catch (NoSuchAlgorithmException e) {
            log.info("MD5加密失败:",e);
        }
        return null;
    }

    private static String byteToString(byte[] bytes){
        int len = bytes.length;
        StringBuilder result = new StringBuilder();
        for (byte byte0 : bytes) {
            result.append(strDigits[byte0 >>> 4 & 0xf]);
            result.append(strDigits[byte0 & 0xf]);
        }
        return result.toString();
    }








    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    // 转换字节数组为16进制字串
    private static String byteToString1(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    public static String getMD5Code(String strObj) {
        String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString1(md.digest(strObj.getBytes("UTF-8")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultString;
    }
}
