package com.ilike.linklist;

/**
 * 约瑟夫问题
 */
public class Josepfu {

    public static void main(String[] args) {
        //测试

        //1.构建环形链表
        CircleSingleLinkedList linkedList = new CircleSingleLinkedList();
        linkedList.addBoy(25);
        //2.显示链表
        linkedList.showBoy();
        //3.测试小孩出圈
        linkedList.countBoy(1,2,25);
    }

}

/**
 * 单向环形的链表
 */
class CircleSingleLinkedList {
    /**
     * 创建一个first节点，当前没有编号
     */
    private Boy first = null;

    /**
     * 添加小孩，构建成环形的链表
     */
    public void addBoy(int nums) {
        //对nums校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        //辅助变量，帮助构建环形链表
        Boy curBoy = null;
        //使用for循环来创建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号，创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                //构成环
                first.setNext(first);
                //让curBoy指向第一个小孩
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    /**
     * 遍历当前环形链表
     */
    public void showBoy() {
        if (first == null) {
            System.out.println("没有一个小孩");
            return;
        }
        //使用一个辅助指针完成遍历
        Boy curBoy = first;
        do {
            System.out.println(curBoy.toString());
            curBoy = curBoy.getNext();
        } while (curBoy != first);
    }

    /**
     * 根据用户的输入，计算小孩出圈的顺序
     *
     * @param startNo  从哪个小孩开始数数
     * @param countNum 数几下
     * @param nums     有多少个小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //1.对数据校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数有误，请重新输入");
            return;
        }
        //创建辅助指针，帮助完成小孩出圈

        //辅助指针helper应该指向环形链表的最后一个节点
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                //说明helper指向了最后一个节点
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first和helper移动k-1次
        for (int i = 0; i <startNo-1 ; i++) {
            first=first.getNext();
            helper=helper.getNext();
        }
        //小孩报数时，让first和helper指针同时移动m-1次，然后出圈
        //这是一个循环的操作
        while (true){
            if(helper==first){
                //圈中只有一个节点
                break;
            }
            //让first和helper指针同时移动m-1次
            for (int i = 0; i <countNum-1 ; i++) {
                first=first.getNext();
                helper=helper.getNext();
            }
            //这时first指向的节点，就是出圈的节点
            System.out.printf("出圈的小孩编号是%d\n",first.getNo());
            //让first指向的小孩出圈
            first=first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d\n",first.getNo());


    }

}


/**
 * 先创建一个boy类，表示一个节点
 */
class Boy {

    /**
     * 编号
     */
    private int no;
    /**
     * 指向下一个节点
     */
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
