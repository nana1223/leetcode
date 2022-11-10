package labuladong.Dynamic_Programming;

/**
 * 714. 买卖股票的最佳时机含手续费
 * 每笔交易都需要付手续费。
 */
public class Solution714 {

    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            //1.base case
            if (i - 1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i] - fee;
                continue;
            }
            //2.状态转移 【每次买入的时候要付出手续费】
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
        }
        return dp[n - 1][0];

    }
}
