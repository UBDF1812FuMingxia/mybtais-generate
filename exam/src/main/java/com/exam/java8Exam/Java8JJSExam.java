package com.exam.java8Exam;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.math.BigDecimal;

/**
 * @ClassName : Java8JJSExam
 * @Description : Java 8 新特性 JJS 测试
 *                  jjs是个基于Nashorn引擎的命令行工具。他接受一些JavaScript源代码为参数，并且执行。
 * @Author : fmx
 * @Date: 2021-08-20 15:36
 */
public class Java8JJSExam {
    public static void main(String[] args) {
        /**
         * Java中调用JavaScript
         * 使用ScriptEngineManager, JavaScript代码可以在Java中执行
         */
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");

        String name = "Runoob";
        Integer result = null;

        try {
            nashorn.eval("print('" + name +"')");
            result = (Integer) nashorn.eval("10 + 2");
        } catch (ScriptException e) {
            System.out.println("执行脚本错误：" + e.getFileName());
        }

        System.out.println(result.toString());

        System.out.println(calculate(568000000000000023L,13.9));

    }

    public static Double calculate(long amount, double percentage) {

        BigDecimal result = new BigDecimal(amount).multiply(new BigDecimal(percentage)).divide(new BigDecimal("100"), 2,BigDecimal.ROUND_HALF_EVEN);

        return result.doubleValue();
    }
}
