package com.utils;

import java.util.Base64;

/**
 * @ClassName : Base64Utils
 * @Description : base64的加解密工具类
 * @Author : fmx
 * @Date: 2021-07-30 09:43
 */
public class Base64Utils {

    //解码
    public static String decode(String decodeStr) {
        byte[] decodeByte = null;
        String deRes = "";
        decodeByte = Base64.getDecoder()
                .decode(decodeStr.getBytes());
        deRes = new String(decodeByte);
        return deRes;
    }

    //编码
    public static String encode(String encodeStr) {
        String enRes = "";
        byte[] encodeByte = null;
        encodeByte = Base64.getEncoder().encode(encodeStr.getBytes());
        enRes = new String(encodeByte);
        return enRes;
    }
}
