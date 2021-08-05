package com.exam.proxyPattern.dynamicAgent;

/**
 * @ClassName : CalculatorExam
 * @Description : 测试动态代理
 * @Author : fmx
 * @Date: 2021-08-05 16:06
 */
public class CalculatorExam {

    public static void main(String[] args) {
        Calculator calculator = new CalculatorImpl();

        Calculator proxy = new CalculatorDynamicProxy(calculator).getCalculator();
        int add = proxy.add(29, 1);

        int sub = proxy.sub(9, 2);

        int mul = proxy.mul(3, 7);

        double div = proxy.div(6, 8);
    }
}
