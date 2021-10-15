package com.exam.algorithmExam;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName : TwoSumExam
 * @Description : 两数之和
 * @Author : fmx
 * @Date: 2021-10-12 15:04
 */
public class TwoSumExam {
    public static void main(String[] args) {
        int[] nums = {3,2,4};
        int[] result= new int[2];
        result = twoSum(nums,6);
        Arrays.stream(result).forEach(System.out::print);
    }

    private static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                String s = map.get(nums[i]);
                s = s + "," + i;
                map.put(nums[i],s);
            } else {
                map.put(nums[i], String.valueOf(i));
            }

        }
        for (int j = 0; j < nums.length; j++) {
            int i1 = target - nums[j];
            if(map.containsKey(i1)) {
                String i2 = map.get(i1);
                result[0] = j;
                if (i2.contains(",")) {
                    int i = i2.indexOf(String.valueOf(j));
                    result[1] = Integer.parseInt(i2.split(",")[i+1]);
                    break;
                } else {
                    if (j != Integer.parseInt(i2)) {
                        result[1] = Integer.parseInt(i2);
                        break;
                    }

                }
            }
        }
        return result;

    }
}
