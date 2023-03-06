package jianzhi_offer;

import labuladong.二叉树.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 * <p>
 * 首先，二叉搜索树就是左边比根小右边比根大
 * 所以，二叉搜索树的中序遍历是递增序列。二叉搜索树中序遍历的倒序为递减序列
 * <p>
 * 思路：利用栈，因为二叉搜索树遍历是从小到大，要求第k大的，再出栈出到第k个。但是这样复杂度有点高
 * <p>
 * 优化思路：在第一次遍历的过程中不断更新k，在第一次遍历中就找到k对应的节点
 * 【求中序遍历的倒序】：先遍历右孩子再遍历左孩子，这样第k个就是所求
 */
public class Solution54 {

    /**
     * 思路二
     */
    int k;
    int res;

    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfsReverse(root);
        return res;
    }

    private void dfsReverse(TreeNode root) {
        //1.递归结束条件：到叶节点，或者到k对应的节点
        if (root == null) {
            return;
        }
        dfsReverse(root.right);
        if (k == 0) {
            return;
        }
        if (--k == 0) {
            res = root.val;
        }
        dfsReverse(root.left);
    }


    Deque<TreeNode> stack = new ArrayDeque<>();

    public int kthLargest1(TreeNode root, int k) {
        //1.遍历入栈
        traverse(root);
        //2.出栈
        int res = 0;
        for (int i = k; i > 0; i--) {
            res = stack.pop().val;
        }
        return res;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse(root.left);
        stack.push(root);
        traverse(root.right);
    }

}
