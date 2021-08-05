package com.exam.proxyPattern.staticAgent;

import org.springframework.context.annotation.Bean;

/**
 * @ClassName : Extent
 * @Description : 对获取的长度和宽度进行疯转
 * @Author : fmx
 * @Date: 2021-08-05 14:37
 */
public class Extent {

    private String width;

    private String length;

    public Extent(String width, String length) {
        this.width = width;
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getLength() {
        return length;
    }

    @Bean
    public void setLength(String length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Extent{" +
                "width='" + width + '\'' +
                ", length='" + length + '\'' +
                '}';
    }
}
