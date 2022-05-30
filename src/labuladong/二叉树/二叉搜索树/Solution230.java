package labuladong.二叉树.二叉搜索树;


/**
 * 230.二叉搜索树中第K小的元素
 * 思路：利用二叉搜索树的中序遍历，找出第k小的
 */

public class Solution230 {

    public int kthSmallest(TreeNode root, int k) {
        traversal(root, k);
        return res;
    }

    //记录结果
    int res = 0;

    //记录当前元素是第几小
    int rank = 0;

    private void traversal(TreeNode root, int k) {
        if (root == null) {

            return;
        }
        traversal(root.left, k);

        rank++;
        //找到第k小的元素
        if (rank == k) {
            res = root.val;
            return;
        }
        traversal(root.right, k);
    }


    public class TreeNode {
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
