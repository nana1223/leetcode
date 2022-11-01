package labuladong.Dynamic_Programming;

import java.util.Arrays;

/**
 * 64. 最小路径和
 * 给定一个包含非负整数的 mxn网格grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 动归：思路：1.先定义dp 2.找到base case 3.针对具体的i j 看看怎么状态转移 列出状态转移方程  4.看有没有重叠子问题优化加备忘录
 * 1.定义dp：dp(grid, i, j)表示从[0][0]到[i][j]的最小路径和
 * 2.base case: dp[0][0]=grid[0][0] i<m j<n   i>=0 j>=0 （注意！base case要考虑特殊值和越界的地方）
 * 3. 向右或者向下取最小 （向右：dp[i][j] + grid[i][j+1]   向下：dp[i][j] + grid[i+1][j]）
 * ！！！！！！也就是说！一个数的值取决于左边和上边
 * 4.有重叠子问题，存memo
 *
 * 反思：注意要看递归的方向是哪个！！！这道题是从后往前
 */
public class Solution64 {

    int[][] memo;

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        //1.构造备忘录并初始化
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        //分析：dp(grid, i, j)的值取决于dp(grid, i-1, j)和dp(grid, i, j-1)的值取最小，所以从右下开始遍历
        return dp(grid, m - 1, n - 1);
    }


    private int dp(int[][] grid, int i, int j) {

        //1.base case
        if (i == 0 && j == 0) {
            return grid[0][0];
        }

        //越界处理：如果索引出界，返回一个很大的值，保证在取 min 的时候不会被取到
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }
        //2.查备忘录
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        //3.状态转移
        memo[i][j] = Math.min(
                dp(grid, i - 1, j),
                dp(grid, i, j - 1)
        ) + grid[i][j];

        return memo[i][j];
    }
}
