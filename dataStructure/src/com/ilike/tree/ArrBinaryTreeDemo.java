package com.ilike.tree;

import com.sun.prism.impl.paint.PaintUtil;

/**
 * 数组顺序存储二叉树
 */
public class ArrBinaryTreeDemo {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        //创建一个ArrBinaryTree
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
    }
}

/**
 * 实现顺序存储二叉树遍历
 */
class ArrBinaryTree{
    /**
     * 存储数据节点的数组
     */
    private int [] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 重载preOrder
     */
    public void preOrder(){
        preOrder(0);
    }


    /**
     * 顺序存储二叉树的一个前序遍历
     * @param index  数组的下标
     */
    public void preOrder(int index){
       //如果数组为空，或者arr.length=0
        if(arr==null||arr.length==0){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向左递归遍历
        if(2*index+1<arr.length){
            preOrder(2*index+1);
        }
        //向右递归遍历
        if(2*index+2<arr.length){
            preOrder(2*index+2);
        }
    }
}
