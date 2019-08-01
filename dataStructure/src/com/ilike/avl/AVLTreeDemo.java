package com.ilike.avl;

/**
 * AVL树
 */
public class AVLTreeDemo {

    public static void main(String[] args) {
      //int [] arr={4,3,6,5,7,8};
        int [] arr={10,12,8,9,7,6};
      AVLTree avlTree = new AVLTree();
        for (int i = 0; i <arr.length ; i++) {
            avlTree.add( new Node(arr[i]));
        }
        System.out.println("中序遍历");
        avlTree.infixOrder();
        System.out.println("处理后");
        System.out.println("树的高度："+avlTree.root.height());//4
        System.out.println("左子树的高度："+avlTree.root.left.height());//1
        System.out.println("右子树的高度："+avlTree.root.right.height());//3
        System.out.println(avlTree.root);
    }

}

class AVLTree{
    Node root;


    /**
     * @param node 传入的节点，当做二叉排序树的根节点
     * @return 返回以node为根节点的二叉排序树的最小节点的值
     */
    public int deleteRightTree(Node node) {
        Node target = node;
        //循环查找左节点就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        //target指向了最小节点
        //删除最小节点
        deleteNode(target.value);
        return target.value;
    }

    public void deleteNode(int value) {
        if (root == null) {
            return;
        } else {
            //1.先找到要删除的节点targetNode
            Node targetNode = search(value);
            //没有找到要删除的节点
            if (targetNode == null) {
                return;
            }
            //如果我们发现当前这课二叉排序树只有一个节点
            if (root.left == null && root.right == null) {
                root = null;
            }
            //去查找targetNode的父节点
            Node parentNode = searchParent(value);
            //如果要删除的节点是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode是父节点的左子节点换是右子节点
                if (parentNode.left != null && parentNode.left.value == value) {
                    //是左子节点
                    parentNode.left = null;
                } else if (parentNode.right != null && parentNode.right.value == value) {
                    //是右子节点
                    parentNode.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                //有两颗子树的节点
                int minValue = deleteRightTree(targetNode.right);
                targetNode.value = minValue;
            } else {
                //有一颗子树的节点
                //如果targetNode有左子节点
                if (targetNode.left != null) {
                    if (parentNode != null) {
                        if (parentNode.left.value == value) {
                            parentNode.left = targetNode.left;
                        } else {
                            parentNode.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {
                    if (parentNode != null) {
                        //如果targetNode有右子节点
                        if (parentNode.left.value == value) {
                            parentNode.left = targetNode.right;
                        } else {
                            parentNode.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }


    /**
     * 查找节点
     *
     * @param value
     * @return
     */
    public Node search(int value) {
        if (root == null) {
            return null;
        }
        return root.search(value);
    }

    /**
     * 查找父节点
     *
     * @param value
     * @return
     */
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        }
        return root.searchParent(value);
    }

    /**
     * 添加节点
     *
     * @param node
     */
    public void add(Node node) {
        if (root == null) {
            root = node;//如果root为空，直接让root指向node
        } else {
            root.add(node);
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("树为空，不能遍历");
        }
    }

}


/**
 * 创建node节点
 */
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 左旋转
     */
    public void leftRotate(){
        //创建新的节点
        Node newNode = new Node(this.value);
        //把新的节点的左子树，设置成当前节点的左子树
        newNode.left=this.left;
        //把新的节点的右子树设置成当前节点的右子树的左子树
        newNode.right=this.right.left;
        //把当前节点的值替换成右子节点的值
        this.value=this.right.value;
        //把当前节点的右子树设置成当前节点右子树的右子树
        this.right=this.right.right;
        //把当前节点的左子节点设置成新的节点
        this.left=newNode;
    }

    /**
     * 右旋转
     */
    public void rightRotate(){
        Node newNode = new Node(value);
        newNode.right=right;
        newNode.left=left.right;
        value=left.value;
        left=left.left;
        right=newNode;

    }


    /**
     * 返回左子树的高度
     */
    public int leftHeight(){
        if(left==null){
            return 0;
        }
        return left.height();
    }

    /**
     * 返回左子树的高度
     */
    public int rightHeight(){
        if(right==null){
            return 0;
        }
        return right.height();
    }

    /**
     * 获取树的高度
     * @return
     */
    public int height(){
       return Math.max(left==null?0:left.height(),right==null?0:right.height())+1;
    }


    /**
     * 查找要删除节点的父节点
     *
     * @param value 要删除节点的值
     * @return 返回要删除节点的父节点，如果没有返回null
     */
    public Node searchParent(int value) {
        //如果当前节点就是要删除节点的父节点，就返回
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果要查找的值比当前节点的值小，并且当前节点的左子节点不为空
            if (this.value > value && this.left != null) {
                return this.left.searchParent(value);//向左子树递归查找
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);//向右子树递归查找
            } else {
                return null;//没有找到父节点
            }
        }

    }

    /**
     * 查找要删除的节点
     *
     * @param value 要找的节点的值
     * @return 找到返回节点，没找到返回null
     */
    public Node search(int value) {
        if (this.value == value) {
            return this;
        } else if (value < this.value) {
            //向左子树递归查找
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            //向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 添加节点（需要满足二叉排序树）
     *
     * @param node
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }
        //判断传入的节点的值，和当前子树的根节点的值得关系
        if (node.value < this.value) {
            if (this.left == null) {
                //如果当前节点左子节点为空
                this.left = node;
            } else {
                //递归的向左子树添加
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                //如果当前节点右子节点为空
                this.right = node;
            } else {
                //递归的向右子树添加
                this.right.add(node);
            }
        }
        //当添加完一个节点后，右子树的高度-左子树的高度 > 1,左旋转
        if(rightHeight()-leftHeight()>1){
          this.leftRotate();//左旋转
        }
        //当添加完一个节点后，左子树的高度-右子树的高度 > 1,右旋转
        if(leftHeight()-rightHeight()>1){
            this.rightRotate();//右旋转
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}