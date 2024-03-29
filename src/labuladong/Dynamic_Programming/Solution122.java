package labuladong.Dynamic_Programming;

/**
 * 122. 买卖股票的最佳时机 II
 * 和121对比：即k为正无穷，可以理解成 k和k-1是一样的（正无穷），所以k的状态可以不考虑
 */
public class Solution122 {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            //1.base case
            if (i - 1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            //2.今天持股/没持股
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
        }
        return dp[n - 1][0];
    }
}
