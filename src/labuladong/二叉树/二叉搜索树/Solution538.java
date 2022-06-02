package labuladong.二叉树.二叉搜索树;

import labuladong.二叉树.TreeNode;

/**
 * 538.把二叉搜索树转换成累加树
 * 使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
 * <p>
 * 思路：中序遍历的时候，加它之后顺序的字段 （这样没法加）
 * <p>
 * 思路开拓：中序遍历是打印升序顺序 ，那如何打印降序顺序？？？ 只需要调换一下遍历顺序，先遍历右子树再遍历左子树
 * 通过降序顺序依次打印，就可以求得大于等于root.val的值的和
 */
public class Solution538 {

    //记录累加和
    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        traversal(root);
        return root;
    }

    private void traversal(TreeNode root) {
        if (root == null) {
            return;
        }

        traversal(root.right);

        sum += root.val;
        root.val = sum;

        traversal(root.left);
    }


}
