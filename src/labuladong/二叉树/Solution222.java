package labuladong.二叉树;

/**
 * 222. 完全二叉树的节点个数
 * 思路一：遍历树，复杂度O(n)
 * 思路二：因为是完全二叉树，找一个更简单的方法。（对普通二叉树和满二叉树的结合使用）
 * 一棵完全二叉树的两棵子树，至少有一棵是满二叉树。所以，左右子树的两个递归只有一个会真的递归下去，另一个一定会触发满二叉树的条件 hl == hr 而立即返回，不会递归下去。
 */
public class Solution222 {

    public int countNodes(TreeNode root) {
        TreeNode p = root, q = root;
        //沿最左侧和最右侧分别计算高度，判断子树是否是满二叉树
        int leftHeight = 0, rightHeight = 0;
        while (p != null) {
            p = p.left;
            leftHeight++;
        }
        while (q != null) {
            q = q.right;
            rightHeight++;
        }

        //以root根节点对应的二叉树是满二叉树，则节点数可以直接算出
        if (leftHeight == rightHeight) {
            return (int) Math.pow(2, leftHeight) - 1;
        }

        //以root根节点对应的二叉树不是满二叉树，则继续以普通二叉树的方式计算
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

}
