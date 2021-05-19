package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName : MainRun
 * @Description : 启动类
 * @Author : fmx
 * @Date: 2021-05-14 10:05
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.*"})
public class MainRun {

    public static void main(String[] args) {
        new SpringApplication().run(MainRun.class);
    }
}
