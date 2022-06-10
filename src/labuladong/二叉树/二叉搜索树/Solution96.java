package labuladong.二叉树.二叉搜索树;

import java.util.HashMap;

/**
 * 96. 不同的二叉搜索树
 * <p>
 * 思路：穷举
 * 二叉树算法的关键就在于明确根节点需要做什么，其实 BST 作为一种特殊的二叉树，核心思路也是一样的。
 * <p>
 * 举个例子，比如给算法输入n = 5，也就是说用{1,2,3,4,5}这些数字去构造 BST。
 * <p>
 * 1.首先，这棵 BST 的根节点总共有几种情况？
 * 显然有 5 种情况对吧，因为每个数字都可以作为根节点。
 * <p>
 * 2.比如说我们固定3作为根节点，这个前提下能有几种不同的 BST 呢？
 * 根据 BST 的特性，根节点的左子树都比根节点的值小，右子树的值都比根节点的值大。
 * 所以如果固定3作为根节点，左子树节点就是{1,2}的组合，右子树就是{4,5}的组合。
 * 左子树的组合数和右子树的组合数乘积就是3作为根节点时的 BST 个数。所以，根节点为3的BST有 2×2= 4种
 * <p>
 * 3.可以一眼看出{1,2}和{4,5}有几种组合，但是怎么让算法进行计算呢？——递归
 * ，遍历1到n，以值i（注意不是下标）当做根节点，其左边有i-1个节点，右边有n-i个节点，计算出可能的二叉搜索树数量，添加到总结果里即可
 */
public class Solution96 {

    /**
     * 存 结点数量n 和 n个节点对应含有的子树数量 （若不存的话就会多次重复计算）
     */
    HashMap<Integer, Integer> subTreeCount = new HashMap<>();

    public int numTrees(int n) {

        //若只有0或1个结点 则可能的子树情况有1种
        if (n == 0 || n == 1) {
            return 1;
        }

        //不用重复计算
        if (subTreeCount.containsKey(n)) {
            return subTreeCount.get(n);
        }

        int count = 0;

        for (int i = 1; i <= n; i++) {
            //以结点i为根节点

            //左边有多少种子树
            int leftNum = numTrees(i - 1);

            //右边有多少子树
            int rightNum = numTrees(n - i);

            //左右相乘就是当前结点的子树个数
            count += leftNum * rightNum;
        }

        subTreeCount.put(n, count);

        return count;
    }
}
