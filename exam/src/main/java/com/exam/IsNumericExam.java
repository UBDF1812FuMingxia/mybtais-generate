package com.exam;

import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

import java.util.regex.Pattern;

/**
 * @ClassName : IsNumericExam
 * @Description : 判断数据是否是整型
 * @Author : fmx
 * @Date: 2021-10-11 15:10
 */
public class IsNumericExam {
    public static void main(String[] args) {
        //方法一
        isNumeric("2323");
        isNumeric("3r33d3");

        //方法二
        isNumeric1("23231r");
        isNumeric1("3333");

        //方法三
        isNumeric2("r2323");
        isNumeric2("3");
    }

    private static void isNumeric2(String s) {
        Pattern pattern = Pattern.compile("^[-+]?[d]*$");
        System.out.println(pattern.matcher(s).matches());
    }

    //用到了java自带的函数isDigit(char c)
    private static void isNumeric1(String s) {
        boolean flag = true;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                flag = false;
            }
        }
        System.out.println(flag);
    }

    private static void isNumeric(String s) {
        Pattern pattern = Pattern.compile("[0-9]*");
        System.out.println(pattern.matcher(s).matches());
    }

}
