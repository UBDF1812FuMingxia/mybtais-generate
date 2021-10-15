package com.exam.algorithmExam;

import org.omg.CORBA.INTERNAL;

import java.util.*;
import java.util.stream.Collectors;

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
        String string = "abcabc-%3958%bbabdciekgo34859621";
        System.out.println("无重复子符的最长子串的长度为："+lengthOfLongestSubstring(string));
    }

    private static int lengthOfLongestSubstring(String s) {
        Map<String, String> map = new HashMap();
        List list = new ArrayList();
        Set<String> set = new HashSet();
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            String substring = s.substring(i, i + 1);
            if (map.containsKey(substring)) {
                String s1 = map.get(substring);
                String[] split = s1.split(",");
                int maxOld = Integer.parseInt(split[split.length]);
                int i1 = i - maxOld;
                list.add(i1);
                if (j < maxOld + 1) {
                    j = maxOld +1;
                }
                s1 = s1 + "," + i;
                map.put(substring, s1);

                set.add(substring);

            } else {
                map.put(substring,String.valueOf(i));
            }


        }
        list.add(s.length() - j);
        /*Set set = new HashSet();
        set.addAll(list);
        Object[] objects = set.toArray();
        return (int)objects[objects.length-1];*/
        return calculateMax1(set, map,0, s.length(),0);
    }

    private static int calculateMax1(Set<String> set, Map<String, String> map,int start, int end, int max) {
        if (Objects.nonNull(set)) {
            Object[] objects = set.toArray();
            List<String> strList = Arrays.stream(objects).filter(Objects::nonNull).map(o -> (String) o).collect(Collectors.toList());
            String string = strList.get(0);
            String indexStr = map.get(string);
            String[] split = indexStr.split(",");
            for (int i = 0; i < split.length - 1; i++) {
                int index1 = Integer.parseInt(split[i]);
                int index2 = Integer.parseInt(split[i+1]);
                if (start >= index1 && end >= index2) {

                }
            }

        }



        return 0;
    }

    private static int calculateMax(Set<String> set, Map<String, String> map) {

        int max = 0;
        Object[] objects = set.toArray();
        List<String> strList = Arrays.stream(objects).filter(Objects::nonNull).map(o -> (String) o).collect(Collectors.toList());
        for (int i = 0; i < strList.size(); i++) {
            String s = strList.get(i);
            String indexStr = map.get(s);
            if (indexStr.contains(",")) {

                String[] split = indexStr.split(",");
                for (int i1 = 0; i1 < split.length; i1++) {
                    int index = Integer.parseInt(split[i1]);
                    if (i == 0) {
                        if (index > max) {
                            max = Integer.parseInt(split[split.length-2]) + 1;
                        }
                    }
                }

            } else {
                if (max < Integer.parseInt(indexStr)) {
                    max = Integer.parseInt(indexStr);
                }
            }
        }
        return max;
    }

    /*private static int lengthOfLongestSubstringSub(String s, int start, int end) {
        char[] chars = s.toCharArray();
        int maxLength = 0;
        Boolean flag = false;
        List<Integer> list = new ArrayList<>();
        if (start > chars.length-1) {
            return maxLength;
        }
        if (start < end && start+1<end) {
            for (int i = start; i < end-1; i++) {

                char aChar = chars[i];
                int i1 = s.indexOf(String.valueOf(aChar), i + 1);
                if (i1 != -1) {
                    flag = true;
                    if (maxLength < i1 -i) {
                        end = i1;
                        maxLength = i1 - i;
                        int i2 = lengthOfLongestSubstringSub(s, i + 1, end);
                        if (i2 != 0) {
                            maxLength = i2;
                        }
                    }
                } else {
                    end = chars.length;
                    maxLength = chars.length - 1;
                    int i2 = lengthOfLongestSubstringSub(s, i + 1, end);
                    if (i2 != 0) {
                        maxLength = i2;
                    }
                }

            }
            if (!flag) {
                maxLength = 0;
            }
        } else if(start+1 == end){
            if (chars[start] == chars[end]) {
                maxLength = 1;
            } else {
                maxLength = 0;
            }
        }
        return maxLength;
    }*/

}
