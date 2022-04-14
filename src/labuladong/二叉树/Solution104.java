package labuladong.二叉树;

/**
 * 反思：
 * 二叉树类的题目，解题思维分为两种：
 * 1.是否可以通过遍历一遍二叉树得到答案？如果可以，用一个 traverse 函数配合外部变量来实现，这叫「遍历」的思维模式。
 * 2.是否可以定义一个递归函数，通过子问题（子树）的答案推导出原问题的答案？如果可以，写出这个递归函数的定义，并充分利用这个函数的返回值，这叫「分解问题」的思维模式。
 */
public class Solution104 {

    //记录最大深度
    int res = 0;

    //记录遍历到的结点的深度
    int depth = 0;

    /**
     * 法一：遍历思维
     *
     * @param root
     * @return
     */
    public int maxDepth1(TreeNode root) {
        traverse(root);
        return res;

    }

    private void traverse(TreeNode root) {
        //到叶子节点
        if (root == null) {
            res = Math.max(res, depth);
            return;
        }
        //前序位置上，depth++；后序位置上，depth--；因为前序位置是进入一个节点的时候，后序位置是离开一个节点的时候
        depth++;
        traverse(root.left);
        traverse(root.right);
        depth--;
    }

    /**
     * 法二：分解思维
     * 求树的最大深度可以 分解成求左右子树的最大深度 （然后取较大值，再加一）
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {

        //递归结束条件，到叶节点，他的左右子树深度也是0
        if (root ==null) {
            return 0;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        int res = Math.max(leftDepth, rightDepth) + 1;
        return res;

    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
