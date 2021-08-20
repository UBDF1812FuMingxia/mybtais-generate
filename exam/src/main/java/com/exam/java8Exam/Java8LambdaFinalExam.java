package com.exam.java8Exam;

/**
 * @ClassName : Java8LambdaFinalExam
 * @Description : lambda表达式中变量的作用域
 * @Author : fmx
 * @Date: 2021-08-12 14:06
 */
public class Java8LambdaFinalExam {
    final static String salutation = "Hello!";

    public static void main(String[] args) {
        Java8LambdaExam.GreetingService greetingService = message ->
                System.out.println(salutation + message);
        greetingService.sayMessage("Runoob");


        Java8LambdaExam.GreetingService greetingService1
                = new Java8LambdaExam.GreetingService() {

            @Override
            public void sayMessage(String message) {
                System.out.println(salutation + message);
            }
        };
        greetingService1.sayMessage("jack");

        //2、可以在lambda表达式中访问外层的局部变量
        final  int num = 1;
        Converter<Integer, String> s = (param)
                -> System.out.println(String.valueOf(param + num));
        s.convert(2);

        //3、lambda表达式的局部变量可以不用声明为final，但是必须不可被后面的代码修改
        int num1 = 1;
        Converter<Integer, String> s1
                = (param) ->
                System.out.println(String.valueOf(param + num1));

        s1.convert(2);
        //num1 = 5;//如果进行参数值的修改，编译通不过
    }

    public interface Converter<T1, T2> {
        void convert(int i);
    }
}
