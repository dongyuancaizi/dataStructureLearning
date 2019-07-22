package com.ilike.tree;

import java.time.Instant;
import java.util.Arrays;

/**
 * 堆排序
 *
 * @author sangweidong
 * @create 2019-07-21 11:30
 **/
public class HeapSort {

    public static void main(String[] args) {
        //要求将数组进行升序排列
       /* int[] arr = {4, 6, 8, 5, 9,-10,56,7};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));*/

        //1.创建80000个随机数的数组
        long start = Instant.now().getEpochSecond();
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 80000);//会生成一个[0,80000)的随机数
        }
        heapSort(arr);
        //System.out.println(Arrays.toString(arr));
        long end = Instant.now().getEpochSecond();
        System.out.println("用时：" + (end - start));
    }

    /**
     * 编写一个堆排序的方法
     */
    public static void heapSort(int[] arr) {
        int temp = 0;
        //System.out.println("堆排序");
       /* //1.分布完成
        adjustHeap(arr,1,arr.length);
        System.out.println("第1次："+ Arrays.toString(arr));//{4,9,8,5,6};
        adjustHeap(arr,0,arr.length);
        System.out.println("第2次："+ Arrays.toString(arr));//{9,6,8,5,4}*/

        //完成我们的最终代码

        //1.将无序序列构成一个堆，根据升序需求选择大顶或者小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        //2.将堆顶元素和末尾元素进行交换，将最大元素沉到数组末端

        //3.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序
        for (int j = arr.length - 1; j > 0; j--) {
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
    }

    /**
     * 完成将以i对应的非叶子节点的树（二叉树），调整成一个大顶堆
     * 举例：int [] arr={4,6,8,5,9}; =》i=1 =》adjuestHeap =》{4,9,8,5,6};
     * 如果我们再次调用adjuestHeap 传入的i=0 =》{4,9,8,5,6}  =》 {9,6,8,5,4}
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子节点在数组中的索引
     * @param length 对多少个元素进行调整，length是在逐渐减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        //先取出当前元素的值，保存在一个临时变量
        int temp = arr[i];
        //开始调整
        //说明：
        //1.j = i*2+1  j是i节点的左子节点
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
            if (j + 1 < length && arr[j] < arr[j + 1]) {//说明左子节点的值小于右子节点
                //j指向右子节点
                j++;
            }
            if (arr[j] > temp) {
                //如果子节点大于父节点
                arr[i] = arr[j];//把较大的值赋给当前节点
                i = j;//让i指向j
            }
            else {
                break;
            }
        }
        //当for循环结束后，我们已经将以i为节点的这棵树的最大值，放在了最顶上(局部)
        arr[i] = temp;//将temp放在调整后的位置
    }

}
