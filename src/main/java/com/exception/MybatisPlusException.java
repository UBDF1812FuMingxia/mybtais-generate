package com.exception;

/**
 * @ClassName : MybatisPlusException
 * @Description : Mybatis使用时的异常类
 * @Author : fmx
 * @Date: 2021-05-06 14:48
 */
public class MybatisPlusException extends RuntimeException {
    public MybatisPlusException(String msg){
        super(msg);
    }
}
