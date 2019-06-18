package com.ilike.linklist;

/**
 * 双向链表
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        System.out.println("双向链表的测试");
        //1.先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        //2.创建链表
        DoubleLinkedList linkList = new DoubleLinkedList();
        linkList.add(hero1);
        linkList.add(hero2);
        linkList.add(hero3);
        linkList.add(hero4);
        linkList.show();
        //修改测试
        HeroNode2 hero5 = new HeroNode2(4, "公孙胜", "入云龙");
        linkList.update(hero5);
        System.out.println("修改后的链表" );
        linkList.show();
        //测试删除
        linkList.delete(3);
        System.out.println("删除后的链表" );
        linkList.show();
        //测试按顺序插入
        HeroNode2 hero6 = new HeroNode2(3, "吴用", "智多星");
        linkList.addByOrder(hero6);
        HeroNode2 hero7 = new HeroNode2(5, "桑伟东", "程序员");
        linkList.addByOrder(hero7);
        System.out.println("顺序插入节点后的链表" );
        linkList.show();
    }
}

/**
 * 创建一个双向链表类
 */

class DoubleLinkedList{
    //先初始化一个头结点，头结点不要动
    private HeroNode2 head = new HeroNode2(0, "", "");

    /**
     * 获取单链表头结点
     * @return
     */
    public HeroNode2 getHead(){
        return head;
    }

    /**
     * 添加节点到链表
     * 当不考虑编号的顺序时
     * 1.找到当前列表的最后节点
     * 2.将最后节点的这个next指向这个新节点
     */
    public void add(HeroNode2 heroNode) {
        //因为head节点不能动，因此我们需要一个temp
        HeroNode2 temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后，就将temp后移
            temp = temp.next;
        }
        //当退出循环时，temp就指向了链表的最后
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre=temp;
    }

    /**
     * 第二种添加英雄的方式，根据排名将英雄插入到指定的位置
     * (如果有这个排名，则添加失败)
     */
    public void addByOrder(HeroNode2 heroNode) {
        HeroNode2 temp = head.next;
        boolean flag = false;//标识添加的编号是否存在，默认为false
        while (true) {
            if (temp == null) {
                //说明temp已经到链表的最后
                break;
            }
            if (temp.no < heroNode.no&&(temp.next==null||temp.next.no>heroNode.no)) {
                //位置找到了
                break;
            } else if (temp.no == heroNode.no) {
                //这个排名已经存在
                flag = true;
                break;
            }
            temp = temp.next;//后移
        }
        if (flag) {
            //说明编号存在
            System.out.printf("准备插入的英雄已的编号%d经存在", heroNode.no);
        } else {
            //插入到链表中temp的后边
            heroNode.pre  = temp ;
            heroNode.next= temp.next ;
            temp.next=heroNode;

        }
    }


    /**
     * 修改节点的信息，根据编号来修改，即NO编号不能改
     */
    public void update(HeroNode2 heroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        HeroNode2 temp = head.next;
        boolean flag = false;//标识是否找到该节点
        while (true) {
            if (temp == null) {
                break;//已经遍历完链表
            } else if (temp.no == heroNode.no) {
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否是否已经找到要修改的节点
        if (flag) {
            //找到了=
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        } else {
            //没找到
            System.out.printf("没有找到编号%d的节点，不能修改", heroNode.no);
        }
    }

    /**
     * 删除节点
     * 思路：
     * 1.对于双向链表，直接找到要删除的节点，自我删除即可
     */
    public void delete(int no) {
//判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        HeroNode2 temp = head.next;
        boolean flag = false;//标识是否找到该节点
        while (true) {
            if (temp == null) {
                break;//已经遍历完链表
            } else if (temp.no == no) {
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否是否已经找到要删除的节点
        if (flag) {
            //找到了，可以删除
            temp.pre.next=temp.next;
            if( temp.next!=null){
                temp.next.pre=temp.pre;
            }
        } else {
            //没找到
            System.out.printf("没有找到编号%d的节点，不能删除", no);
        }
    }

    /**
     * 显示链表
     */
    public void show() {
        //1.先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将next后移
            temp = temp.next;
        }
    }
}

//定义HeroNode,每个heroNode对象就是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    //指向下一个节点
    public HeroNode2 next;

    //指向前一个节点
    public HeroNode2 pre;

    /**
     * 构造器
     */
    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    /**
     * 为了显示方便，重写toString
     */
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' + '}';
    }
}