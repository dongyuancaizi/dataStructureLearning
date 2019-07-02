package com.ilike.sort;

import java.time.Instant;
import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {

    public static void main(String[] args) {
        //int [] arr ={-9,78,0,23,-567,70,-1,900,456,1};
        //quickSort(arr,0,arr.length-1);
       // System.out.println(Arrays.toString(arr));

        //1.创建80000个随机数的数组
        long start = Instant.now().getEpochSecond();
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 800000);//会生成一个[0,80000)的随机数
        }
        quickSort(arr,0,arr.length-1);
        //System.out.println(Arrays.toString(arr));
        long end = Instant.now().getEpochSecond();
        System.out.println("用时：" + (end - start));
    }

    /**
     * 快速排序
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort(int [] arr,int left,int right){
        int l=left;//左下标
        int r=right;//右下标
        //中轴值
        int pivot=arr[(left+right)/2];
        int temp=0;//临时变量作为交换时使用
        //while循环的目的是让比pivot小放到左边
        //比pivot大放到右边
        while (l<r){
            //在pivot的左边一直找，找到大于或者等于pivot的值，才退出
            while (arr[l]<pivot){
                l+=1;
            }
            //在pivot的右边一直找，找到小于或者等于pivot的值，才退出
            while (arr[r]>pivot){
                r-=1;
            }
            //如果l>=r，说明pivot左右两边的值已经按照左边全部是小于等于pivot的值，右边是大于等于pivot的值
            if(l>=r){
                break;
            }
            //交换
            temp=arr[r];
            arr[r]=arr[l];
            arr[l]=temp;

            //如果交换完后，发现这个arr[l]==pivot;  相等 r-- ，前移
             if(arr[l]==pivot){
                 r-=1;
             }
            //如果交换完后，发现这个arr[r]==pivot;  相等 l-- ，后移
            if(arr[l]==pivot){
                l+=1;
            }
        }
        //如果l==r ,必须l++,r--,否则会出现栈溢出
        if(l==r){
            l+=1;
            r-=1;
        }
        //向左递归
        if(left<r){
            quickSort(arr,left,r);
        }
        //向右递归
        if(right>l){
            quickSort(arr,l,right);
        }



    }

}
