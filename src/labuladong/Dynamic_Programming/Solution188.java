package labuladong.Dynamic_Programming;

/**
 * 188. 买卖股票的最佳时机 IV
 */
public class Solution188 {

    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        //n天，最多两笔交易，对股票有01两种状态持有or非持有
        //注意k笔交易的取值是从1开始
        int[][][] dp = new int[n][k + 1][2];

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                //1.base case
                if (i - 1 == -1) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[i];
                    continue;
                }
                if (j == 0) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[i];
                }

                //2.状态转移 【！！！k在买入的时候变化，一次买卖过程叫一次交易】
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][k][0];
    }
}
