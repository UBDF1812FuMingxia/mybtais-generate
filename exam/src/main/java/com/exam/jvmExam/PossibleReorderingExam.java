package com.exam.jvmExam;

/**
 * @ClassName : PossibleReorderingExam
 * @Description : jvm内存的指令重排序的逻辑理解的测试
 * @Author : fmx
 * @Date: 2021-08-02 10:58
 */
public class PossibleReorderingExam {
    static int x = 0, y = 0;
    static int a = 0, b = 0;
    public static void main(String[] args) throws InterruptedException {
        /*for(int i = 0; i < 200; i++){
            new Test().test();
        }*/
        test1();
    }

    public static void test1() throws InterruptedException {
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                a = 1;
                x = b;
            }
        });

        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                b = 1;
                y = a;
            }
        });

        one.start();

        two.start();
        System.out.println("测");
        one.join();
        two.join();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("测试");
            }
        });

        Thread three = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("2");
            }
        });
        three.start();
        three.join();

        System.out.println("(" + x + "," + y +")");

    }


}

class Test {
    int x = 0, y = 0;
    int a = 0, b = 0;
    public void test() throws InterruptedException {
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                a = 1;
                x = b;
            }
        });

        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                b = 1;
                y = a;
            }
        });

        one.start();
        two.start();
        one.join();
        two.join();
        System.out.println("(" + x + "," + y +")");
    }
}
