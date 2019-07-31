package com.ilike.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 图
 */
public class Graph {


    public static void main(String[] args) {
       //测试图的创建
        int n=5;//节点的个数
        String [] vertexValue={"A","B","C","D","E"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环添加顶点
        for (int i = 0; i < vertexValue.length; i++) {
            graph.insertVertex(vertexValue[i]);
        }
        //添加边  A-B,A-C,B-C ,B-D,B-E
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        //显示领结矩阵
        graph.showGraph();


    }

    /**
     * 存储顶点集合
     */
    private List<String> vertexList;
    /**
     * 存储图对应的领结矩阵
     */
    private int [][] edges;
    /**
     * 表示边的数目
     */
    private int numOfEdges;

    /**
     * 返回图中节点的个数
     * @return
     */
    public int getNumOfVertex(){
     return vertexList.size();
    }

    /**
     * 返回图中边的个数
     * @return
     */
    public int getnumOfEdges(){
        return numOfEdges;
    }

    /**
     * 返回顶点下标对应的值
     * @param i 顶点的下标
     * @return
     */
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    /**
     * 返回两个顶点对应的权值
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1,int v2){
      return edges[v1][v2];
    }

    /**
     * 显示图对应的矩阵
     */
    public void showGraph(){
        for (int [] link:edges) {
            System.out.println(Arrays.toString(link));
        }
    }


    /**
     * 构造方法
     * @param n
     */
    public Graph(int n) {
        //初始化矩阵edges和vertexList
        edges = new int[n][n];
        vertexList=new ArrayList<>(n);
        numOfEdges=0;

    }

    /**
     * 插入节点
     * @param vertex
     */
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * 添加边
     * @param v1 点的下标即是第几个顶点
     * @param v2 第二个顶点对应的下标
     * @param weight 权值
     */
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2]=weight;
        edges[v2][v1]=weight;
        numOfEdges++;
    }


}
