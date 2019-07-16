package com.ilike.hashtab;

import java.util.Scanner;

/**
 * 哈希表
 */
public class HashTabDemo {

    public static void main(String[] args) {

        //1.创建hashTab
        HashTab hashTab = new HashTab(7);
        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find:查找雇员");
            System.out.println("exit:退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("请输入id");
                    int id = scanner.nextInt();
                    System.out.println("请输入名字");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id, name);
                    //将雇员添加到哈希表
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入id");
                     id = scanner.nextInt();
                    hashTab.find(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }

    }
}

/**
 * 表示一个雇员
 */
class Emp {
    public int id;

    public String name;

    public Emp next; //默认为null

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

/**
 * 创建hash表，管理多条链表
 */
class HashTab {
    /**
     * 表示共有多少条链表
     */
    private int size;
    private EmpLinkedList[] empLinkedListArray;

    /**
     * 构造器
     *
     * @param size
     */
    public HashTab(int size) {
        //初始化链表
        empLinkedListArray = new EmpLinkedList[size];
        this.size = size;
        //分别初始化每一条链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    /**
     * 添加
     *
     * @param emp
     */
    public void add(Emp emp) {
        //根据员工的id,得到该员工应该添加到那条链表
        int empLinkedListNo = hashFun(emp.id);
        //将emp添加到对一个的链表
        empLinkedListArray[empLinkedListNo].add(emp);
    }


    /**
     * 遍历所有的链表，遍历hashTab
     */
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    /**
     * 编写散列函数，使用简单的取模法
     */
    public int hashFun(int id) {
        return id % size;
    }

    /**
     * 根据输入的id,查找雇员
     */
    public void find(int id){
        //根据员工的id,得到该员工应该添加到那条链表
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].find(id);
        if(emp!=null){
            System.out.printf("在第%d条链表中找到该雇员id=%d\n",(empLinkedListNo+1),id);
        }else{
            System.out.println("没有找到该雇员");
        }
    }

}


/**
 * 创建EmpLinkedList，表示链表
 */
class EmpLinkedList {

    //头指针，指向第一个emp,因此我们这个的链表的head是直接指向第一个Emp
    private Emp head;//默认为null

    /**
     * 添加雇员到链表
     * 说明：
     * 1.当添加雇员时，id是自增长的，即id的分配总是从小到大
     * 2.因此我们将雇员加入到本链表的最后
     *
     * @param emp
     */
    public void add(Emp emp) {
        //如果是添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个雇员，使用一个辅助的指针，帮助定位到最后
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                //说明到链表最后
                break;
            }
            curEmp = curEmp.next;//后移
        }
        //退出时直接将链表加入到最后
        curEmp.next = emp;
    }

    /**
     * 遍历链表的雇员信息
     */
    public void list(int no) {
        if (head == null) {
            //说明链表为空
            System.out.println("第" + (no + 1) + "条链表为空");
            return;
        }
        System.out.print("第" + (no + 1) + "条链表为：");
        Emp curEmp = head;
        while (true) {
            System.out.printf("=> id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                //说明curEmp已经是最后节点
                break;
            }
            curEmp = curEmp.next;//后移
        }
        System.out.println();
    }

    /**
     * 根据id查找雇员
     * 说明：
     * 如果查找到，就返回Emp，没找到，返回null
     *
     * @param id
     * @return
     */
    public Emp find(int id) {
        //判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp currEmp = head;
        while (true) {
            if (currEmp.id == id) {//找到
                //这时currEmp指向你要找的雇员
               break;
            }
            //退出
            if(currEmp.next==null){
                //遍历当前链表没有找到该雇员
                currEmp=null;
                break;
            }
            currEmp=currEmp.next;//后移
        }
        return currEmp;
    }

}
