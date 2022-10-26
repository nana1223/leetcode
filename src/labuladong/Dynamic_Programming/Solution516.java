package labuladong.Dynamic_Programming;

import java.util.Arrays;

/**
 * 一、什么时候用动态规划：一旦涉及到子序列和最值，那几乎可以肯定，考察的是动态规划技巧，时间复杂度一般都是 O(n^2)。
 * （原因很简单，你想想一个字符串，它的子序列有多少种可能？起码是指数级的吧，这种情况下，不用动态规划技巧，还想怎么着？）
 * 二、用动规的两种思路模板
 * <p>
 * 516. 最长回文子序列
 * “回文串”：是一个正读和反读都一样的字符串
 * <p>
 * 思路：1.先定义dp 2.找到base case 3.针对具体的i j 看看怎么状态转移 列出状态转移方程  4.看有没有重叠子问题优化加备忘录
 * 1.dp数组的定义：在子数组arr[i…j]中，我们要求的子序列的长度为dp[i][j]
 * 2.base case: ij相等的时候 长度为1 和 i>j返回
 * 3.怎么转移：（ij分别从最左边和最右边开始）
 * 分两种大的情况：ij相等和ij不相等
 * 3.1 ij相等就两个一起往里面移动
 * 3.2 ij不相等就（分别移i或者j然后取最大值）
 * 4.有重叠子问题 ij 移到i+1和j-1可以走不同路，所以用一个二维数组memo存一下
 */
public class Solution516 {

    int[][] memo;

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        //1.初始化备忘录数组
        memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp(s, 0, n - 1);

    }

    private int dp(String s, int i, int j) {

        //1.base case
        if (i == j) {
            return 1;
        }
        if (i > j) {
            return 0;
        }

        //2.去备忘录看有无
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        //3.状态转移
        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = 2 + dp(s, i + 1, j - 1);
        } else {
            memo[i][j] = Math.max(
                    dp(s, i + 1, j),
                    dp(s, i, j - 1)
            );
        }
        return memo[i][j];
    }
}
