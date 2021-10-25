package com.exam.algorithmExam;

/**
 * @ClassName : convertExam
 * @Description : Z字形变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * @Author : fmx
 * @Date: 2021-10-25 16:14
 */
public class convertExam {
    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 3;
        String sNew = convert(s,numRows);
        System.out.println("原字符串经过Z字形变换后产生的新字符串："+ sNew);
    }

    private static String convert(String s, int numRows) {
        StringBuilder stringBuilder = new StringBuilder();
        /*int length = s.length();
        int one = (numRows * 2 - 2);
        int column = (length/(numRows * 2 - 2))*(numRows - 1);
        int remainder = length % (2 * numRows - 2);
        if (remainder != 0) {
            if (numRows >= remainder) {
                column += 1;
            } else {
                column = column + 1 + (remainder - numRows);
            }
        }
        char[][] arr = new char[numRows][column];
        int rowStart = 0;
        int columnStart = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((i+1)%one == 0) {
                while(columnStart < column ) {
                    if (rowStart < numRows) {

                    }
                }
            }
        }*/
        return stringBuilder.toString();
    }
}
