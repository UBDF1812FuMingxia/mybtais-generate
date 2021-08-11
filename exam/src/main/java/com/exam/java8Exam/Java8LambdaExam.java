package com.exam.java8Exam;

/**
 * @ClassName : Java8LambdaExam
 * @Description : lambda测试
 * @Author : fmx
 * @Date: 2021-08-11 17:46
 */
public class Java8LambdaExam {
    public static void main(String[] args) {
        Java8LambdaExam exam = new Java8LambdaExam();
        //类型声明
        MathOperation addition = (int a, int b) -> a + b;
        //不用声明类型
        MathOperation subtraction = (a, b) -> a - b;
        //大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };

        //没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;
        System.out.println("10 + 5 = " + exam.operate(10, 5, addition));
        System.out.println("10 - 5 = " + exam.operate(10, 5, subtraction));
        System.out.println("10 * 5 = " + exam.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + exam.operate(10, 5, division));

        //不用括号
        GreetingService greetingService = message ->
                System.out.println("Hello " + message);
        //用括号
        GreetingService greetingService1 = (message) ->
                System.out.println("Hello " + message);
        greetingService.sayMessage("Runoob");
        greetingService1.sayMessage("Google");
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a,b);
    }
}
