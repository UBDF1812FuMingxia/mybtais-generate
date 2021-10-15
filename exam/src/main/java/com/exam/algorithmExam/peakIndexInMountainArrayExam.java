package com.exam.algorithmExam;

/**
 * @ClassName : peakIndexInMountainArrayExam
 * @Description :符合下列属性的数组 arr 称为 山峰数组（山脉数组） ：
 * arr.length >= 3
 * 存在 i（0 < i < arr.length - 1）使得：
 * arr[0] < arr[1] < ... arr[i-1] < arr[i]
 * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * 给定由整数组成的山峰数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i ，即山峰顶部
 *提示：
 * 3 <= arr.length <= 104
 * 0 <= arr[i] <= 106
 * 题目数据保证 arr 是一个山脉数组
 * @Author : fmx
 * @Date: 2021-10-14 09:22
 */
public class peakIndexInMountainArrayExam {
    public static void main(String[] args) {
        int[] arr = {12,13,19,41,55,69,70,71,96,72};
        System.out.println("该山峰数组的山脉元素的下表："+peakIndexInMountainArray(arr));
    }

    public static int peakIndexInMountainArray(int[] arr) {

        int index = 0;
        int i = arr.length/2;
        if (arr.length % 2 != 0) {
            i = i+1;
        }
        int maxLeft = 0;
        int maxRight = i;
        for (int j = 1; j < i; j++) {
            if (arr[j] > arr[maxLeft]) {
                maxLeft = j;
            }
            if (j + i < arr.length) {
                if (arr[j+i] > arr[maxRight]) {
                    maxRight = j + i;
                }
            }
        }
        if (arr[maxLeft] <= arr[maxRight]) {
            index = maxRight;
        } else {
            index = maxLeft;
        }
        return index;

    }
}
