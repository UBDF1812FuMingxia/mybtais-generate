package com.exam.algorithmExam;

import java.util.*;

/**
 * @ClassName : MinMovesExam
 * @Description : 最小操作次数使数组元素相等
 * @Author : fmx
 * @Date: 2021-10-20 09:27
 */
public class MinMovesExam {
    public static void main(String[] args) {
        int[] nums = {1,2,3,6,2,30};
        System.out.println("当前数组使元素相等的最小操作次数：" + minMoves(nums));
    }

    //我发现使数组中所有的元素都相等的最小次数就是各个数与最小的数的差的和
    private static int minMoves(int[] nums) {
        //Arrays.stream(nums).min().getAsInt();//网上使用该方法获得最小数
        int result = 0;
        Arrays.sort(nums);
        int num = nums[0];
        if (nums.length > 1) {
            for (int i = 1; i < nums.length; i++) {
                result = result + (nums[i]-num);
            }
        }

        return result;
    }

    private static int minMoves2(int[] nums) {
        int result = 0;
        while (true) {
            //方法二：也可，但是也存在超时的问题，数组过大时，循环存在问题；
            Arrays.sort(nums);
            if (nums[0] == nums[nums.length-1]) {
                break;
            } else {
                int i = nums[nums.length - 1] - nums[0];
                result += i;
                for (int i1 = 0; i1 < nums.length - 1; i1++) {
                    nums[i1] = nums[i1] + i;
                }
            }
        }

        return 0;
    }


    private static int minMoves1(int[] nums) {
        int result = 0;
        Set<Integer> set;
        int n = nums.length;
        int max = 0;

        while (true) {

            //方法一：可以，但是在leetcode执行时超时了，并且代码繁琐
            set = new TreeSet<>();
            for (int i = 0; i < n; i++) {
                int num = nums[i];
                set.add(num);
                if (nums[i] > nums[max]) {
                    max = i;
                }
            }
            if (set.size() != 1) {
                for (int i = 0; i < nums.length; i++) {
                    if (max != i) {
                        nums[i] = nums[i] +1;
                    }
                }
                result += 1;
            } else {
                break;
            }
        }
        return result;
    }
}
