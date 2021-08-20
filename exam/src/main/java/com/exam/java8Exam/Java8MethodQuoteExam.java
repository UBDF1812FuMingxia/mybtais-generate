package com.exam.java8Exam;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName : Java8MethodQuoteExam
 * @Description : Java8新特性--方法的引用的测试
 * @Author : fmx
 * @Date: 2021-08-12 14:55
 */
public class Java8MethodQuoteExam {
    @FunctionalInterface
    public interface Supplier<T> {
        T get();
    }

    //Supplier是JDK1.8的接口，这里和lambda一起使用了
    public static Java8MethodQuoteExam create(final Supplier<Java8MethodQuoteExam> supplier) {
        return supplier.get();
    }

    public static void collide(final Java8MethodQuoteExam java8MethodQuoteExam) {
        System.out.println("Collide " + java8MethodQuoteExam.toString());
    }

    public void follow(final Java8MethodQuoteExam another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

    public static void main(String[] args) {
        //构造器引用：语法是Class::new，或者更一般的Class<T>::new实例如下：
        Java8MethodQuoteExam exam = Java8MethodQuoteExam
                .create(Java8MethodQuoteExam::new);
        Java8MethodQuoteExam exam1 = Java8MethodQuoteExam
                .create(Java8MethodQuoteExam::new);
        Java8MethodQuoteExam exam2 = Java8MethodQuoteExam
                .create(Java8MethodQuoteExam::new);
        Java8MethodQuoteExam exam3 = new Java8MethodQuoteExam();
        List<Java8MethodQuoteExam> java8MethodQuoteExams
                = Arrays.asList(exam, exam1, exam2, exam3);
        System.out.println("======================构造器引用=================");
        //静态方法引用：语法是Class::static_method,实例如下
        java8MethodQuoteExams.forEach(Java8MethodQuoteExam::collide);
        System.out.println("======================静态方法引用================");
        //特定类的任意对象的方法引用：语法是Class::method，实例如下：
        java8MethodQuoteExams.forEach(Java8MethodQuoteExam::repair);
        System.out.println("======================特定类的任意对象的方法引用=================");
        //特定对象的方法引用：语法是instance::method，实例如下：
        final Java8MethodQuoteExam exam4 = Java8MethodQuoteExam
                .create(Java8MethodQuoteExam::new);
        java8MethodQuoteExams.forEach(exam4::follow);
        System.out.println("特定对象的方法引用");

        //2、方法引用实例
        List names = new ArrayList();
        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");
        names.forEach(System.out::print);
    }
}
