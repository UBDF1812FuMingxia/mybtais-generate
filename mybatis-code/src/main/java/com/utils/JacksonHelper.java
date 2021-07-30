package com.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName : JacksonHelper
 * @Description : 任何数据转换成json格式的工具类
 * @Author : fmx
 * @Date: 2021-07-29 14:07
 */
public class JacksonHelper {
    private static ObjectMapper toJSONMapper = new ObjectMapper();

    private static ObjectMapper fromJSOMMapper = new ObjectMapper();

    static{
        fromJSOMMapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false
        );

        fromJSOMMapper.configure(
                JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        fromJSOMMapper.setDateFormat(
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        );

        toJSONMapper.setDateFormat(
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        );
    }

    public static String toJSON(Object obj) {
        try {
            return toJSONMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toPrettyJSON(Object obj) {
        try {
            return toJSONMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJSON(String json, Class<T> clazz) {
        try {
            return fromJSOMMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class TestTime {

    private Date time;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
