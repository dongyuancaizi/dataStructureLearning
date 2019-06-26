package com.ilike.recursion;

/**
 * 8皇后问题
 */
public class Queue8 {

    //先定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array,保存皇后放置位置的结果，比如arr[8]={0,4,7,5,2,6,1,3};
    int[] array = new int[max];
    static int count = 0;

    public static void main(String[] args) {
        //测试
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("解法总数：" + count);

    }

    /**
     * 编写一个方法，放置第n个皇后
     * 特别注意，check每一次递归进入到check都有一套 for (int i = 0; i < max; i++)，因此有回溯
     */
    public void check(int n) {
        //如果
        if (n == max) {//n=8，其实8个皇后已然放好了
            print(array);
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n,放到该行的第一列
            array[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)) {//不冲突，接着放n+1个皇后
                check(n + 1);
            }
            //如果冲突，继续执行array[n]=i,即将第n个皇后，放置在本行后移的一个位置
        }

    }

    /**
     * 查看当我们放置第n和皇后时，就去检测该皇后和前面已经摆放的皇后冲突
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //说明
            //1. array[i]==array[n]表示第n个皇后是否和n-1个皇后在同一列
            //2. Math.abs(n-i)==Math.abs(array[n]-array[i])表示第n个皇后是否和第i个皇后是否在同一个斜线
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 写一个方法,将皇后摆放的位置打印
     */

    private void print(int[] array) {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
