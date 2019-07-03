package com.ilike.sort;

import java.time.Instant;
import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {

    public static void main(String[] args) {
      /*  int [] arr ={8,4,5,7,1,3,6,2};
        int [] temp = new int[arr.length];//归并排序需要一个额外的空间
        mergeSort(arr,0,arr.length-1,temp);
        System.out.println("归并排序后："+ Arrays.toString(arr));*/

        //1.创建80000个随机数的数组
        long start = Instant.now().getEpochSecond();
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 80000);//会生成一个[0,80000)的随机数
        }
        int [] temp = new int[arr.length];//归并排序需要一个额外的空间
        mergeSort(arr,0,arr.length-1,temp);
        //System.out.println(Arrays.toString(arr));
        long end = Instant.now().getEpochSecond();
        System.out.println("用时：" + (end - start));
    }


    /**
     * 分+合的方法
     */
    public static void mergeSort(int [] arr,int left,int right,int [] temp ){
        if(left<right){
            int middle=(left+right)/2;//中间的索引
            //向左递归进行分解
            mergeSort(arr,left,middle,temp);
            //向右递归进行分解
            mergeSort(arr,middle+1,right,temp);
            //合并
            merge(arr,left,middle,right,temp);
        }
    }





    /**
     * 合并
     * @param arr 排序的原始索引
     * @param left 左边有序序列的初始索引
     * @param middle 中间索引
     * @param right 右边有序序列的初始索引
     * @param temp 做中转的数组
     */
    public static void merge(int [] arr,int left,int middle,int right,int [] temp){
        //初始化i,左边有序序列的初始索引
        int i= left;
        //初始化j,右边有序序列的初始索引
        int j=middle+1;
        //指向temp数组的当前索引
        int t=0;
        //（一）
        //先把左右两边(有序)的数据按照规则填充到temp数组，直到左右两边有一边处理完毕为止
        while (i<=middle && j<=right){
            //继续
            if(arr[i]<=arr[j]){
                //如果左边有序序列的当前元素，小于等于右边有序序列的当前元素
                //即将左边的当前元素copy到temp数组
                //t++ ，i++
                temp[t]=arr[i];
               t+=1;
               i+=1;
            }else{
                  //反之将右边的当前元素copy到temp数组
                temp[t]=arr[j];
                t+=1;
                j+=1;
            }
        }

        //（二）
        //把有剩余数据的一边，依次填充到temp
         while (i<=middle){
             //左边的有序序列换有剩余，全部填充到temp
             temp[t]=arr[i];
             t+=1;
             i+=1;
         }
        while (j<=right){
            //右边的有序序列换有剩余，全部填充到temp
            temp[t]=arr[j];
            t+=1;
            j+=1;
        }
        //（三）
        //将temp数组的数据copy到arr
        //并不是每次都拷贝8个
        t=0;
        int tempLeft=left;
            while (tempLeft<=right){ //第一次合并tempLeft=0，right=1
                arr[tempLeft]=temp[t];
                t+=1;
                tempLeft+=1;
            }

    }
}
