package com.cc.bootstrap.intl.demo.algorithm.binarySearch;

import java.util.Arrays;

/**
 * @Description: 合并数组
 * @author: ChenChen
 * @date: 2024-04-10 20:40
 */
public class LeetCode {

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = {4,5,6};
        int n = 3;
        LeetCode.merge(nums1, m, nums2, n);
        LeetCode.printArr(nums1);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int len = m + n;

        int index = 0;
        for (int i = m; i < len; i ++) {
            nums1[i] = nums2[index];
            index ++;
        }
        
        Arrays.sort(nums1);
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
}
