package com.exam.java8Exam;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @ClassName : Java8FunctionalInterfaceExam
 * @Description : Java1.8函数式接口的测试
 * @Author : fmx
 * @Date: 2021-08-12 16:38
 */
public class Java8FunctionalInterfaceExam {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);

        //Predicate<Integer> predicate = n -> true
        //n 是一个参数传递到Predicate接口的test方法
        //n 如果存在则test方法返回true
        System.out.println("输出所有数据：");
        //传递参数 n
        eval(list, n->true);

        //Predicate<Integer> predicate1 = n -> n%2 == 0
        //n 是一个参数传递到Predicate接口的test方法
        //如果n%2 为 0,test方法返回true
        System.out.println("输出所有偶数：");
        eval(list, n -> n%2 == 0);

        //Predicate<Integer> predicate2 = n -> n > 3
        //n 是一个参数传递到Predicate接口的test方法
        // 如果n 大于 3，test方法返回true
        System.out.println("输出大于3的所有数字：");
        eval(list, n -> n > 3);

    }

    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n :
                list) {
            if (predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }
}
