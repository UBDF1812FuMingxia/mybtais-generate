package com.exam.proxyPattern.dynamicAgent;

/**
 * 场景：实现计算器的加减乘除
 */
public interface Calculator {

    int add(int i, int j);//加

    int sub(int i, int j);//减

    int mul(int i, int j);//乘

    double div(int i, int j);//除
}
