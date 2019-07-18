package com.ilike.tree;

/**
 * 二叉树
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {
        //先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        //说明：我们先手动创建二叉树，后面我们以递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);
      /*  //前序遍历
        System.out.println("前序遍历:");//1,2,3,4
        binaryTree.preOrder();
        System.out.println("中序遍历:");//2,1,3,4
        binaryTree.infixOrder();
        System.out.println("后序遍历:");//2,3,4,1
        binaryTree.postOrder();

        //前序遍历查找
        System.out.println("前序遍历查找");
        HeroNode heroNode = binaryTree.preOrderSearch(5);
        System.out.println(heroNode);
        //中序遍历查找
        System.out.println("中序遍历查找");
        heroNode = binaryTree.infixOrderSearch(5);
        System.out.println(heroNode);
        //后序遍历查找
        System.out.println("后序遍历查找");
        heroNode = binaryTree.postOrderSearch(5);
        System.out.println(heroNode);*/
      //删除前
        System.out.println("删除前，前序遍历");
        binaryTree.postOrder();
        //binaryTree.delNode(5);
        binaryTree.delNode(3);
        System.out.println("删除后，前序遍历");
        binaryTree.postOrder();
    }
}

/**
 * 定义BinaryTree
 */
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /**
     * 删除节点
     * @param no 要删除的节点
     */
    public void delNode(int no){
        if(root!=null){
            if(root.getNo()==no){
                root=null;
            }else{
                root.delNode(no);
            }
        }else{
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
        if(this.right!=null&&this.right.no==no){
            this.right=null;
            return;
        }
        //向左子树递归删除
        if(this.left != null){
            this.left.delNode(no);
        }
        //向右子树递归删除
        if(this.right != null){
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
