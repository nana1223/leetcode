package second.dp;

/**
 * 714. 买卖股票的最佳时机含手续费
 * <p>
 * 定义dp：dp[i][j] = x  x表示第i天是否持有股票时累计最大利润
 * base case:
 * dp[0][0] = 0
 * dp[0][1] = -prices[0] -fee
 * 状态转移：（手续费在买入的时候掏
 * dp[i][0] = Math.max ( dp[i-1][0], dp[i-1][1] + prices[i] )
 * dp[i][1] = Math.max ( dp[i-1][1], dp[i-1][0] - prices[i] - fee)
 */
public class Solution714 {

    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        dp[0][0] = 0;
        //注意初始化别忘了第一天买入的时候也要-fee
        dp[0][1] = -prices[0] - fee;

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);
        }
        return dp[n - 1][0];
    }
}
