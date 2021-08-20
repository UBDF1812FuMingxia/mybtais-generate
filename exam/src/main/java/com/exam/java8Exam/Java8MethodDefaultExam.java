package com.exam.java8Exam;

/**
 * @ClassName : Java8MethodDefaultExam
 * @Description : java 8 默认方法的测试
 * @Author : fmx
 * @Date: 2021-08-12 17:30
 */
public class Java8MethodDefaultExam {
    public static void main(String[] args) {
        Vehicle vehicle = new Car();
        vehicle.print();
    }
}

interface Vehicle {
    default void print() {
        System.out.println("我是一辆车！");
    }

    static void blowHorn() {
        System.out.println("按喇叭！！！");
    }
}

interface FourWheeler {
    default void print() {
        System.out.println("我是一辆四轮车！");
    }
}

class Car implements Vehicle, FourWheeler {

    public void print() {
        Vehicle.super.print();
        FourWheeler.super.print();
        Vehicle.blowHorn();
        System.out.println("我是一辆汽车！");
    }
}