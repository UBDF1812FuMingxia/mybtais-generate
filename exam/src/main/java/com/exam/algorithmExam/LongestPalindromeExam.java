package com.exam.algorithmExam;

import java.util.*;

/**
 * @ClassName : LongestPalindromeExam
 * @Description : 最长回文子串
 * @Author : fmx
 * @Date: 2021-10-19 14:44
 */
public class LongestPalindromeExam {
    public static void main(String[] args) {
        String s = "abb";
        //该方法可行，但是对于长字符串，过于浪费时间
        //System.out.println("字符串“"+s+"”中的最长回文子串：" +longestPalindrome1(s));
        System.out.println("字符串“"+s+"”中的最长回文子串：" +longestPalindrome(s));

    }

    private static String longestPalindrome(String s) {
        int maxLength = 0;
        String maxStr = "";
        for (int i = 0; i < s.length(); i++) {
            if (maxLength < s.substring(i).length()) {
                char c = s.charAt(i);
                int end = s.length();
                while (end >= 0) {
                    if (maxLength < s.substring(i,end).length()) {
                        if (s.charAt(--end) == c) {
                            String substring = s.substring(i, end + 1);
                            int length = substring.length();
                            if (maxLength < length && maxLength(substring)) {
                                maxStr = substring;
                                maxLength = length;
                            }
                        }

                    } else {
                        break;
                    }
                }
            } else {
                break;
            }

        }
        return maxStr;
    }

    //该方法嵌套多层循环，消耗时间过多
    private static String longestPalindrome1(String s) {
        Map<Character, List<Integer>> map = new HashMap<>();
        List<Integer> list = null;
        Map<Integer, String> resultMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                list = map.get(c);
            } else {
                list = new ArrayList<>();
            }
            list.add(i);
            map.put(c,list);
        }
        Set<Character> characters = map.keySet();
        for (Character character : characters) {
            list = map.get(character);
            if (list.size() == 1) {
                resultMap.put(1,s.substring(list.get(0),list.get(0)+1));
            } else {
                for (int i = 0; i < list.size()-1; i++) {
                    for (int i1 = i+1; i1 < list.size(); i1++) {
                        String substring = s.substring(list.get(i), list.get(i1) + 1);
                        if (resultMap.containsKey(substring.length())) {

                        }
                        if(maxLength(substring)) {
                            resultMap.put(substring.length(),substring);
                        }
                    }
                }
            }
        }
        Set<Integer> integers = resultMap.keySet();
        Integer[] arr = new Integer[integers.size()];
        Arrays.sort(integers.toArray(arr));
        return resultMap.get(arr[arr.length-1]);
    }

    private static boolean maxLength(String s) {
        boolean flag = true;
        int length = s.length();
        for (int i = 0; i < length/2; i++) {
            if (s.charAt(i) != s.charAt(length - 1-i)) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
