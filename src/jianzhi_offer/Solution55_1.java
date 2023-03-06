package jianzhi_offer;

import labuladong.二叉树.TreeNode;

/**
 * 剑指 Offer 55 - I. 二叉树的深度
 * <p>
 * 【二叉树求深度】：Math.max(左子树深度, 右子树深度) + 1（当前节点算一层）
 */
public class Solution55_1 {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }


}
