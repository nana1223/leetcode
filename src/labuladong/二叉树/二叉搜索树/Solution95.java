package labuladong.二叉树.二叉搜索树;

import labuladong.二叉树.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 95. 不同的二叉搜索树 II    ??????????????????????????????????????还不是很熟
 * <p>
 * 思路一：穷举法
 * 1、穷举root节点的所有可能。
 * 2、递归构造出左右子树的所有合法 BST。
 * 3、给root节点穷举所有左右子树的组合。
 */

public class Solution95 {

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> res = new ArrayList<>();

        //此时没有数字，将null加入结果
        if (start > end) {
            res.add(null);
            return res;
        }

        //只有一个数字，当前数字作为一棵树加入结果
        if (start == end) {
            res.add(new TreeNode(start));
            return res;
        }

        //让每个数字作为根节点
        for (int i = start; i <= end; i++) {

            //得到所有可能的左子树
            List<TreeNode> leftTrees = generateTrees(start, i - 1);
            //得到所有可能的右子树
            List<TreeNode> rightTrees = generateTrees(i + 1, end);
            //左右子树两两组合起来 加在结果集
            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftTree;
                    root.right = rightTree;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
