package labuladong.二叉树.二叉搜索树;

import labuladong.二叉树.TreeNode;

/**
 * 235. 二叉搜索树的最近公共祖先
 * 思路一：利用二叉搜索树大小的特性
 * 思路二：同236
 */
public class Solution235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        //p、q的值一个比root大一个比root小，则root为最近公共祖先
        if ((root.val - p.val) * (root.val - q.val) <= 0) {
            return root;
        }

        //p、q同时比root大或者同时比root小，则再往下找
        return lowestCommonAncestor(p.val < root.val ? root.left : root.right, p, q);

    }
}
