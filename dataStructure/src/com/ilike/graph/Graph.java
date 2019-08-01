package com.ilike.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 图
 */
public class Graph {


    public static void main(String[] args) {
        //测试图的创建
        int n = 5;//节点的个数
       String[] vertexValue = {"A", "B", "C", "D", "E"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环添加顶点
        for (int i = 0; i < vertexValue.length; i++) {
            graph.insertVertex(vertexValue[i]);
        }
        //添加边  A-B,A-C,B-C ,B-D,B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        //显示领结矩阵
        graph.showGraph();
        //测试一把，我们的dfs遍历是否ok
     /*   System.out.println("深度优先遍历");
        graph.dfs();*/
        System.out.println("广度优先遍历");
        graph.bfs();

    }

    /**
     * 存储顶点集合
     */
    private List<String> vertexList;
    /**
     * 存储图对应的领结矩阵
     */
    private int[][] edges;
    /**
     * 表示边的数目
     */
    private int numOfEdges;
    /**
     * 某个节点是否已访问
     */
    private boolean[] isVisited;

    /**
     * 返回图中节点的个数
     *
     * @return
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * 返回图中边的个数
     *
     * @return
     */
    public int getnumOfEdges() {
        return numOfEdges;
    }

    /**
     * 返回顶点下标对应的值
     *
     * @param i 顶点的下标
     * @return
     */
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    /**
     * 返回两个顶点对应的权值
     *
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    /**
     * 显示图对应的矩阵
     */
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 广度优先遍历算法
     */
    private void bfs(boolean[] isVisited, int i){
        //队列的头结点对应的下标
       int u=0;
       //领结节点的下标
       int w=0;
       //队列，记录节点访问的顺序
        LinkedList<Integer> queue = new LinkedList<>();
        //访问节点
        System.out.print(getValueByIndex(i)+"->");
        //标记为已访问
        isVisited[i]=true;
       //将节点加入队列
        queue.addLast(i);

        while (!queue.isEmpty()){
            //取出队列的头结点
            u = queue.removeFirst();
            //得到第一个领节点的下标
            w=getFirstNeighbor(u);
            while (w!=-1){
                //是否访问过
                if(!isVisited[w]){
                    System.out.print(getValueByIndex(w)+"->");
                    //标记已访问
                    isVisited[w]=true;
                    //加入队列
                    queue.addLast(w);
                }
                //以u为前驱点，找w后面的下一个领节点
                w=getNextNeighbor(u,w);
            }

        }

    }

    /**
     * 遍历所有的节点，进行广度优先搜索
     */
    public void bfs(){
        for (int i = 0; i <getNumOfVertex() ; i++) {
            if(!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }




    /**
     * 深度优先遍历算法
     * i 第一次就是0
     */
    private void dfs(boolean[] isVisited, int i) {
        //首先我们访问该节点，输出
        System.out.print(getValueByIndex(i) + "->");
        //将这个节点设置为已经访问
        isVisited[i] = true;
        //查找节点i的第一个领结节点w
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            //如果w节点已经被访问
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 对dfs进行重载，遍历我们所有的节点，并进行dfs
     */
    public void dfs() {
       //遍历所有的节点，进行dfs[回溯]
        for (int i = 0; i <getNumOfVertex() ; i++) {
            if(!isVisited[i]){
                dfs(isVisited,i);
            }
        }

    }

    /**
     * 构造方法
     *
     * @param n
     */
    public Graph(int n) {
        //初始化矩阵edges和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];

    }

    /**
     * 根据前一个领结节点的下标来获取下一个领结节点
     *
     * @param v1
     * @param v2
     * @return
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 得到第一个领结节点的下标w
     *
     * @return 如果存在就返回对应的下标，否则返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 插入节点
     *
     * @param vertex
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     点的下标即是第几个顶点
     * @param v2     第二个顶点对应的下标
     * @param weight 权值
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }


}
