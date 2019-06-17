package com.ilike.queue;

import java.util.Scanner;

/**
 * 数组实现队列
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        //测试队列
        //1.初始化队列
        ArrayQueue arrayQueue = new ArrayQueue(3);
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

//使用数组模拟队列--编写一个ArrayQueue的类

class ArrayQueue {
    /**
     * 数组的最大容量
     */
    private int maxSize;
    /**
     * 队列头
     */
    private int front;
    /**
     * 队列尾部
     */
    private int rear;
    /**
     * 该数组用于存放数据，模拟队列
     */
    private int[] arr;

    /**
     * 队列的构造器
     */
    public ArrayQueue(int arrayMaxSize) {
        maxSize = arrayMaxSize;
        arr = new int[arrayMaxSize];
        //指向队列头部，分析出front是指向队列头部前一个位置
        front = -1;
        //指向队列尾部，指向队列尾的数据(就是队列最后一个数据)
        rear = -1;
    }

    /**
     * 判断队列是否满
     */
    public boolean isFull() {
        return rear == maxSize - 1;
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
        rear++;//让rear后移
        arr[rear] = value;
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
        front++;//让front后移
        return arr[front];
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
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
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
        return arr[front + 1];
    }
}
