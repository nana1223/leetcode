package jianzhi_offer;

import labuladong.二叉树.TreeNode;

/**
 * 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
 * <p>
 * 找二叉搜索树的最近公共祖先和找普通二叉树的有什么区别？————要考虑二叉搜索树本身的特征，它左<根<右
 * 二叉搜索树可以通过判断pq对于root的大小优化，不用全树遍历
 * 若 root < p, p在root的右子树
 * 若 root > p, p在root的左子树
 * 若 root = p, p就是root
 */
public class Solution68_1 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        //1.递归结束条件：不需要，因为官方给了均存在与树中的条件。即老子现在肯定是这两个乖孙子的公共祖先

        //2.递推工作
        //第一种情况：看看是不是都是左孩子的后代
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }

        //第二种情况：看看是不是都是右孩子的后代
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        //第三种情况：
        return root;
    }
}
