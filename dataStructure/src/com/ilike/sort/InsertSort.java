package com.ilike.sort;

import java.time.Instant;
import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertSort {

    public static void main(String[] args) {
        //int [] arr ={101,34,119,1,89,-1};
        //insertSort2(arr);
        //1.创建80000个随机数的数组
        long start = Instant.now().getEpochSecond();
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);//会生成一个[0,80000)的随机数
        }
        insertSort2(arr);
        //System.out.println(Arrays.toString(arr));
        long end = Instant.now().getEpochSecond();
        System.out.println("用时：" + (end - start));
    }

    /**
     * 插入排序，优化后
     * @param arr
     */
    public static void insertSort2(int [] arr){
        //使用for循环
        int insertValue=0;
        int insertIndex=0;//即arr[1]前面这个数的下标
        for (int i = 1; i < arr.length; i++) {
             insertValue=arr[i];
             insertIndex=i-1;//即arr[1]前面这个数的下标
            //给insertValue找到插入的位置
            //说明：
            // 1.insertIndex>=0给insertValue插入位置时不越界
            // 2.待插入的数换没有找到插入位置
            // 3.就需要将arr[insertIndex] 后移
            while (insertIndex>=0&&insertValue<arr[insertIndex]){
                arr[insertIndex+1]= arr[insertIndex];
                insertIndex--;
            }
            //当退出while循环时，插入位置找到，insertIndex+1
            if(insertIndex+1!=i){
                arr[insertIndex+1]=insertValue;
            }
            System.out.println("第"+i+"轮后");
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 插入排序
     * @param arr
     */
    public static void insertSort1(int [] arr){
         //使用逐步推导的方式来演示，便于理解
        //第一轮{101,34,119,1}; =》 {,34,101119,1};
        //定义待插入的数
        int insertValue=arr[1];
        int insertIndex=1-1;//即arr[1]前面这个数的下标
        //给insertValue找到插入的位置
        //说明：
        // 1.insertIndex>=0给insertValue插入位置时不越界
        // 2.待插入的数换没有找到插入位置
        // 3.就需要将arr[insertIndex] 后移
        while (insertIndex>=0&&insertValue<arr[insertIndex]){
            arr[insertIndex+1]= arr[insertIndex];
            insertIndex--;
        }
        //当退出while循环时，插入位置找到，insertIndex+1
        arr[insertIndex+1]=insertValue;
        System.out.println("第一轮后");
        System.out.println(Arrays.toString(arr));
        //第二轮
         insertValue=arr[2];
         insertIndex=2-1;//即arr[1]前面这个数的下标
        //给insertValue找到插入的位置
        //说明：
        // 1.insertIndex>=0给insertValue插入位置时不越界
        // 2.待插入的数换没有找到插入位置
        // 3.就需要将arr[insertIndex] 后移
        while (insertIndex>=0&&insertValue<arr[insertIndex]){
            arr[insertIndex+1]= arr[insertIndex];
            insertIndex--;
        }
        //当退出while循环时，插入位置找到，insertIndex+1
        arr[insertIndex+1]=insertValue;
        System.out.println("第二轮后");
        System.out.println(Arrays.toString(arr));
        //第三轮
        insertValue=arr[3];
        insertIndex=3-1;//即arr[1]前面这个数的下标
        //给insertValue找到插入的位置
        //说明：
        // 1.insertIndex>=0给insertValue插入位置时不越界
        // 2.待插入的数换没有找到插入位置
        // 3.就需要将arr[insertIndex] 后移
        while (insertIndex>=0&&insertValue<arr[insertIndex]){
            arr[insertIndex+1]= arr[insertIndex];
            insertIndex--;
        }
        //当退出while循环时，插入位置找到，insertIndex+1
        arr[insertIndex+1]=insertValue;
        System.out.println("第三轮后");
        System.out.println(Arrays.toString(arr));
    }
}
