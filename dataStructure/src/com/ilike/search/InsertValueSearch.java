package com.ilike.search;

import java.util.Arrays;

/**
 * 插值查找算法
 */
public class InsertValueSearch {

    public static void main(String[] args) {
        int [] arr=new int[100];
        for (int i = 0; i < 100; i++) {
          arr[i]=i+1;
        }
        //System.out.println(Arrays.toString(arr));
        int i = insertValueSearch(arr, 0, arr.length - 1, 23);
        System.out.println(i);
    }

    /**
     * 插值查找
     *  说明：插值查找算法也要求数组是有序的
     * @param arr 传入的数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findValue 要查找的值
     * @return
     */
    public static  int insertValueSearch(int [] arr,int left ,int right ,int findValue){
        System.out.println("haha");
        //终止条件
        //注意：arr[0]>findValue和arr[arr.length-1]<findValue必须要有，否则可能越界
         if(left>right||arr[0]>findValue||arr[arr.length-1]<findValue){
             return -1;
         }
         //求出mid
         int mid=left+(right-left)*(findValue-arr[left])/(arr[right]-arr[left]);
         int midValue=arr[mid];
         if(findValue>midValue){
             //向右边查找
            return insertValueSearch(arr,mid+1,right,findValue);
         }else if(findValue<midValue){
             //向左边查找
             return insertValueSearch(arr,left,mid-1,findValue);
         }else{
             return mid;
         }
    }
}
