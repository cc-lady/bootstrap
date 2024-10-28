package com.cc.bootstrap.intl.demo.algorithm.binarySearch;

/**
 * @Description: 冒泡排序
 * @author: ChenChen
 * @date: 2024-04-10 19:29
 */
public class MainSort {

    public static void main(String[] args) {
        int[] arr = {1,34,23,5,19,34,23,5,45,3,77,23,9,23};
        MainSort.printArr(arr);

        // 冒泡排序
        MainSort.bubbleSort(arr);
        MainSort.printArr(arr);
    }

    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
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
