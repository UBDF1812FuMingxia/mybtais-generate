package com.exam.algorithmExam;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : FizzBuzz
 * @Description : FizzBuzz的测试练习
 * @Author : fmx
 * @Date: 2021-10-13 14:20
 */
public class FizzBuzzExam {
    public static void main(String[] args) {
        int n = 30;
        List<String> list = fizzBuzz(n);
        System.out.println(list);
    }

    private static List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0
                    && i % 5 == 0) {
                list.add("FizzBuzz");
            } else if (i % 5 == 0) {
                list.add("Buzz");
            } else if (i % 3 == 0) {
                list.add("Fizz");
            } else {
                list.add(String.valueOf(i));
            }
        }
        return list;
    }
}
