package labuladong.二叉树.二叉搜索树;

import labuladong.二叉树.TreeNode;

/**
 * 700. 二叉搜索树中的搜索
 */
public class Solution700 {

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (val < root.val) {
            return searchBST(root.left, val);
        } else if (val > root.val) {
            return searchBST(root.right, val);
        } else {
            return root;
        }
    }
}
