package labuladong.二叉树.二叉搜索树;

import labuladong.二叉树.TreeNode;

/**
 * 98.验证是否是有效的二叉树
 * 思路：通过中序遍历看是不是升序顺序，
 * 中序遍历时，判断当前节点是否大于中序遍历的前一个节点，如果大于，说明满足 BST，继续遍历；否则直接返回 false。
 */
public class Solution98 {

    Long preVal = Long.MIN_VALUE;

    boolean res = true;

    public boolean isValidBST(TreeNode root) {
        traversal(root);
        return res;
    }

    private void traversal(TreeNode root) {

        if (root == null) {
            return;
        }

        traversal(root.left);
        if (root.val <= preVal) {
            res = false;
            return;
        }
        preVal = (long) root.val;

        traversal(root.right);
    }
}
