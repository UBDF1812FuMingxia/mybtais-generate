package com.utils;
import com.exception.MybatisPlusException;
import org.springframework.util.StringUtils;

import java.util.Scanner;

/**
 * @ClassName : CodeGenerate
 * @Description : 测试mybatis-plus生成代码的格式
 * @Author : fmx
 * @Date: 2021-05-06 14:36
 */
public class CodeGenerate {
    /**
     * 读取控制台的内容
     */
    public static String scanner(String tip){
        Scanner scanner = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("请输入" + tip + ":");
        System.out.println(stringBuilder.toString());
        if(scanner.hasNext()){
            String ipt = scanner.next();
            if(!StringUtils.isEmpty(ipt)){
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "!");
    }

    public static void main(String[] args){
        //代码生成器
        //AutoGenerator mpg = new AutoGenerator();
    }
}
