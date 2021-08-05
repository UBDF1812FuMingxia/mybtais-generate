package com.exam.proxyPattern.dynamicAgent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @ClassName : CalculatorDynamicProxy
 * @Description : 代理类
 * @Author : fmx
 * @Date: 2021-08-05 15:50
 */
public class CalculatorDynamicProxy {

    private Calculator calculator;//要代理的对象

    public CalculatorDynamicProxy(Calculator calculator) {
        this.calculator = calculator;
    }

    public Calculator getCalculator() {
        Calculator proxy = null;

        //获取类加载器
        ClassLoader loader = calculator.getClass().getClassLoader();

        //代理对象的类型
        Class[] interfaces = {Calculator.class};

        //调用处理器
        InvocationHandler handler = new InvocationHandler() {

            //proxy:正在返回的代理对象
            //method:被调用的方法
            //args:传入的参数

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                System.out.println("----日志记录开始----");
                String name = method.getName();//获取方法的名字
                System.out.println("方法" + name + "()开始执行了");
                System.out.println("方法中的参数是：" + Arrays.asList(args));
                Object invoke = method.invoke(calculator, args);
                System.out.println("方法执行后的结果是" + invoke);
                return invoke;
            }
        };

        //代理对象
        proxy = (Calculator) Proxy.newProxyInstance(loader, interfaces, handler);
        return proxy;
    }


}
