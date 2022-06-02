package labuladong.二叉树.二叉搜索树;

import labuladong.二叉树.TreeNode;

/**
 * 450.删除二叉搜索树中的结点，保证二叉搜索树性质不变
 * <p>
 * 步骤思路：
 * 1. 找到要删除的结点
 * 2. 因为删除结点时还要保持二叉搜索树的性质，所以根据要删除节点的孩子个数可以分三种情况处理：
 * （1）该节点只有左孩子：让左孩子顶替该节点的位置，删掉该节点
 * （2）该节点只有右孩子：让右孩子顶替该节点的位置，删掉该节点
 * （3）该节点有左右子树：将该节点的左孩子放在右孩子的最左结点的左子树上， 再把右孩子放在该节点的位置， 再把右孩子删掉。  (好像堆排序的过程)
 * <p>
 * 反思：一定要把所有情况都考虑全乎
 */
public class Solution450 {



    /**
     * 正确解法
     *
     * @param root
     * @param key
     * @return
     */

    public TreeNode deleteNode(TreeNode root, int key) {

        if (root == null) {
            return null;
        }

        if (key > root.val) {
            //去右子树删除，同时要把root.right对应的结点保存下来，使递归的两层之间的关联保存下 （就是指针的引用关系保留下）
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            //去左子树删除
            root.left = deleteNode(root.left, key);
        } else {
            //当前结点root就是要删除的结点

            //case1：要删除的结点只有右孩子，那就直接让右孩子返回，直接赋给上一级的结点（也就是父节点）的孩子 （看上面两个if下递归的值赋给父节点的孩子）
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            if (root.left != null && root.right != null) {
                //1.把该节点的左孩子放在右孩子的左子树的最下面
                TreeNode p = root.right;
                while (p.left != null) {
                    p = p.left;
                }
                p.left = root.left;
                //2.把该节点的右孩子放在该节点的位置上
                //理解这句root = root.right的赋值：是让root指针指向root.right结点 （改变的是指针的指向，不是树的结构）
                root = root.right;

            }
        }
        return root;
    }



    /**
     * 这种写法解法答案不对……XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
     * <p>
     * 原因：
     * 1.理解node = node.right 不是改变树结构，是node指针指向node.right结点 （二叉树基操：删除节点操作其实是改变指针的指向）
     * 2.还是递归 要多想想 递归问题 ， 要删除的结点，要改变该节点的父节点的指针指向，递归要存下父节点的指向 让返回
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode1(TreeNode root, int key) {

        //1.找到需要删除的结点
        TreeNode node = findNode(root, key);
        if (node == null) {
            //该树中没有值为key的结点，直接返回原树
            return root;
        }
        //2.删除该结点
        deleteNode(node);
        return root;
    }


    private TreeNode findNode(TreeNode root, int key) {

        if (root == null) {
            return null;
        }
        if (key == root.val) {
            return root;
        } else if (key > root.val) {
            return findNode(root.right, key);
        } else if (key < root.val) {
            return findNode(root.left, key);
        }
        return null;
    }

    private void deleteNode(TreeNode node) {

        if (node.right == null) {
            node = node.left;
            return;
        } else if (node.left == null) {
            node = node.right;
            return;
        } else {
            //1.把该节点的左孩子放在右孩子的左子树的最下面
            TreeNode p = node.right;
            while (p.left != null) {
                p = p.left;
            }
            p.left = node.left;
            node.left = null;
            //2.把该节点的右孩子放在该节点的位置上
            node = node.right;
            System.out.println(node.val);
            node.right = null;
        }
    }

    public static void main(String[] args) {
        Solution450 solution450 = new Solution450();
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(7);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node5;

        solution450.deleteNode(root, 3);
    }

}
