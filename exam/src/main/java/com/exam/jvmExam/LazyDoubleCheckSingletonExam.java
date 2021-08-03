package com.exam.jvmExam;

/**
 * @ClassName : LazyDoubleCheckSingletonExam
 * @Description : jvm中happens-before的使用(通过懒汉式，双重检查锁实现单例)
 * @Author : fmx
 * @Date: 2021-08-02 16:46
 */
public class LazyDoubleCheckSingletonExam {
    private static LazyDoubleCheckSingletonExam instance = null;

    //指令重排序可能导致实例未创建完，但是已经进行了关联，后续操作可能会报错
    //volatile关键字是可以解决指令重排序问题的一种方式
    //private volatile static LazyDoubleCheckSingletonExam instance = null;

    private LazyDoubleCheckSingletonExam() {

    }

    /**
     * 双重检查锁，减小加锁粒度，相对于LazySynchronizedSingleton提升性能，减少不必要的锁开销
     * @return
     */
    public static LazyDoubleCheckSingletonExam getInstance() {
        if (instance == null) {
            synchronized (LazyDoubleCheckSingletonExam.class) {
                if (instance == null) {
                    instance = new LazyDoubleCheckSingletonExam();
                    //CPU执行时候会转换成JVM指令执行
                    //1、分配内存给对象；2、初始化对象；
                    //3、将初始化对象和内存地址建立关联，赋值
                    //4、用户初次访问

                    //注意：存在的问题，指令重排序，有可能2和3顺序颠倒
                }
            }
        }
        return instance;
    }
}
