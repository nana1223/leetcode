package labuladong;

import labuladong.二叉树.TreeNode;

import java.util.HashMap;

public class Solution337 {

    //备忘录
    HashMap<TreeNode, Integer> memo = new HashMap<>();

    public int rob(TreeNode root) {
        //1.base case
        if (root == null) {
            return 0;
        }
        //2.查备忘录
        if (memo.containsKey(root)) {
            return memo.get(root);
        }
        //3.状态转移
        //抢，然后去下下家
        int doIt = root.val +
                (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right)) +
                (root.right == null ? 0 : rob(root.right.left) + rob(root.right.right));
        //不抢，去下家
        int notRob = rob(root.left) + rob(root.right);
        //反思：什么时候是两种情况相加，什么时候是两种情况取最大，注意看题判别
        int res = Math.max(doIt, notRob);
        memo.put(root, res);
        return res;
    }

}
