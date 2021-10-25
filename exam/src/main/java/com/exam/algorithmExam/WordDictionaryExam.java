package com.exam.algorithmExam;

import com.fasterxml.jackson.databind.deser.CreatorProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @ClassName : WordDictionaryExam
 * @Description : 添加与搜索单词
 * 实现词典类 WordDictionary ：
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
 * @Author : fmx
 * @Date: 2021-10-19 09:29
 */
public class WordDictionaryExam {

    Map<Integer,Set<String>> map = new HashMap<>();

    {
        Set<String> list = new HashSet<>();
        list.add("WordDictionary");
        map.put(14,list);
        list = new HashSet<>();
        list.add("addWord");
        map.put(7,list);
        list = new HashSet<>();
        list.add("search");
        map.put(6,list);

    }

    public static void main(String[] args) {
        WordDictionaryExam wordDictionaryExam = new WordDictionaryExam();
        wordDictionaryExam.addWord("bad");
        wordDictionaryExam.addWord("dad");
        wordDictionaryExam.addWord("mad");
        wordDictionaryExam.addWord("a");
        wordDictionaryExam.addWord("ab");

        System.out.println("是否包含pad  ：" + wordDictionaryExam.search("pad"));;
        System.out.println("是否包含bad  ：" + wordDictionaryExam.search("bad"));;
        System.out.println("是否包含.ad  ：" + wordDictionaryExam.search(".ad"));;
        System.out.println("是否包含b..  ：" + wordDictionaryExam.search("."));;
        System.out.println("是否包含b..  ：" + wordDictionaryExam.search(".a"));;
    }


    public WordDictionaryExam() {

    }

    public void addWord(String word) {
        int length = word.length();
        Set<String> strings = null;
        if (map.containsKey(length)) {
            strings = map.get(length);
            strings.add(word);
        } else {
            strings = new HashSet<>();
            strings.add(word);
        }
        map.put(length,strings);
    }

    public boolean search(String word) {
        boolean flag = false;
        int length = word.length();
        if (map.containsKey(length)) {
            Set<String> strings = map.get(length);
            if (!word.contains(".")) {
                flag = hasContains(strings, word, true);
            } else {
                String[] split = word.split("\\.");
                String[] sHandle = Arrays.stream(split).filter(s -> Objects.nonNull(s) && !s.equals("")).collect(Collectors.toList()).toArray(split);

                if (sHandle.length == 1) {
                    String s = sHandle[0];
                    if (word.charAt(0) == '.') {
                        flag = hasContains(strings, s+"_"+0, false);
                    } else if (word.charAt(word.length()-1) == '.') {
                        flag = hasContains(strings, s+"_"+1, false);
                    }
                } else if (sHandle.length == 0) {
                    flag = true;
                } else {
                    for (String string : strings) {
                        boolean f = true;
                        for (int i = 0; i < string.length(); i++) {
                            char c = string.charAt(i);
                            char c1 = word.charAt(i);
                            if (c1 != '.' && c1 != c) {
                                f = false;
                                break;
                            }
                        }
                        if (f) {
                            flag = true;
                        }
                    }
                }
            }
        }
        return flag;
    }

    private boolean hasContains(Set<String> ss, String replace, boolean b) {
        boolean flag = false;
        if (!b) {
            for (String s : ss) {
                String[] s1 = replace.split("_");
                if (s1[1].equals("0")) {
                    s = s.substring(s.length()-s1[0].length(),s.length());
                } else if (s1[1].equals("1")) {
                    s = s.substring(0,s1[0].length());
                }
                if (s.equals(s1[0])) {
                    flag = true;
                    break;
                }
            }
        } else {
            flag =  ss.contains(replace);
        }
        return flag;
    }

}
