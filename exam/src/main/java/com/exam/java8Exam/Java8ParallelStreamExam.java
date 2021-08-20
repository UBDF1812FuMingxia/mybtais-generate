package com.exam.java8Exam;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName : Java8ParallelStreamExam
 * @Description : Java 8 新特性并行流的测试
 * @Author : fmx
 * @Date: 2021-08-19 15:44
 */
public class Java8ParallelStreamExam {

    public static void main(String[] args) {
        Java8ParallelStreamExam java8ParallelStreamExam = new Java8ParallelStreamExam();

        Thread thread = new Thread(() -> {
            java8ParallelStreamExam.streamTest();
        });

        Thread thread1 = new Thread(() -> {
            java8ParallelStreamExam.streamTest2();
        });

        thread.start();
        thread1.start();

        try {
            thread.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //第一个并行流
    public void streamTest() {
        try {
            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
            numbers.parallelStream().forEach(num -> {
                System.out.println("第一个请求并行：" +
                        Thread.currentThread().getName() +
                        ">>" + num);
                try {
                    Thread.sleep(5000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //第二个并行流
    public void streamTest2() {
        try {
            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
            numbers.parallelStream().forEach(num -> {
                System.out.println("第二次请求并行：" +
                        Thread.currentThread().getName() +
                        ">>" + num);
                try {
                    Thread.sleep(5000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
