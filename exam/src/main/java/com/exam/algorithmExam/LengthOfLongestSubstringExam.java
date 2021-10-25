package com.exam.algorithmExam;

import java.util.*;

/**
 * @ClassName : LengthOfLongestSubstringExam
 * @Description : 无重复字符的最长子串    s 由英文字母、数字、符号和空格组成
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * @Author : fmx
 * @Date: 2021-10-13 17:29
 */
public class LengthOfLongestSubstringExam {
    public static void main(String[] args) {
        String string = "abdciekgo34859621-%abcabc-%3958%bbabdciekgo34859621";
        System.out.println("无重复子符的最长子串的长度为："+lengthOfLongestSubstring(string));
    }

    private static int lengthOfLongestSubstring(String s) {

        //方法一：官网提供答案
       /*
       // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
       int rk = -1, ans = 0;
        Set<Character> setS = new HashSet<>();
        int n = s.length();
        // 枚举左指针的位置，初始值隐性地表示为 -1
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                setS.remove(s.charAt(i-1));
            }
            while (rk+1<n && !setS.contains(s.charAt(rk+1))) {
                // 不断地移动右指针
                setS.add(s.charAt(rk+1));
                rk++;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk-i+1);
        }
        return ans;*/

       //方法二：使用map
        Map<Character, Integer> map = new HashMap<>();
        int max = 0 ;
        int start = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if(map.containsKey(c)) {
                //start = Math.max(map.get(c)+1,start);
                if (start <(map.get(c)+1)) {
                    start = map.get(c)+1;
                }
            }
            max = Math.max(max, i - start +1);
            map.put(c,i);
        }
        return max;
    }
}
