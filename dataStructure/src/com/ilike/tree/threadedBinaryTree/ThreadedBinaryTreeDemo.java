package com.ilike.tree.threadedBinaryTree;

/**
 * 线索化二叉树
 */
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        //测试中序线索化二叉树
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jak");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");
        //二叉树，后面我们递归创建，现在简单处理，手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        //测试线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();
        //测试：以10号节点测试
        HeroNode left = node5.getLeft();
        System.out.println(left);
       HeroNode right = node5.getRight();
        System.out.println(right);

    }
}

/**
 * 实现了线索化功能的二叉树
 */
class ThreadedBinaryTree {
    private HeroNode root;


    /**
     * 为了实现线索化，需要创建要给指向当前节点的前驱节点的指针
     * 在递归进行线索化时，总是保存前一个节点
     */
    private HeroNode pre;


    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void threadedNodes(){
        this.threadedNodes(root);
    }

    /**
     * 对二叉树进行中序线索化
     *
     * @param heroNode 当前需要线索化的节点
     */
    public void threadedNodes(HeroNode heroNode) {
        //如果heroNode=null,不能线索化
        if (heroNode == null) {
            return;
        }
        //先线索化左子树
        threadedNodes(heroNode.getLeft());
        //线索化当前节点

        //先处理当前节点的前驱节点
        if (heroNode.getLeft() == null) {
            //让当前节点的左指针指向前驱
            heroNode.setLeft(pre);
            //修改当前节点的左指针的类型,指向前驱节点
            heroNode.setLeftType(1);
        }
        //处理后继节点
        if (pre != null && pre.getRight() == null) {
            //让前驱节点的右指针指向当前节点
            pre.setRight(heroNode);
            //修改前驱节点的右指正类型
            pre.setRightType(1);
        }
        //每处理一个节点后，让当前节点是下一个节点的前驱
        pre=heroNode;


        //再线索化右子树
        threadedNodes(heroNode.getRight());
    }

    /**
     * 删除节点
     *
     * @param no 要删除的节点
     */
    public void delNode(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.out.println("空树，不能删除");
        }
    }


    /**
     * 前序遍历
     */
    public void preOrder() {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 前序遍历查找
     */
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }


    /**
     * 中序遍历查找
     */
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }


    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (root != null) {
            root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 后序遍历查找
     */
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}


/**
 * 先创建heroNode节点
 */
class HeroNode {

    private int no;

    private String name;

    private HeroNode left;//默认null

    private HeroNode right;//默认null
    /**
     * 说明：
     * 1.如果leftType=0，表示指向左子树，leftType=1,表示指向前驱节点
     * 2.如果rightType=0，表示指向右子树，rightType=1,表示指向后继节点
     */
    private int leftType;

    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    /**
     * 递归删除节点
     *
     * @param no 要删除的节点
     */
    public void delNode(int no) {
        //判断左子节点
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        //判断右子节点
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        //向左子树递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        //向右子树递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }

    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);//输出父节点
        //递归左子树
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归右子树
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 前序遍历查找
     *
     * @param no 查找no
     */
    public HeroNode preOrderSearch(int no) {
        System.out.println(111);
        //判断当前节点是不是
        if (this.no == no) {
            return this;
        }
        //左边递归查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            //左子树找到了
            return resNode;
        }
        //右边递归查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 中序遍历查找
     *
     * @param no 查找no
     */
    public HeroNode infixOrderSearch(int no) {
        //左边递归查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            //左子树找到了
            return resNode;
        }
        System.out.println(222);
        //判断当前节点是不是
        if (this.no == no) {
            return this;
        }
        //右边递归查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 后序遍历查找
     *
     * @param no 查找no
     */
    public HeroNode postOrderSearch(int no) {
        //左边递归查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            //左子树找到了
            return resNode;
        }
        //右边递归查找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            //右子树找到了
            return resNode;
        }
        System.out.println(333);
        //判断当前节点是不是
        if (this.no == no) {
            return this;
        }
        return resNode;
    }


    /**
     * 中序遍历
     */
    public void infixOrder() {
        //递归左子树
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);//输出父节点
        //递归右子树
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 前序遍历
     */
    public void postOrder() {
        //递归左子树
        if (this.left != null) {
            this.left.postOrder();
        }
        //递归右子树
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);//输出父节点
    }
}
