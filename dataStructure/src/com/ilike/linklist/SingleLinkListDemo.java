package com.ilike.linklist;

import java.util.Stack;

/**
 * 单链表
 */
public class SingleLinkListDemo {
    public static void main(String[] args) {
        //测试
        //1.先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        //2.创建链表
        SingleLinkList linkList = new SingleLinkList();
        //加入按照编号的顺序
      /*  linkList.addByOrder(hero1);
        linkList.addByOrder(hero4);
        linkList.addByOrder(hero2);
        linkList.addByOrder(hero3);*/
       linkList.add(hero1);
       linkList.add(hero2);
       linkList.add(hero3);
       linkList.add(hero4);
        linkList.show();
        System.out.println("逆序打印的结果");
        reversePrint(linkList.getHead());
      //  linkList.show();

     /*   linkList.show();
        System.out.println("修改后的链表");
        //测试修改节点的代码
        HeroNode heroNode = new HeroNode(2, "小卢", "玉麒麟++");
        linkList.update(heroNode);
        //3.显示链表
        linkList.show();
        //删除一个节点
     //   linkList.delete(1);
      //  linkList.delete(4);
//        linkList.delete(2);
//        linkList.delete(3);
        System.out.println("删除后的链表");
        linkList.show();
        //测试求单链表中有效节点的个数
        System.out.println(getLength(linkList.getHead()));
        //测试一下，得到倒数第1个元素
        System.out.println(findLastIndexNode(linkList.getHead(), 4));
        //测试一下，链表反转
        reverse(linkList.getHead());
        System.out.println("输出反转后的结果");
        linkList.show();*/
    }

    /**
     * 将单链表逆序打印
     *    利用栈这种数据结构，将各个节点压入栈中，然后利用栈的先进后出的原则，就实现了逆序打印的效果
     */
   public static void reversePrint(HeroNode head){
       //空链表直接返回
       if(head.next==null){
           return ;
       }
       Stack<HeroNode> stack = new Stack<>();
       HeroNode temp =head.next;
       while (temp!=null){
           stack.push(temp);
           //当前节点后移，就可以压入下一个节点
           temp=temp.next;
       }
       //将栈中的节点打印
       while (stack.size()>0){
           System.out.println(stack.pop());
       }
   }

    /**
     * 将单链表进行反转
     *
     */
    public static  void reverse(HeroNode head){
        //对单链表进行判断，如果为空，无需反转，直接返回
        if(head.next==null||head.next.next==null){
            return ;
        }
        //创建一个新的反转链表的头节点
        HeroNode reverseHead= new HeroNode(0,"","");
        //定义一个辅助的指针变量，帮助我们遍历原来的链表
        HeroNode temp=head.next;
        //指向当前节点的洗衣歌节点
        HeroNode next=null;
        //遍历原来的链表，每遍历一个，便将其摘下来，放在反转链表的最前边
        while (temp!=null){
            //先暂时保存当前节点的下一个节点
            next= temp.next;
            //让当前节点的下一个节点指向新的链表的最前端
            temp.next=reverseHead.next;
            //将当前节点连接到新的链表上
            reverseHead.next=temp;
            //当前节点后移
            temp=next;

        }
        //将head.next指向reverseHead.next
        head.next=reverseHead.next;
    }


    /**
     * 获取单链表中倒数第k个节点、
     *   思路：
     *       1.编写一个方法接受head节点，同时接受一个index
     *       2.index表示是倒数第index个节点
     *       3.先把链表从头到尾遍历一下，得到链表的总的长度getLength
     *       4.得到size，从链表的第一个开始遍历，遍历（size-index）个，就可以得到
     *       5.如果找到返回该节点，找不到就返回null
     */
   public static HeroNode findLastIndexNode(HeroNode head,int index){
       if(head.next==null){
           return null;
       }
       //第一次遍历，得到节点的个数
       int size = getLength(head);
       //第二次遍历，遍历到size-index位置，就是倒数index的节点
       //先做一次校验
       if(index<0||index>size){
           return null;
       }
       HeroNode currentNode=head.next;
       for (int i=0;i<size-index;i++){
           currentNode=currentNode.next;
       }
       return currentNode;
   }

    /**
     * 获取单链表的节点的个数（如果是带头结点的链表，不统计头结点）
     *
     * @param head 是链表的头结点
     * @return 返回有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        //定义一个辅助变量
        HeroNode temp = head.next;
        int sum = 0;
        while (temp!= null) {
            sum++;
            temp = temp.next;
        }
        return sum;

    }
}

//定义SingleLinkList管理我们的英雄9
class SingleLinkList {
    //先初始化一个头结点，头结点不要动
    private HeroNode head = new HeroNode(0, "", "");

    /**
     * 获取单链表头结点
     * @return
     */
    public HeroNode getHead(){
        return head;
    }

    /**
     * 添加节点到单项链表
     * 当不考虑编号的顺序时
     * 1.找到当前列表的最后节点
     * 2.将最后节点的这个next指向这个新节点
     */
    public void add(HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                temp.next = heroNode;
                break;
            }
            //如果没有找到最后，就将temp后移
            temp = temp.next;
        }
        //当退出循环时，temp就指向了链表的最后
        temp.next = heroNode;
    }

    /**
     * 第二种添加英雄的方式，根据排名将英雄插入到指定的位置
     * (如果有这个排名，则添加失败)
     */
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;//标识添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {
                //说明temp已经到链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) {
                //位置找到了
                break;
            } else if (temp.next.no == heroNode.no) {
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
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 修改节点的信息，根据编号来修改，即NO编号不能改
     */
    public void update(HeroNode heroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        HeroNode temp = head.next;
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
     * 1.head节点不能动，需要temp辅助节点，找到待删除节点的前一个节点
     * 2.在比较的时候，是temp.next=temp.next.next
     */
    public void delete(int no) {
//判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        HeroNode temp = head;
        boolean flag = false;//标识是否找到该节点
        while (true) {
            if (temp.next == null) {
                break;//已经遍历完链表
            } else if (temp.next.no == no) {
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否是否已经找到要删除的节点
        if (flag) {
            //找到了，可以删除
            temp.next = temp.next.next;
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
        HeroNode temp = head.next;
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
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    //指向下一个节点
    public HeroNode next;

    /**
     * 构造器
     */
    public HeroNode(int no, String name, String nickName) {
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