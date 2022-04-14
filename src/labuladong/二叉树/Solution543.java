package labuladong.二叉树;

/**
 * 二叉树的直径
 * 思路：最长路径，用一次遍历
 * 反思：先理解题目：什么是二叉树的直径？可以转换成什么计算？————可以转换成求二叉树最大深度的思路来算
 * 假设我们知道对于该节点的左儿子向下遍历经过最多的节点数 L（即以左儿子为根的子树的深度） 和其右儿子向下遍历经过最多的节点数 R（即以右儿子为根的子树的深度），
 * 那么以该节点为起点的路径经过节点数的最大值即为 L+R+1
 */
public class Solution543 {

    int res = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        //树的直径 为经过的结点数-1
        return res - 1;
    }

    private int depth(TreeNode root) {

        //递归结束条件
        if (root == null) {
            return 0;
        }

        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        res = Math.max(res, leftDepth + rightDepth + 1);
        // 返回该节点为根的子树的深度
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
