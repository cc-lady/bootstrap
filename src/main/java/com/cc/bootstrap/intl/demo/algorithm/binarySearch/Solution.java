package com.cc.bootstrap.intl.demo.algorithm.binarySearch;

import java.util.Arrays;

/**
 * @Description: TODO
 * @author: ChenChen
 * @date: 2024-04-08 21:35
 */
public class Solution {

    public static void main(String[] args) {
        int target = 2;
        int[] datas = {1,2,3,5,8};
        Solution.printArr(datas);

        // 线性查找方式
        int index = searchTarget(datas, target);
        System.out.println("线性查找方式，查询目标值：" + index);

        // 二分查找方式
        Arrays.sort(datas);
        Solution.printArr(datas);
        int index2 = searchSplitTarget(datas, target);
        System.out.println("二分查找方式，查询目标值：" + index2);

    }

    private static int searchSplitTarget(int[] datas, int target) {
        int head = 0,tail = datas.length - 1;
        while (head <= tail) {
            int middle = (head + tail) / 2;
            if (target < datas[middle]) {
                tail = middle - 1;
            } else if (datas[middle] < target) {
                head = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    private static void printArr(int[] datas) {
        System.out.print("datas=[");
        for (int i = 0; i < datas.length; i++) {
            if(i != datas.length - 1) {
                System.out.print(datas[i]+",");
            }else {
                System.out.print(datas[i]);
            }
        }
        System.out.print("]");
        System.out.println();
    }

    private static int searchTarget(int[] datas, int target) {
        for (int i = 0;i<datas.length;i++) {
            if (target == datas[i]) {
                return i;
            }
        }
        return -1;
    }
}


