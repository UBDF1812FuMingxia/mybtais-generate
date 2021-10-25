package com.exam.algorithmExam;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName : FindMedianSortedArraysExam
 * @Description : 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数
 * @Author : fmx
 * @Date: 2021-10-18 15:03
 */
public class FindMedianSortedArraysExam {
    public static void main(String[] args) {
        int[] num1 = {1,3};
        int[] num2 = {};
        double median = findMedianSortedArrays(num1,num2);
        System.out.println("两个数组的中位数：" + median);
    }

    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //DecimalFormat decimalFormat = new DecimalFormat("0.00000");
        double median = 0.00000;
        int n1 = nums1.length;
        int n2 = nums2.length;
        int n = 0 ;
        List<Integer> list = new ArrayList<>();
        if (n1 == 0 && n2 != 0) {
            for (int i = 0; i < n2; i++) {
                list.add(nums2[i]);
            }
            n = n2;
        } else if (n1 != 0 && n2 == 0) {
            for (int i = 0; i < n1; i++) {
                list.add(nums1[i]);
            }
            n = n1;
        } else if (n1 != 0 && n2 != 0) {
            n = n1 + n2;
            for (int i = 0; i < n; i++) {
                if (i < n1) {
                    list.add(nums1[i]);
                } else if (i >= n1) {
                    list.add(nums2[i-n1]);
                }
            }
        }
        Collections.sort(list);
        if (n % 2 == 0) {
            median = (list.get(n/2) + list.get(n/2-1))/2.0;
        } else {
            median = list.get(n/2);
        }
        return median;
    }
}
