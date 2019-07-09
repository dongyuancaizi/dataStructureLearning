package com.ilike.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 二分查找
 *  注意：
 *     1.二分查找的前提是数组有序
 *
 */
public class BinarySearch {

    public static void main(String[] args) {
        int [] arr={1,8,10,89,1000,1000,1000,1234};
        //int i = binarySearch(arr, 1238, 0, arr.length-1);
        //System.out.println(i);
        List<Integer> integers = binarySearch2(arr, 1000, 0, arr.length - 1);
        System.out.println(integers);
    }

    /**
     * 二分查找
     * @param arr 数据源
     * @param value  要查找的数据
     * @param left   左边的索引
     * @param right  右边的索引
     * @return
     */
    public static int binarySearch( int [] arr,int value,int left,int right) {
        //当left>right,说明遍历了整个数组，没有找到
        if(left>right){
            return -1;
        }
        int middle=(left+right)/2;
        int middleValue=arr[middle];
        if(middleValue<value){
            //向右递归
            return binarySearch(arr,value,middle+1,right);
        }else if(middleValue>value){
            return binarySearch(arr,value,left,middle-1);
        }else{
            return middle;
        }
    }

    /**
     * 二分查找所有与value值相等的数据的下标
     * @param arr  数据源
     * @param value 要查找的数据
     * @param left  左边索引
     * @param right 右边索引
     * @return
     */
    public static List<Integer> binarySearch2(int [] arr, int value, int left, int right) {
        //当left>right,说明遍历了整个数组，没有找到
        if(left>right){
            return  new ArrayList<>();
        }
        int middle=(left+right)/2;
        int middleValue=arr[middle];
        if(middleValue<value){
            //向右递归
            return binarySearch2(arr,value,middle+1,right);
        }else if(middleValue>value){
            return binarySearch2(arr,value,left,middle-1);
        }else{
            List<Integer> list = new ArrayList<>();
            int temp =middle-1;
            while (true){
                if(temp<0||arr[temp]!=value){//退出
                    break;
                }
                list.add(temp);
                temp-=1;
            }
            list.add(middle);
            temp=middle+1;
            while (true){
                if(temp>arr.length-1||arr[temp]!=value){//退出
                    break;
                }
                list.add(temp);
                temp+=1;
            }
            return list;
        }
    }
}
