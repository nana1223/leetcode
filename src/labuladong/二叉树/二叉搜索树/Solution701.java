package labuladong.二叉树.二叉搜索树;

import labuladong.二叉树.TreeNode;

/**
 * 701. 二叉搜索树中的插入操作
 * <p>
 * 对数据结构的操作无非遍历 + 访问，遍历就是「找」，访问就是「改」。具体到这个问题，插入一个数，就是先找到插入位置，然后进行插入操作。
 * <p>
 * 一旦涉及「改」，就类似二叉树的构造问题，函数要返回 TreeNode 类型，并且要对递归调用的返回值进行接收。
 * <p>
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!接收递归的返回值很重要！！！
 */
public class Solution701 {

    public TreeNode insertIntoBST(TreeNode root, int val) {

        //找到空位置插入新结点 （这题不要求平衡 所以可以都插在叶子结点上 而不用调整树结构）
        if (root == null) {
            return new TreeNode(val);
        }

        if (val < root.val) {
            //!!!!!!涉及「改」，就类似二叉树的构造问题，函数要返回 TreeNode 类型，并且要【对递归调用的返回值进行接收】！！！
            root.left = insertIntoBST(root.left, val);
        }
        if (val > root.val) {
            root.right = insertIntoBST(root.right, val);
        }
        return root;

    }
}
