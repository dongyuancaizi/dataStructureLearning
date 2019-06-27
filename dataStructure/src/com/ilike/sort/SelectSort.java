package com.ilike.sort;

import java.time.Instant;
import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
      //int [] arr= {101,34,119,1};
       // System.out.println("排序前");
        //System.out.println(Arrays.toString(arr));
        //selectSort1(arr);
       // selectSort2(arr);
        //1.创建80000个随机数的数组
        long start = Instant.now().getEpochSecond();
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);//会生成一个[0,80000)的随机数
        }
        selectSort2(arr);
        //System.out.println(Arrays.toString(arr));
        long end = Instant.now().getEpochSecond();
        System.out.println("用时：" + (end - start));
    }

    /**
     * 选择排序（第一次优化后）
     * @param arr
     */
    public static void selectSort2(int [] arr){
        for (int i = 0; i <arr.length-1 ; i++) {
            int minIndex=i;
            int min=arr[i];
            for (int j = 1+i; j < arr.length; j++) {
                if(min>arr[j]){
                    //假定的最小值不是最小值
                    min=arr[j];
                    minIndex=j;
                }
            }
            if(minIndex!=i){
                arr[minIndex]=arr[i];
                arr[i]=min;
            }

        }
    }

    /**
     * 选择排序
     * @param arr
     */
    public static void selectSort1(int [] arr){
        //在推导的过程中我们发现了规律，可以使用一个for循环来完成
      //使用逐步推导的方式来展示选择排序
        //原始数组：101，34，119,1
        //第一轮排序：1，34，119,101

        int minIndex=0;
        int min=arr[0];
        for (int i = 1; i <arr.length ; i++) {
            if(min>arr[i]){
                //假定的最小值不是最小值
                min=arr[i];
                minIndex=i;
            }
        }
      //将最小值放在arr[0]位置,即交换
        if(minIndex!=0){
            arr[minIndex]=arr[0];
            arr[0]=min;
        }

        System.out.println("第一轮后");
        System.out.println(Arrays.toString(arr));
        //第二轮
         minIndex=1;
         min=arr[1];
        for (int i = 1+1; i <arr.length ; i++) {
            if(min>arr[i]){
                //假定的最小值不是最小值
                min=arr[i];
                minIndex=i;
            }
        }
        //将最小值放在arr[0]位置,即交换
        if(minIndex!=1){
            arr[minIndex]=arr[1];
            arr[1]=min;
        }
        System.out.println("第二轮后");
        System.out.println(Arrays.toString(arr));
        //第三轮
        minIndex=2;
        min=arr[2];
        for (int i = 1+2; i <arr.length ; i++) {
            if(min>arr[i]){
                //假定的最小值不是最小值
                min=arr[i];
                minIndex=i;
            }
        }
        //将最小值放在arr[0]位置,即交换
        if(minIndex!=2){
            arr[minIndex]=arr[2];
            arr[2]=min;
        }
        System.out.println("第三轮后");
        System.out.println(Arrays.toString(arr));
    }
}
