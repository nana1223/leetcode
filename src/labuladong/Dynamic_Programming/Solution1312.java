package labuladong.Dynamic_Programming;

import java.util.Arrays;

/**
 * 1312. 让字符串成为回文串的最少插入次数
 * <p>
 * 思路：1.先定义dp 2.找到base case 3.针对具体的i j 看看怎么状态转移 列出状态转移方程  4.看有没有重叠子问题优化加备忘录
 * 1.dp数组的定义：在子数组arr[i…j]中，我们要求的为让dp[i][j]成为回文串的操作次数
 * * 2.base case: ij相等的时候 不用插入 返回0 / j>j的时候 返回0
 * * 3.怎么转移：（ij分别从最左边和最右边开始）
 * * 分两种大的情况：ij相等和ij不相等
 * * 3.1 ij相等就两个一起往里面移动
 * * 3.2 ij不相等就（1i和j的位置都要插入对方的字符，操作两次 2只插入i  3只插入j）
 * * 4.有重叠子问题 2 3可以变为1（3.2中的三种操作），所以用一个二维数组memo存一下
 */
public class Solution1312 {

    int[][] memo;

    public int minInsertions(String s) {
        int n = s.length();
        memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp(s, 0, n - 1);
    }

    private int dp(String s, int i, int j) {
        //1.base case
        if (i == j || i > j) {
            return 0;
        }
        //2.查备忘录
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        //3.状态转移
        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = dp(s, i + 1, j - 1);
        } else {
            memo[i][j] = Math.min(
                    1 + dp(s, i + 1, j),
                    1 + dp(s, i, j - 1)
            );
        }
        return memo[i][j];
    }
}
