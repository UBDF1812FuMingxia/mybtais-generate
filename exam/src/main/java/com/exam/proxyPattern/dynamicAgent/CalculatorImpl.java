package com.exam.proxyPattern.dynamicAgent;

/**
 * @ClassName : CalculatorImpl
 * @Description : 实现计算器的接口
 * @Author : fmx
 * @Date: 2021-08-05 15:48
 */
public class CalculatorImpl implements Calculator {
    @Override
    public int add(int i, int j) {
        return i + j;
    }

    @Override
    public int sub(int i, int j) {
        return i - j;
    }

    @Override
    public int mul(int i, int j) {
        return i * j;
    }

    @Override
    public double div(int i, int j) {
        return (i/j);
    }
}
