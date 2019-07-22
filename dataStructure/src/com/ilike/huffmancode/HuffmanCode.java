package com.ilike.huffmancode;

/**
 * 赫夫曼编码
 */
public class HuffmanCode {

    public static void main(String[] args) {
        String str="i like like like java do you like a java";
        byte[] contentBytes = str.getBytes();
        System.out.println(contentBytes.length);

    }


}

/**
 * 创建Node,待数据和权值
 */
class Node implements Comparable<Node> {
    /**
     * 存放数据本身，比如 ‘a’=>  97 ''=>32
     */
    Byte data;
    int weight;//权值，表示字符出现的次数

    Node left;

    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }
}
