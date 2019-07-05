package com.ilike.sort;

import java.time.Instant;
import java.util.Arrays;

/**
 * 基数排序
 */
public class RadixSort {

    public static void main(String[] args) {
        //int [] arr={53,3,542,748,14,214};
        //radixSort1(arr);
        //radixSort(arr);
        //1.创建80000个随机数的数组
        long start = Instant.now().getEpochSecond();
        int[] arr = new int[80000000];
        for (int i = 0; i < 80000000; i++) {
            arr[i] = (int) (Math.random() * 80000);//会生成一个[0,80000)的随机数
        }
        radixSort(arr);
        //System.out.println(Arrays.toString(arr));
        long end = Instant.now().getEpochSecond();
        System.out.println("用时：" + (end - start));
    }

    /**
     * 基数排序最终版
     * @param arr
     */
    public static void  radixSort( int [] arr){
        //1.获取数组中最大的数的位数
        //假设第一个数就是最大的
        int max=arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]>max){
                max =arr[i];
            }
        }
        //得到最大数是几位数
        int maxLength=(max+"").length();
        int [][] bucket=new int[10][arr.length];
        int [] bucketElementCounts=new int[10];
        //这里使用循环
        for (int k = 0,n=1; k < maxLength; k++,n*=10) {
            for (int i = 0; i <arr.length ; i++) {
                //取出每个元素的对应位位数
                int digitOfElement=arr[i]/n%10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]]=arr[i];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来的数组中)
            int index=0;
            //遍历每一个桶，并将桶中的数据，放入到原数组
            for (int i = 0; i <bucketElementCounts.length; i++) {
                //如果桶中有数据，我们才放入到原数组
                if(bucketElementCounts[i]!=0){
                    //循环第i个桶（即第i个一维数组）
                    for (int j = 0; j <bucketElementCounts[i] ; j++) {
                        //放入到arr中
                        arr[index]=bucket[i][j];
                        index++;
                    }
                }
                //每一轮处理后，需要将每个bucketElementCounts[i]=0;！！！！
                bucketElementCounts[i]=0;
            }
           // System.out.println("第"+(k+1)+"轮结果:");
           // System.out.println(Arrays.toString(arr));
        }
    }


    /**
     * 基数排序
     * @param arr
     */
    public static void  radixSort1( int [] arr){
        //第一轮排序，针对每个元素的个位数排序

        //定义一个二维数据，表示10个桶，每个桶就是一个一维数组
        //说明
         //1.二维数组包含10个一维数组
         //2.为了防止在放入数据的时候，数据溢出，则每个一维数组(桶)，大小只能定位arr.length;
        //3.基数算法是空间换时间的经典算法
        int [][] bucket=new int[10][arr.length];

        //为了记录每个桶中实际存储了多少个数据，我们定义一个一维数组记录各个桶每次放入的数据的个数
        //可以这样理解，比如 bucketElementCounts[0],记录的就是bucket[0]这个桶的元素的个数
        int [] bucketElementCounts=new int[10];

        //第一轮排序，针对每个元素的个位数排序
        for (int i = 0; i <arr.length ; i++) {
           //取出每个元素的个位数
            int digitOfElement=arr[i]%10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]]=arr[i];
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来的数组中)
        int index=0;
        //遍历每一个桶，并将桶中的数据，放入到原数组
        for (int i = 0; i <bucketElementCounts.length; i++) {
            //如果桶中有数据，我们才放入到原数组
            if(bucketElementCounts[i]!=0){
                //循环第i个桶（即第i个一维数组）
                for (int j = 0; j <bucketElementCounts[i] ; j++) {
                   //放入到arr中
                  arr[index]=bucket[i][j];
                  index++;
                }
            }
            //第一轮处理后，需要将每个bucketElementCounts[i]=0;！！！！
            bucketElementCounts[i]=0;
        }

        System.out.println("第1轮结果:");
        System.out.println(Arrays.toString(arr));
          //第二轮
        for (int i = 0; i <arr.length ; i++) {
            //取出每个元素的十位数
            int digitOfElement=arr[i]/10%10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]]=arr[i];
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来的数组中)
        index=0;
        //遍历每一个桶，并将桶中的数据，放入到原数组
        for (int i = 0; i <bucketElementCounts.length; i++) {
            //如果桶中有数据，我们才放入到原数组
            if(bucketElementCounts[i]!=0){
                //循环第i个桶（即第i个一维数组）
                for (int j = 0; j <bucketElementCounts[i] ; j++) {
                    //放入到arr中
                    arr[index]=bucket[i][j];
                    index++;
                }
            }
            //第二轮处理后，需要将每个bucketElementCounts[i]=0;！！！！
            bucketElementCounts[i]=0;

        }
        System.out.println("第2轮结果:");
        System.out.println(Arrays.toString(arr));

        //第三轮
        for (int i = 0; i <arr.length ; i++) {
            //取出每个元素的百位数
            int digitOfElement=arr[i]/100%10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]]=arr[i];
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来的数组中)
        index=0;
        //遍历每一个桶，并将桶中的数据，放入到原数组
        for (int i = 0; i <bucketElementCounts.length; i++) {
            //如果桶中有数据，我们才放入到原数组
            if(bucketElementCounts[i]!=0){
                //循环第i个桶（即第i个一维数组）
                for (int j = 0; j <bucketElementCounts[i] ; j++) {
                    //放入到arr中
                    arr[index]=bucket[i][j];
                    index++;
                }
            }


        }
        System.out.println("第3轮结果:");
        System.out.println(Arrays.toString(arr));

    }
}
