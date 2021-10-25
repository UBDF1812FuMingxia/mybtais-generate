package com.exam.algorithmExam;

import org.jsoup.Jsoup;

import java.math.BigInteger;

/**
 * @ClassName : ConstructRectangleExam
 * @Description : 构造矩形
 * 1. 你设计的矩形页面必须等于给定的目标面积。
 * 2. 宽度 W 不应大于长度 L，换言之，要求 L >= W 。
 * 3. 长度 L 和宽度 W 之间的差距应当尽可能小
 * @Author : fmx
 * @Date: 2021-10-25 14:45
 */
public class ConstructRectangleExam {
    public static void main(String[] args) {
        int area = 800;
        int[] result = constructRectangle(area);
        System.out.println("构成的面积为"+area+"的矩形的长为："+result[0]+"，宽为："+result[1]);
    }

    /**
     * 网上答案：
     * 因为长要大于宽，即宽 *宽 <= 长 * 长，即小于等于area
     * @param area
     * @return
     */
    public static int[] constructRectangle(int area) {
        int w = (int) Math.sqrt(area);//开平方
        while (area % w != 0) {
            --w;
        }
        return new int[]{area / w, w};
    }

    /**
     * 自己想法，数据特别大时，不是特别友好
     * @param area
     * @return
     */
    private static int[] constructRectangle1(int area) {
        int[] result = new int[2];
        int temp = Integer.MAX_VALUE;
        for (int width = 1; width <= area; width++) {
            if (area % width == 0 && width <= area/2+1) {
                int length = area / width;
                if (length >= width && length - width < temp) {
                    temp = length - width;
                    result[0] = length;
                    result[1] = width;
                }
            }
        }
        return result;
    }
}
