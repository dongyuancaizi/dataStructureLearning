package com.ilike.stack;

import java.util.Scanner;

public class ArrayStackDemo {

    public static void main(String[] args) {
        //测试
        //1.先创建一个栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;//控制是否退出
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show:表示显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:添加数据");
            System.out.println("pop:从栈取出数据");
            System.out.println("请输入你的选择");
            key=scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                case "push":
                    System.out.println("请输入一个数：");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据%d\n", res);

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

/**
 * 定义一个ArrayStack表示栈
 */
class ArrayStack{
    /**
     * 栈的大小
     */
    private int maxSize;
    /**
     * 数组模拟栈，数据放在该数组中
     */
    private int [] stack;
    /**
     * 表示栈顶，初始化为-1
     */
    private int top=-1;

    public ArrayStack(int maxSize){
        this.maxSize=maxSize;
        stack=new int[maxSize];
    }

    /**
     * 栈是否满
     * @return
     */
    public boolean isFull(){
        return top==maxSize-1;
    }

    /**
     * 栈是否是空
     * @return
     */
    public boolean isEmpty(){
        return top==-1;
    }

    /**
     * 入栈
     * @param value
     */
    public void push(int value){
      //判断栈是否满
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top]=value;
    }

    /**
     * 出栈
     */
    public int pop(){
       //判断栈是否已空
       if(isEmpty()) {
          //抛出异常
           throw new RuntimeException("栈空，没有数据");
       }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 遍历栈
     * 遍历栈需要从栈顶开始
     */
    public void list(){
        if(isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >=0 ; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }

    }
}
