package com.exam.algorithmExam;

import java.util.Arrays;

/**
 * @ClassName : PlusOne
 * @Description : 加一
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头
 * @Author : fmx
 * @Date: 2021-10-21 09:38
 */
public class PlusOne {
    public static void main(String[] args) {
        int num = 4321;
        int[] digits = {0};
        //拆分生成数据
        //int[] digits = generalArray(num);
        int[] digitsResult = plusOne(digits);
        for (int i = 0;i<digitsResult.length; i++) {
            System.out.print(digitsResult[i]+",");
        }
    }

    private static int[] plusOne(int[] digits) {
        int temp = 1;
        for (int i = digits.length-1; i >= 0; i--) {
            int digit = digits[i];
            if (digit == 9) {
                digits[i] = 0;
            } else {
                digits[i] = digit + temp;
                temp = 0;
                break;
            }
        }
        if (temp == 1) {
            digits = new int[digits.length+1];
            digits[0] =1;
        }
        return digits;
    }

    private static int[] generalArray(int num) {
        String s = String.valueOf(num);
        int[] digits = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            digits[i] = s.charAt(i);
        }
        return digits;
    }
}
