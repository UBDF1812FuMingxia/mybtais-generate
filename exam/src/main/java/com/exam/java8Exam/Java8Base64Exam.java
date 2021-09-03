package com.exam.java8Exam;

import org.jsoup.Connection;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

/**
 * @ClassName : Java8Base64Exam
 * @Description : Java 8 新特性Base64类的使用测试
 * @Author : fmx
 * @Date: 2021-08-23 10:08
 */
public class Java8Base64Exam {

    public static void main(String[] args) {
        try {
            //使用基本编码
            String base64encodeString = Base64.getEncoder().encodeToString("runoob?java8".getBytes("utf-8"));
            System.out.println("Base64编码字符串（基本）：" + base64encodeString);

            //解码
            byte[] base64decodeString = Base64.getDecoder().decode(base64encodeString);
            System.out.println("原始字符串：" + new String(base64decodeString, "utf-8"));

            base64encodeString = Base64.getUrlEncoder().encodeToString("TutorialsPoint>java8".getBytes("utf-8"));
            System.out.println("Base64 编码字符串（URL）：" + base64encodeString);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                stringBuilder.append(UUID.randomUUID().toString());
            }
            byte[] mimeBytes = stringBuilder.toString().getBytes("utf-8");
            String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
            System.out.println("Base64 编码字符串（MIME）：" + mimeEncodedString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
