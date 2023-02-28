package jianzhi_offer;

/**
 * 剑指 Offer 47. 礼物的最大价值
 * <p>
 * 1.状态：所在的格子位置；选择：往右还是往下
 * 2.dp[i][j]表示从左上角走到dp[i][j]所得的最大价值
 * 3.base case: dp[0][0] = grid[0][0]
 * 所求：dp[m-1][n-1]
 * 4.状态转移：
 * dp[i][j] = Math.max(dp[i-1][j] + grid[i][j], dp[i][j-1] + grid[i][j])
 * <p>
 * 思路是对的！！！就是代码写的递归。but！这道题递归会超时！！！所以用迭代！
 */
public class Solution47 {

    public int maxValue(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j] + grid[i][j], dp[i][j - 1] + grid[i][j]);
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
