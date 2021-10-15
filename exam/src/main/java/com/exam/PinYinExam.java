package com.exam;

import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.Arrays;

/**
 * @ClassName : PinYinExam
 * @Description : 测试将汉字转换成拼音
 * @Author : fmx
 * @Date: 2021-09-08 17:24
 */
public class PinYinExam {
    public static void main(String[] args) {
        char ch = '重';

        String[] strings = PinyinHelper.toGwoyeuRomatzyhStringArray(ch);
        Arrays.stream(strings).forEach(System.out::print);
        System.out.println();
        System.out.println("--------------------------------------");

        String[] strings1 = PinyinHelper.toHanyuPinyinStringArray(ch);
        Arrays.stream(strings1).forEach(System.out::print);
        System.out.println();
        System.out.println("--------------------------------------");

        String[] strings2 = PinyinHelper.toMPS2PinyinStringArray(ch);
        Arrays.stream(strings2).forEach(System.out::print);
        System.out.println();
        System.out.println("--------------------------------------");

        String[] strings3 = PinyinHelper.toTongyongPinyinStringArray(ch);
        Arrays.stream(strings3).forEach(System.out::print);
        System.out.println();
        System.out.println("--------------------------------------");

        String[] strings4 = PinyinHelper.toWadeGilesPinyinStringArray(ch);
        Arrays.stream(strings4).forEach(System.out::print);
        System.out.println();
        System.out.println("--------------------------------------");

        String[] strings5 = PinyinHelper.toYalePinyinStringArray(ch);
        Arrays.stream(strings5).forEach(System.out::print);
        System.out.println();
        System.out.println("--------------------------------------");


    }
}
