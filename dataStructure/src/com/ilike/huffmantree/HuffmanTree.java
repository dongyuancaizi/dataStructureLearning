package com.ilike.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树
 *
 * @author sangweidong
 * @create 2019-07-21 15:16
 **/
public class HuffmanTree {

    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node node = createHuffmanTree(arr);
        //测试一把
        preOrder(node);

    }

    /**
     * 前序遍历的方法
     */
    public static void preOrder(Node root){
       if(root!=null){
           root.preOrder();
       }else{
           System.out.println("空树");
       }
    }

    /**
     * 创建赫夫曼树
     * @param arr 需要创建成赫夫曼树的数组
     * @return  创建好的赫夫曼树的root节点
     */
    public static Node createHuffmanTree(int[] arr) {
        //第一步，为了操作方便，
        //1.遍历arr数组，
        //2.将arr的每个元素构建成一个Node
        //3.将Node放入到ArrayList中
        List<Node> nodes = new ArrayList<>();
        for (int val : arr) {
            nodes.add(new Node(val));
        }
        //我们处理的过程是一个循环的过程
        while (nodes.size()>1){
            //排序,从小到大
            Collections.sort(nodes);
            System.out.println(nodes);
            //取出根节点权值最小的两棵二叉树
            //1.取出权值最小的节点
            Node leftNode = nodes.get(0);
            //2.取出权值第二小的节点
            Node rightNode = nodes.get(1);
            //3.构建新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //4.从nodes中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //5.将parent加入到nodes
            nodes.add(parent);
        }
        //返回赫夫曼树的root节点
        return nodes.get(0);
    }

}

/**
 * 创建节点类
 * 为了让node对象支持排序
 * 让Node实现Comparable接口
 */
class Node implements Comparable<Node> {
    int value;//节点的权值
    /**
     * 指向左子节点
     */
    Node left;

    /**
     * 指向右子节点
     */
    Node right;

    /**
     * 前序遍历
     */
    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
           this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }

    /**
     * 构造方法
     *
     * @param value
     */
    public Node(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排
        return this.value - o.value;
    }

    @Override
    public String toString() {
        return "Node{" + "value=" + value + '}';
    }
}
