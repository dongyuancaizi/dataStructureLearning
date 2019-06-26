package com.ilike.sort;

import java.time.Instant;
import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {
    /**
     * 第一趟排序后的数组
     * [3, -1, 9, -2, 10]
     * 第二趟排序后的数组
     * [-1, 3, -2, 9, 10]
     * 第三趟排序后的数组
     * [-1, -2, 3, 9, 10]
     * 第四趟排序后的数组
     * [-2, -1, 3, 9, 10]
     *
     * @param args
     */
    public static void main(String[] args) {
        //int[] arr = {3, 9, -1, 10, 20};
        //int [] arr={1,2,3,4,5,6};
        //为了容易理解，我们把冒泡排序的演变过程展示出来
        //sort1(arr);
        //第一次优化
        //sort2(arr);
        //第二次优化
        //sort3(arr);
        //测试冒泡排序
        //bubbleSort(arr);
        // System.out.println("排序后：");
        // System.out.println(Arrays.toString(arr));

        //测试冒泡排序的时间 O(n^2),给80000个数据，模拟一下
        //1.创建80000个随机数的数组
        long start = Instant.now().getEpochSecond();
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);//会生成一个[0,80000)的随机数
        }
        bubbleSort(arr);
        //System.out.println(Arrays.toString(arr));
        long end = Instant.now().getEpochSecond();
        System.out.println("用时：" + (end - start));
    }

    /**
     * 冒泡排序最终版
     *
     * @param arr 要排序的数组
     */
    public static void bubbleSort(int[] arr) {
        int temp;//临时变量
        boolean flag = false;//标识变量，表示是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {
            flag = true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //如果前面的数比后面的数大，则交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                //在一趟排序中，一次交换都没有发生,说明排序已经完成
                break;
            }

        }
    }

    /**
     * 第二次排序，经过再次优化后的代码
     *
     * @param arr
     */
    private static void sort3(int[] arr) {
        int temp;//临时变量
        boolean flag = false;//标识变量，表示是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {
            flag = true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //如果前面的数比后面的数大，则交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = false;
                }
            }
            System.out.println("第" + (i + 1) + "趟排序后的数组");
            System.out.println(Arrays.toString(arr));
            if (flag) {
                //在一趟排序中，一次交换都没有发生,说明排序已经完成
                break;
            }

        }
    }

    /**
     * 第二次排序，经过优化后的代码
     *
     * @param arr
     */
    private static void sort2(int[] arr) {
        int temp;//临时变量
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //如果前面的数比后面的数大，则交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("第" + (i + 1) + "趟排序后的数组");
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 第一次排序
     *
     * @param arr
     */
    private static void sort1(int[] arr) {
        //第一趟排序，就是将最大的元素排在最后
        int temp;//临时变量
        for (int i = 0; i < arr.length - 1 - 0; i++) {
            //如果前面的数比后面的数大，则交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第一趟排序后的数组");
        System.out.println(Arrays.toString(arr));
        //第二趟排序，就是将第二大的元素排在倒数第二位
        for (int i = 0; i < arr.length - 1 - 1; i++) {
            //如果前面的数比后面的数大，则交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第二趟排序后的数组");
        System.out.println(Arrays.toString(arr));
        //第三趟排序，就是将第三大的元素排在倒数第三位
        for (int i = 0; i < arr.length - 1 - 2; i++) {
            //如果前面的数比后面的数大，则交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第三趟排序后的数组");
        System.out.println(Arrays.toString(arr));
        //第四趟排序，就是将第四大的元素排在倒数第四位
        for (int i = 0; i < arr.length - 1 - 3; i++) {
            //如果前面的数比后面的数大，则交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第四趟排序后的数组");
        System.out.println(Arrays.toString(arr));
    }
}
