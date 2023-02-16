package jianzhi_offer;

import labuladong.二叉树.TreeNode;

/**
 * 剑指 Offer 28. 对称的二叉树
 * <p>
 * 要和镜像一样
 * <p>
 * 思路一：把镜像的二叉树造出来然后再两个一起遍历看是否一样
 * 思路二：能否在遍历的过程就看呢？
 * <p>
 * 【对称二叉树的定义】：
 * 对于树中任意两个对称节点L和R，一定有：
 * 1.L和R的值相等
 * 2.L的左孩子和R的右孩子 值相等
 * 3.L的右孩子和R的左孩子 值相等
 * <p>
 * 所以，从顶至底递归，判断每对节点是否对称，即可判断是否为对称二叉树
 */
public class Solution28 {
    public boolean isSymmetric(TreeNode root) {

        if (root == null) {
            return true;
        }

        //对于两个位置对称的节点L和R，判断是否对称
        return recur(root.left, root.right);
    }

    private boolean recur(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null && rightNode == null) {
            return true;
        }
        if (leftNode == null || rightNode == null || leftNode.val != rightNode.val) {
            return false;
        }
        //再判断孩子
        return recur(leftNode.left, rightNode.right) && recur(leftNode.right, rightNode.left);
    }


}
