package com.exam.algorithmExam;

/**
 * @ClassName : CountAndSayExam
 * @Description : 描述一个数字字符串
 * 给定一个正整数 n ，输出外观数列的第 n 项。
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
 * 你可以将其视作是由递归公式定义的数字字符串序列：
 * countAndSay(1) = "1"
 * countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
 * 前五项如下：
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 第一项是数字 1
 * 描述前一项，这个数是 1 即 “ 一 个 1 ”，记作 "11"
 * 描述前一项，这个数是 11 即 “ 二 个 1 ” ，记作 "21"
 * 描述前一项，这个数是 21 即 “ 一 个 2 + 一 个 1 ” ，记作 "1211"
 * 描述前一项，这个数是 1211 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "111221"
 * @Author : fmx
 * @Date: 2021-10-15 14:09
 */
public class CountAndSayExam {
    public static void main(String[] args) {
        int n = 5;
        //String s = countAndSay(n);
        String s1 = countAndSay1(n);

        System.out.println(s1);
    }

    /**
     * 官方的代码行数减少
     * @param n
     * @return
     */
    private static String countAndSay1(int n) {
        String s = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            int start = 0;
            int pot = 0;
            while (pot < s.length()) {
                while (pot < s.length() && s.charAt(start) == s.charAt(pot)) {
                    pot++;
                }
                stringBuilder.append(String.valueOf(pot-start)).append(s.charAt(start));
                start = pot;
            }
            s = stringBuilder.toString();
        }
        return s;
    }

    /**
     * 自己写的超级繁琐，执行时间长
     * @param n
     * @return
     */
    private static String countAndSay(int n) {
        String s = "";
        if (n >= 1) {
            s = "1";
        }
        for (int i = 2; i <= n; i++) {
            char[] chars = s.toCharArray();
            int length = s.length();
            if (length == 1) {
                s = "1" + chars[0];
            } else if (length == 2) {
                if (chars[0] == chars[1]) {
                    s = "2" + chars[0];
                } else {
                    s = "1" + chars[0] + "1" + chars[1];
                }
            } else if (length > 2) {
                String temp = "";
                for (int j = 0; j < chars.length; ) {
                    char aChar = chars[j];
                    if (j+1 < chars.length && j+2 < chars.length) {
                        char aChar1 = chars[j + 1];
                        char aChar2 = chars[j + 2];
                        if (aChar == aChar1 && aChar == aChar2) {
                            temp = temp + "3";
                            j = j + 3;
                        } else if (aChar == aChar1) {
                            temp = temp + "2";
                            j = j + 2;
                        } else {
                            temp = temp + "1";
                            j = j + 1;
                        }
                    } else if (j+1 < chars.length) {
                        char aChar1 = chars[j + 1];
                        if (aChar == aChar1) {
                            temp = temp + "2";
                            j = j + 2;
                        } else {
                            temp = temp + "1";
                            j = j + 1;
                        }
                    } else {
                        temp = temp + "1";
                        j = j + 1;
                    }
                    temp = temp + aChar;
                }
                s = temp;
            }
        }

        return s;
    }
}
