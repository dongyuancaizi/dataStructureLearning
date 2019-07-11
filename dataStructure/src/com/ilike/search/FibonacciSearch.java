package com.ilike.search;

import java.util.Arrays;

/**
 * 斐波那契查找（黄金分割位查找）
 */
public class FibonacciSearch {

    public static int maxSize=20;
    public static void main(String[] args) {

        int [] arr={1,8,10,89,1000,1234};
        System.out.println(fibSearch(arr,999));
    }
    /**
     * mid = low +F(k-1)-1
     * 因为后面我们要用到菲波那切数列，因此我们需要先获取一个
     */


    /**
     * 非递归方法得到一个斐波那契数列
     * @return
     */
    public static int[] fib(){
        int []  f =new int[maxSize];
         f[0]=1;
        f[1]=1;
        for (int i = 2; i < maxSize; i++) {
            f[i]=f[i-1]+f[i-2];
        }
        return f;
    }

    /**
     * 菲波那切查找算法（非递归）
     * @param arr  数组
     * @param key 需要查找的元素
     * @return 返回对应下标
     */
    public static int fibSearch(int [] arr,int key){
        int low=0;
        int high=arr.length-1;
        //表示斐波那契分割数值的下标
        int k=0;
        //存放mid值
        int mid=0;
        //获取菲波那切数列
        int [] f= fib();
        //获取到菲波那切数列分割数值的下标
        while (high>f[k]-1){
            k++;
        }
        //因为f[k]值可能大于arr的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向temp
        //不足的部分会使用0填充
        int[] temp= Arrays.copyOf(arr,f[k]);
        //实际上，需要arr数组的最后的数填充temp
        for (int i = high+1; i <temp.length ; i++) {
            temp[i]=arr[high];
        }
        //使用while循环处理，找到我们要找的这个key
        while (low<=high){
            //只要该条件满足，就一直找
            mid = low +f[k-1]-1;
            if(key<temp[mid]){
                //说明我们应该继续向数组的前面查找(左边)
                high=mid-1;
                //说明：
                //1.全部元素=前面的元素+后面的元素
                //2.f[k]=f[k-1]+f[k-2]
                //3.因为前面有f[k-1]个元素，所以我们可以继续拆分f[k-1]=f[k-2]+f[k-3]
                //即f[k-1]的前面继续查找
                k--;
            }else if(key>temp[mid]){
                //说明我们应该继续向数组的后面查找(右边)
                low=mid+1;
                //说明：
                //1.全部元素=前面的元素+后面的元素
                //2.f[k]=f[k-1]+f[k-2]
                //3.因为后面有f[k-2]个元素，所以我们可以继续拆分f[k-2]=f[k-3]+f[k-4]
                //即f[k-2]的前面继续查找
                k-=2;
            }else{
                //需要返回的是哪个下标
                if(mid<=high){
                    //返回mid
                    return mid;
                }else{
                    return high;
                }
            }
        }
        return -1;
    }

}
