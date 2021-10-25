package com.exam.algorithmExam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName : MajorityElement
 * @Description : 求众数
 * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 * @Author : fmx
 * @Date: 2021-10-22 11:03
 */
public class MajorityElement {
    public static void main(String[] args) {
        int [] nums = {2,2};
        List<Integer> list = majorityElement(nums);
        list.stream().forEach(System.out::println);
    }

    private static List<Integer> majorityElement(int[] nums) {
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            if (set.contains(num)) {
                continue;
            } else {
                set.add(num);
            }
            int count = 1;
            int start = i+1;
            if (length-i-1 < length/3) {
                break;
            }
            while(start < length) {
                if (count > nums.length/3) {
                    break;
                }
                if (nums[start] == num) {
                    count += 1;
                }
                start++;
            }
            if (count > length/3) {
                list.add(num);
            }
        }
        return list;
    }
}
