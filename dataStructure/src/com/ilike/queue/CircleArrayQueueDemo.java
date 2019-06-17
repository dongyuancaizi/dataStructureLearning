package com.ilike.queue;

import java.util.Scanner;

/**
 * 环形队列的实现
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
      //测试
        System.out.println("测试数组模拟环形队列的案例");
        //1.初始化队列
        CircleArrayQueue arrayQueue = new CircleArrayQueue(4);//队列的有效数据只有3个
        //接受用户的输入
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头数据");
            //接受一个字符
            key = scanner.next().charAt(0);
            switch (key) {
                case 's'://展示队列数据
                    arrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a'://添加数据
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g'://取数据
                    try {
                        int result = arrayQueue.getQueue();
                        System.out.println("取出的数据是：" + result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h'://查看队列头的数据
                    try {
                        int result = arrayQueue.headQueue();
                        System.out.println("队列头的数据是：" + result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }

}

class CircleArrayQueue {
    /**
     * 数组的最大容量
     */
    private int maxSize;
    /**
     * 队列头，指向队列第一个元素，初始值为0
     */
    private int front;
    /**
     * 队列尾部，指向队列最后一个元素的最后一个位置，初始值为0
     */
    private int rear;
    /**
     * 该数组用于存放数据，模拟队列
     */
    private int[] arr;

    /**
     * 队列的构造器
     */
    public CircleArrayQueue(int arrayMaxSize) {
        maxSize = arrayMaxSize;
        arr = new int[arrayMaxSize];
    }

    /**
     * 判断队列是否满
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 判断队列是否为空
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 添加数据到队列
     */
    public void addQueue(int value) {
        if (isFull()) {
            System.out.println("队列满，不能加入数据");
            return;
        }
        //直接将数据加入
        arr[rear] = value;
        //将rear后移
        rear=(rear+1)%maxSize;
    }

    /**
     * 数据出队列
     */
    public int getQueue() {
        //判断队列是否空
        if (isEmpty()) {
            //通过抛异常来处理
            throw new RuntimeException("队列空，不能取数据");
        }
       //这里需要分析出front是指向队列的第一个元素
        //1.先把front的值保存到一个临时变量
        int value =arr[front];
        //2.将front后移，考虑取模
        front=(front+1)%maxSize;
        //3.将临时保存的变量返回
       return value;

    }
    /**
     * 显示队列的所有数据
     */
    public void showQueue() {
        //遍历
        if (isEmpty()) {
            System.out.println("队列空，没有数据");
            return;
        }
        //思路：从front开始遍历
        for ( int i=front; i < front+size(); i++) {
            System.out.printf("arr[%d]=%d\n", i%maxSize, arr[i%maxSize]);
        }
    }
    /**
     * 求出当前队列有效数据的个数
     */
    public int size(){
        return (rear+maxSize-front)%maxSize;
    }

    /**
     * 显示队列的头数据,注意，不是取出数据
     */
    public int headQueue() {
        //判断
        if (isEmpty()) {
            //通过抛异常来处理
            throw new RuntimeException("队列空，没有数据");
        }
        return arr[front];
    }
}

