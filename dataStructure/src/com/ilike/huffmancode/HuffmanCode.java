package com.ilike.huffmancode;

import java.util.*;

/**
 * 赫夫曼编码
 */
public class HuffmanCode {

    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] contentBytes = str.getBytes();
        List<Node> nodes = getNodes(contentBytes);
        System.out.println(nodes);
        //测试创建的赫夫曼树
        System.out.println("赫夫曼树");
        Node huffmanTree = createHuffmanTree(nodes);
        //前序遍历
        System.out.println("前序遍历");
        preOrder(huffmanTree);
        //测试是否生成了哈夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTree);
       /* System.out.println(contentBytes.length);

        System.out.println("生成的哈夫曼编码表："+huffmanCodes);

        //测试
        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
        System.out.println(Arrays.toString(huffmanCodeBytes));*/

         byte[] bytes = huffmanZip(contentBytes);
        //    System.out.println(Arrays.toString(bytes));
        //测试byteToBitStr
        //System.out.println(Arrays.toString(contentBytes));
        byte[] decode = decode(bytes, huffmanCodes);
        System.out.println(new String(decode));


    }


    /**
     * 解码
     *
     * @param huffmanCodeBytes 编码后得到的字节数组
     * @param huffmanCodes     哈夫曼编码表
     * @return 原来字符串对应的数组
     */

    public static byte[] decode(byte[] huffmanCodeBytes, Map<Byte, String> huffmanCodes) {
      //2.先得到huffmanCodeBytes对应的二进制的字符串
      StringBuilder sb= new StringBuilder();
        for (int i = 0; i < huffmanCodeBytes.length; i++) {
            boolean flag= i==huffmanCodeBytes.length-1;
            sb.append(byteToBitStr(!flag,huffmanCodeBytes[i]));
        }
     //把字符串按照指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行转换
        Map<String,Byte> map= new HashMap<>();
        for (Map.Entry<Byte, String> huffmanCode: huffmanCodes.entrySet()) {
            map.put(huffmanCode.getValue(),huffmanCode.getKey());
        }
        //创建集合，存放byte
        List<Byte> list = new ArrayList<>();
        StringBuilder temp= new StringBuilder();
        for (int i = 0; i <sb.length() ; i++) {
            temp.append(sb.substring(i,i+1));
            Byte aByte = map.get(temp.toString());
            if(aByte!=null){
                list.add(aByte);
                temp.delete(0,temp.length());
            }
        }
       // System.out.println(list.toString());
        byte[] b= new byte[list.size()];
        for (int i = 0; i <list.size() ; i++) {
            b[i]=list.get(i);
        }
        return b;
    }

    /**
     * 将一个byte转成二进制的字符串
     *
     * @param flag 表示是否需要补高位，如果是true，需要补高位，否则不需要
     * @param b    待转的数据
     * @return
     */
    private static String byteToBitStr(boolean flag, byte b) {
        int temp = b;
        //如果是整数，我们换需要补高位
        if (flag) {
            temp |= 256;
        }
        String s = Integer.toBinaryString(temp);//返回的int类型-1的补码
        if (flag) {
            return s.substring(s.length() - 8);
        } else {
            return s;
        }

    }

    /**
     * 使用一个方法，将前面的方法封装起来
     *
     * @param contentBytes 原始的字符串对应的byte数组
     * @return 经过赫夫曼编码处理后的字节数组，压缩后的数组
     */
    private static byte[] huffmanZip(byte[] contentBytes) {
        //
        List<Node> nodes = getNodes(contentBytes);
        //根据nodes 创建的赫夫曼树
        Node huffmanTree = createHuffmanTree(nodes);
        //生成对应的赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTree);
        //根据赫夫曼编码对原始字节数组进行压缩
        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
        return huffmanCodeBytes;
    }


    /**
     * 编写一个方法，将字符串对应的byte[]数组，通过赫夫曼编码表，返回一个赫夫曼编码，压缩后的byte[]
     *
     * @param contentBytes 原始的字符串对应的byte数组
     * @param huffmanCodes 生成的赫夫曼编码
     * @return
     */
    private static byte[] zip(byte[] contentBytes, Map<Byte, String> huffmanCodes) {
        //1.利用huffmanCodes将contentBytes转成赫夫曼编码对应的字符串
        StringBuilder sb = new StringBuilder();
        //2.遍历contentBytes数组
        for (byte b : contentBytes) {
            sb.append(huffmanCodes.get(b));
        }
        //将字符串转成byte[]
        //获取
        int length;
        if (sb.length() % 8 == 0) {
            length = sb.length() / 8;
        } else {
            length = sb.length() / 8 + 1;
        }
        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[length];
        int index = 0;
        for (int i = 0; i < sb.length(); i += 8) {//因为每8位对应一个byte,所以步长+8
            String strByte;
            if (i + 8 > sb.length()) {
                //剩余的不够8位
                strByte = sb.substring(i);
            } else {
                strByte = sb.substring(i, i + 8);
            }
            //将strByte转成byte，放入到huffmanCodeBytes中
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    /**
     * @param bytes 字节数组
     * @return 返回Node列表
     */
    private static List<Node> getNodes(byte[] bytes) {
        //1.创建一个ArrayList
        List<Node> nodes = new ArrayList<>();
        //2.遍历bytes，存储每个byte出现的次数—>map
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                //map中换没有这个字符数据
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        //把每个键值对转成Node
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    /**
     * 创建list对应的赫夫曼数
     */
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //排序，从小到大
            Collections.sort(nodes);
            //取出第一颗最小的二叉树
            Node leftNode = nodes.get(0);
            //取出第二颗最小的二叉树
            Node rightNode = nodes.get(1);
            //创建一颗新的二叉树，他的根节点没有data,只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            //将处理后的这两个节点删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的节点加入到nodes
            nodes.add(parent);
        }
        //返回赫夫曼树的根节点
        return nodes.get(0);
    }

    /**
     * 前序遍历
     *
     * @param node
     */
    private static void preOrder(Node node) {
        if (node != null) {
            node.preOrder();
        } else {
            System.out.println("是空树");
        }
    }

    /**
     * 生成赫夫曼树对应的赫夫曼编码表
     * 思路：
     * 1.将赫夫曼编码表，存放在map<Byte,String,></>中
     * 2.在生成赫夫曼编码表时，需要去拼接路径，定义一个StringBuilder存储某个叶子节点的路劲
     */
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    static StringBuilder sb = new StringBuilder();

    /**
     * 得到赫夫曼编码（重载）
     *
     * @param node
     * @return
     */
    private static Map<Byte, String> getCodes(Node node) {
        if (node == null) {
            return null;
        }
        //处理root的左子树
        getCodes(node.left, "0", sb);
        //处理root的右子树
        getCodes(node.right, "1", sb);
        return huffmanCodes;
    }

    /**
     * 将传入的node节点的所有叶子节点的赫夫曼编码得到，放入到huffmanCodes中
     *
     * @param node 传入的节点
     * @param code 路径：左子节点是0,右子节点是1
     * @param sb   用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder(sb);
        //将传入的code加入到sb2
        sb2.append(code);
        if (node != null) { //如果node=null,不处理
            //判断当前的节点是叶子节点换是非叶子节点
            if (node.data == null) {
                //非叶子节点，递归处理
                //向左递归
                getCodes(node.left, "0", sb2);
                //向右递归
                getCodes(node.right, "1", sb2);
            } else {
                //说明是一个叶子节点
                huffmanCodes.put(node.data, sb2.toString());
            }
        }
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

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
