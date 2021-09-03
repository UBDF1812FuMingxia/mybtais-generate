package com.exam.sortingAlgorithmExam;

import com.exam.proxyPattern.staticAgent.ImageProxy;
import org.springframework.boot.context.event.SpringApplicationEvent;

import java.util.*;

/**
 * @ClassName : BubbleSortingExam
 * @Description : 冒泡排序的测试
 * @Author : fmx
 * @Date: 2021-08-30 17:25
 */
public class BubbleSortingExam {
    public static void main(String[] args) {

        Map map = new LinkedHashMap();
        map.put(null,null);
        System.out.println(map.get(null));
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(null,null);
        System.out.println(linkedHashMap.get(null));
        int[] arr = {2,1, 3, 2, 9, 4, 6,4,5,2,5,3,6,7,86,5,56,43,32};
        int[] arr1 = {2,1, 3, 2, 9, 4, 6,4,5,2,5,3,6,7,86,5,56,43,32};
        int[] arr2 = {2,1, 3, 2, 9, 4, 6,4,5,2,5,3,6,7,86,5,56,43,32};
        int[] arr3 = {2,1, 3, 2, 9, 4, 6,4,5,2,5,3,6,7,86,5,56,43,32};
        int[] arr4 = {2,1, 3, 2, 9, 4, 6,4,5,2,5,3,6,7,86,5,56,43,32};
        int[] arr5 = {2,1, 3, 2, 9, 4, 6,4,5,2,5,3,6,7,86,5,56,43,32};
        int[] arr6 = {2,1, 3, 2, 9, 4, 6,4,5,2,5,3,6,7,86,5,56,43,32};
        //冒泡排序
        System.out.println("--------------冒泡排序--------------");
        bubbleSort(arr);

        //选择排序
        System.out.println("--------------选择排序---------------");
        selectionSort(arr1);

        //插入排序
        System.out.println("-------------插入排序----------------");
        InsertiongSort(arr2);

        //希尔排序
        System.out.println("-------------希尔排序----------------");
        shellSort(arr3);

        //归并排序
        System.out.println("------------归并排序-----------------");
        mergeSort(arr4);
        printArr(arr4);

    }

    /**
     * 归并排序
     * 建立在归并操作上的一种有效的排序算法。
     * 采用分治法，将已有序的子序列合并，得到完全有序的序列；
     * 即先使每个子序列有序，在使子序列段间有序
     * @param arr
     */
    private static void mergeSort(int[] arr) {
        /*
        自己的思路，可以实现
        */
        int length = arr.length;
        if (!Objects.equals(1,length)) {
            int len = length / 2;
            int[] arrLen = Arrays.copyOfRange(arr,0,len);
            int[] arrLen1 = Arrays.copyOfRange(arr,len,length);
            mergeSort(arrLen);
            mergeSort(arrLen1);
            mergeInsert(arr,arrLen,arrLen1);
        }
    }

    /**
     * 两个有序的子数组进行合并
     * @param arr
     * @param arrLen
     * @param arrLen1
     */
    private static void mergeInsert(int[] arr, int[] arrLen, int[] arrLen1) {
        int i = 0,j =0, num = 0 ;
        while (true) {
            if (i >= arrLen.length) {
                for (int k = j; k < arrLen1.length; k++,num++) {
                    arr[num] = arrLen1[k];
                }
                break;
            }
            if (j >= arrLen1.length) {
                for (int k = i; k < arrLen.length; k++,num++) {
                    arr[num] = arrLen[k];
                }
                break;
            }
            if (arrLen[i] < arrLen1[j]) {
                arr[num] = arrLen[i];
                i++;
            } else if(arrLen[i] > arrLen1[j]) {
                arr[num] = arrLen1[j];
                j++;
            } else {
                arr[num++] = arrLen[i];
                arr[num] = arrLen1[j];
                i++;
                j++;
            }
            num++;

        }
    }

    /**
     * 希尔排序
     * 简单插入排序的改进版。与插入排序的不同之处在于，他会优先比较距离较远的元素
     * 将整个待排序的记录序列分割成为若干个序列分别进行直接插入排序
     * 核心：在于间隔序列的设定。
     * @param arr3
     */
    private static void shellSort(int[] arr3) {
        int length = arr3.length;
        //printArr(arr3);
        for (int gap = Math.floorDiv(length, 2); gap > 0; gap = Math.floorDiv(gap,2)) {
            for (int i = gap; i < length; i++) {
                for (int j = i; j-gap >= 0 ; j = j-gap) {
                    if (arr3[j] <arr3[j-gap]) {
                        int temp = arr3[j];
                        arr3[j] = arr3[j-gap];
                        arr3[j-gap] = temp;
                    }
                }
            }
            /*gap：{9，4，2，1}
            这种计算时在gap为1的情况，最后一轮遍历，下标比1大的时候都会计算不到
            System.out.println(gap);
            for (int i = 0; i <gap; i++) {
                for (int j = i; j+gap < length ; j=j+gap) {
                    if (arr3[j] <arr3[j+gap]) {
                        int temp = arr3[j];
                        arr3[j] = arr3[j+gap];
                        arr3[j+gap] = temp;
                    }
                }
            }
            printArr(arr3);*/
        }
        printArr(arr3);
    }

    /**
     * 插入排序
     * 通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入
     * @param arr
     */
    private static void InsertiongSort(int[] arr) {
        int length = arr.length;
        /*for (int i = 0; i < length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] > arr[j-1]) {
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }*/
        int preIndex, current;
        for (int i = 1; i < length; i++) {
            preIndex = i -1;
            current = arr[i];
            while (preIndex >= 0 && arr[preIndex] > current) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
        printArr(arr);
    }

    /**
     * 选择排序
     * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
     * 然后再从剩余排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
     * @param arr
     */
    private static void selectionSort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length -1; i++) {
            int max = i;
            for (int j = i+1; j < length; j++) {
                if (arr[i] < arr[j]) {
                    max = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[max];
            arr[max] = temp;
        }
        printArr(arr);
    }

    /**
     * 冒泡排序
     * 比较相邻的元素。如果第一个比第二个大，就交换她们两个
     * 对每一对相邻元素作同样的工作，从开始第一队到结尾的最后一对，这样在最后的元素应该会是最大的数
     * @param arr
     */
    private static void bubbleSort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length-1; i++) {
            for (int j = 0; j < length-1-i; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        //遍历
        printArr(arr);
    }

    private static void printArr(int[] arr) {
        Arrays.stream(arr).forEach(i -> System.out.print(i + "\t"));
        System.out.println();
        System.out.println("---------------------------------");
    }
}
