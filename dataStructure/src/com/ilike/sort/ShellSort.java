package com.ilike.sort;

import java.time.Instant;
import java.util.Arrays;

/**
 * 希尔排序（插入排序的优化版）
 */
public class ShellSort {
    public static void main(String[] args) {
        //int [] arr={8,9,1,7,2,3,5,4,6,0};
        //shellSort1(arr);
        //shellSort2(arr);
        //shellSort3(arr);

         // System.out.println(Arrays.toString(arr));
        //1.创建80000个随机数的数组
        long start = Instant.now().getEpochSecond();
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);//会生成一个[0,80000)的随机数
        }
        shellSort3(arr);
        //System.out.println(Arrays.toString(arr));
        long end = Instant.now().getEpochSecond();
        System.out.println("用时：" + (end - start));
    }


    /**
     * 希尔排序
     *    对交换式的希尔排序进行改进->移位法
     */
    public static void shellSort3(int [] arr){

        //增量的gap ,并逐步的缩小增量
        for (int gap = arr.length/2; gap >0 ; gap/=2) {
          //从第gap个元素，逐个对其所在的组进行直接插入
            for (int i = gap; i < arr.length; i++) {
                int j=i;
                int temp=arr[j];
                if(arr[j]<arr[j-gap]){
                    while (j-gap>=0&& temp<arr[j-gap]){
                       //移动
                        arr[j]= arr[j-gap];
                        j-=gap;
                    }
                    //当退出while后，就给temp找到了插入的位置
                    arr[j]=temp;


                }
            }
        }
    }
    /**
     * 希尔排序
     *    对有序序列在插入时采用交换法
     * @param arr
     */
    public static void shellSort2(int [] arr){
        int temp=0;
        int count=0;
        for (int gap = arr.length/2; gap >0 ; gap/=2) {
            for (int i = gap; i <arr.length ; i++) {
                count++;
                //遍历各组中所有的元素(共有gap组)，步长=gap
                for (int j = i-gap; j >=0 ; j-=gap) {
                    //如果当前这个元素，大于加上步长后的哪个元素，说明交换
                    if(arr[j]>arr[j+gap]){
                        temp=arr[j];
                        arr[j]=arr[j+gap];
                        arr[j+gap]=temp;
                    }
                }
            }
          //  System.out.println("希尔排序第"+count+"轮后：");
         //   System.out.println(Arrays.toString(arr));
        }
    }
    /**
     * 希尔排序
     *    使用逐步推导的方式来展示
     */
    public static void shellSort1(int [] arr){
        //希尔排序的第一轮
        //因为第一轮排序是将10个数字分成5组
        int temp=0;
        for (int i = 5; i <arr.length ; i++) {
            //遍历各组中所有的元素(共有5组，每组有2个元素)，步长=5
            for (int j = i-5; j >=0 ; j-=5) {
                //如果当前这个元素，大于加上步长后的哪个元素，说明交换
                if(arr[j]>arr[j+5]){
                    temp=arr[j];
                    arr[j]=arr[j+5];
                    arr[j+5]=temp;
                }
            }
        }

        System.out.println("希尔排序第1轮后：");
        System.out.println(Arrays.toString(arr));
        //希尔排序的第二轮
        //因为第一轮排序是将10个数字分成5/2=2组
        for (int i = 2; i <arr.length ; i++) {
            //遍历各组中所有的元素(共有2组，每组有5个元素)，步长=2
            for (int j = i-2; j >=0 ; j-=2) {
                //如果当前这个元素，大于加上步长后的哪个元素，说明交换
                if(arr[j]>arr[j+2]){
                    temp=arr[j];
                    arr[j]=arr[j+2];
                    arr[j+2]=temp;
                }
            }
        }
        System.out.println("希尔排序第2轮后：");
        System.out.println(Arrays.toString(arr));

        //希尔排序的第三轮
        //因为第一轮排序是将10个数字分成5/2/2=1组
        for (int i = 1; i <arr.length ; i++) {
            //遍历各组中所有的元素(共有1组，每组有10个元素)，步长=1
            for (int j = i-1; j >=0 ; j-=1) {
                //如果当前这个元素，大于加上步长后的哪个元素，说明交换
                if(arr[j]>arr[j+1]){
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        System.out.println("希尔排序第3轮后：");
        System.out.println(Arrays.toString(arr));
    }
}
