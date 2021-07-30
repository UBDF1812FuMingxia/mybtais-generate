package com.utils;

import org.apache.http.client.utils.DateUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

/**
 * @ClassName : EncryptInfo
 * @Description : 加解密工具类
 * @Author : fmx
 * @Date: 2021-07-30 09:39
 */
public class EncryptInfo {

    /**
     * 解密reqBizInfo
     * @param keyValue
     * @param reqBizInfo
     * @return
     */
    public static String decodeReqBizInfo(String keyValue,
                                          String reqBizInfo) {
        String baseDecodeReq = "";
        String aesDecodeReq = "";
        String urlDecodeReq = "";
        try {
            //base64解码
            baseDecodeReq = Base64Utils.decode(reqBizInfo);
            //根据密钥进行AES解密
            aesDecodeReq = AESUtils.decrypt(keyValue, baseDecodeReq);
            //对请求报文进行URLDecode解码
            urlDecodeReq = URLDecoder.decode(aesDecodeReq, "utf-8");
            //返回解密后的reqBizInfo报文
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urlDecodeReq;
    }

    /**
     * 加密result的方法
     * @param keyValue
     * @param result
     * @return
     */
    public static String encodeResult(String keyValue, String result) {
        String urlEncodeRes = "";
        String aesEncodeRes = "";
        String encodeRes = "";
        try {
            //对加密后的应答报文进行encode编码
            urlEncodeRes = URLEncoder.encode(result, "utf-8");
            //对传入的应答报文进行AES加密
            aesEncodeRes = AESUtils.encrypt(keyValue, urlEncodeRes);
            //base64进行编码
            encodeRes = Base64Utils.encode(aesEncodeRes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //返回应答报文
        return encodeRes;
    }

    /*public static void main(String[] args) {
        Date date = new Date();
        System.out.println(DateUtils.formatDate(date.getTime()));
    }*/
}
