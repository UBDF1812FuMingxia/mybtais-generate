package com.exam.algorithmExam;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * @ClassName : SearchMatrixExam
 * @Description : 搜索二维矩阵
 * @Author : fmx
 * @Date: 2021-10-25 09:47
 */
public class SearchMatrixExam {
    public static void main(String[] args) {
        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        int target = 0;
        System.out.println("二维矩阵中是否包含目标值："+target+"------"+searchMatrix(matrix,target));
    }

    /**
     * 网上的答案
     * 对每一行都使用一次二分查找
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        for (int[] row : matrix) {
            int index = search(row, target);
            if (index >= 0) {
                return true;
            }
        }
        return false;
    }

    public static int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int num = nums[mid];
            if (num == target) {
                return mid;
            } else if (num > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 自己写的，效果可以实现，但是感觉if判断过多，影响理解以及可看性
     * @param matrix
     * @param target
     * @return
     */
    private static boolean searchMatrix1(int[][] matrix, int target) {
        boolean flag = false;
        int length = matrix.length;//代表有几行
        for (int i = 0; i < matrix[0].length; i++) {
            //如果第一行的某一列小于目标值，则比较该列的最后一个值是否大于目标值
            if (target > matrix[0][i]) {
                if (target < matrix[length-1][i]) {
                    for (int i1 = 1; i1 < length-1; i1++) {
                        if (target == matrix[i1][i]) {
                            flag = true;
                            break;
                        } else if (target < matrix[i1][i]) {
                            break;
                        }
                    }
                } else if (target == matrix[length-1][i]){
                    flag = true;
                } else {
                    continue;
                }
            } else if (target == matrix[0][i]) {
                flag = true;
            } else {
                continue;
            }
            if (flag) {
                break;
            }
        }
        return flag;
    }
}
