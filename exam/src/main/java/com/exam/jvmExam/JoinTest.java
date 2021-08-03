package com.exam.jvmExam;

/**
 * @ClassName : JoinTest
 * @Description : 线程的join方法的测试
 * @Author : fmx
 * @Date: 2021-08-02 13:58
 */
public class JoinTest {

    /**
     * join方法：
     * 1、1）相当于让并行执行的程序变成串行执行（即单线程）；2）如果有参数（join(2)），
     * 相当于让main线程等待该线程之后在执行，即在指定时间是单线程，过了改时间就变为多线程
     * 2、如果join方法在start方法之前执行，执行结果依然为交替打印。
     * 所以join()必须在线程start()调用之后调用才有意义（如果一个线程没有start，那他就无法同步）
     * 注意：jdk规定，join(0)的不是等待0秒，而是等待无限时间，即join(0)等价于join()
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadJoinTest test = new ThreadJoinTest("张三");
        ThreadJoinTest test1 = new ThreadJoinTest("李四");
        test.join(0);//
        test.start();
        //test.join(2);//
        test1.start();
    }
}

class ThreadJoinTest extends Thread {
    public ThreadJoinTest(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(this.getName() + ":" + i);
        }
    }
}
