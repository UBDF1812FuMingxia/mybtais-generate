package com.exam.algorithmExam;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName : TwoNumbersAddExaml
 * @Description : 对两个链表的数据进行求和并且返回链表结构
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * @Author : fmx
 * @Date: 2021-10-13 14:32
 */
public class TwoNumbersAddExam {
    public static void main(String[] args) {
        String string =  "1000000001";
        String string1 = "465";
        ListNode l1 = generateNode(string);
        ListNode l2 = generateNode(string1);
        //方法一：
        ListNode result = new TwoNumbersAddExam().addTwoNumbers1(l1,l2);
        //方法二
        ListNode result1 = new TwoNumbersAddExam().addTwoNumber(l1,l2);
        System.out.println(result1.toString());
        System.out.println(result.toString());
    }

    private ListNode addTwoNumber(ListNode l1, ListNode l2) {
        ListNode result = null;
        int temp = 0;
        return addTN(l1,l2,temp);
    }

    private ListNode addTN(ListNode l1, ListNode l2, int temp) {
        ListNode result = null;
        if (Objects.nonNull(l1) || Objects.nonNull(l2)) {
            int val1 = l1.val;
            int val2 = l2.val;
            int val = val1 + val2 + temp;
            if (val >= 10) {
                temp = val / 10;
                val = val % 10;
            } else {
                temp = 0;
            }
            if (Objects.isNull(l1.next) && Objects.isNull(l2.next) && temp == 0) {
                l1 = null;
                l2 = null;
            } else {
                if (Objects.isNull(l1.next)) {
                    l1 = new ListNode(0);
                } else {
                    l1 = l1.next;
                }
                if (Objects.isNull(l2.next)) {
                    l2 = new ListNode(0);
                } else {
                    l2 = l2.next;
                }
            }

            result = new ListNode(val, addTN(l1,l2,temp));
        }
        return result;
    }

    private ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode result = null;
        BigInteger bigDecimal1 = calculate(l1);
        BigInteger bigDecimal2 = calculate(l2);
        BigInteger bigDecimal = bigDecimal1.add(bigDecimal2);
        String bdStr = bigDecimal.toString();
        result = generateNode(bdStr);
        return result;
    }

    private static ListNode generateNode(String bdStr) {
        ListNode result = null;
        int i = bdStr.length() - 1;
        if (i > 0) {
            result = new ListNode(Integer.parseInt(bdStr.substring(i,i+1)),generateNode(bdStr.substring(0,i)));
        } else {
            result = new ListNode(Integer.parseInt(bdStr.substring(0)));
        }
        return result;
    }

    private static BigInteger calculate(ListNode l1) {
        BigInteger bigDecimal = BigInteger.ZERO;
        List<Integer> list = new ArrayList<>();
        do {
            list.add(l1.val);
            l1 = l1.next;
        } while (Objects.nonNull(l1));
        for (int i = list.size()-1; i >= 0; i--) {
            bigDecimal = bigDecimal.multiply(BigInteger.valueOf(10)).add(BigInteger.valueOf(list.get(i)));
        }
        return bigDecimal;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(){}

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
