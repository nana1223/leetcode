package jianzhi_offer;

import labuladong.二叉树.TreeNode;

/**
 * 剑指 Offer 55 - II. 平衡二叉树
 * <p>
 * 判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树
 * <p>
 * 思路：遍历dfs，对于每个节点求她的左子树的深度和右子树的深度
 */
public class Solution55_2 {

    /**
     * 思路二：后序遍历 + 剪枝
     * 若当前节点node为根的树的左右子树深度差<=1，则返回当前子树的深度
     * 若>1，则返回-1，代表以此node为根的子树不是平衡二叉树
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return recur(root) != -1;
    }

    private int recur(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = recur(root.left);
        //若出现节点的深度为-1，则进行剪枝，开始向上返回，之后的迭代不再进行
        if (leftDepth == -1) {
            return -1;
        }
        int rightDepth = recur(root.right);
        if (rightDepth == -1) {
            return -1;
        }
        return Math.abs(leftDepth - rightDepth) <= 1 ? Math.max(leftDepth, rightDepth) + 1 : -1;
    }

    /**
     * 思路一：遍历每个节点，算当前节点的左右子树的深度差。但是时间复杂度是O(n2)
     */
    public boolean isBalanced2(TreeNode root) {

        if (root == null) {
            return true;
        }
        return Math.abs(getDepth(root.left) - getDepth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 返回当前节点为根的树的深度
     */
    private int getDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return Math.max(getDepth(node.left), getDepth(node.right)) + 1;
    }
}
