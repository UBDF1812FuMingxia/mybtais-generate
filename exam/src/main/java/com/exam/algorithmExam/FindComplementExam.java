package com.exam.algorithmExam;

/**
 * @ClassName : FindComplementExam
 * @Description : 数字的补数
 * 给你一个 正 整数 num ，输出它的补数。补数是对该数的二进制表示取反。
 * @Author : fmx
 * @Date: 2021-10-18 10:07
 */
public class FindComplementExam {
    public static void main(String[] args) {
        int num = 8;
        System.out.println(num+"的补数的是：" + findComplement(num));
    }

    private static int findComplement(int num) {
        int result = 0;
        StringBuilder resultStrB = new StringBuilder();
        while(num != 0) {
            resultStrB.append(num%2);
            num = num / 2;
        }
        String string = resultStrB.toString();
        int length = string.length();
        for (int i = 0; i < length; i++) {
            char c = string.charAt(i);
            if (c == '1') {
                c = 0;
            } else if (c == '0') {
                c = 1;
            }

            result = result + c * (int)Math.pow(2,i);
        }
        return result;
    }
}
