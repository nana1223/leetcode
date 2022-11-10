package labuladong.Dynamic_Programming;

/**
 * 123. 买卖股票的最佳时机 III
 * <p>
 * dp 数组的遍历顺序是怎么确定的，主要是根据 base case，以 base case 为起点，逐步向结果靠近。
 */
public class Solution123 {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        //n天，最多两笔交易，对股票有01两种状态持有or非持有
        //注意两笔交易k的取值是1 2 所以k的位置大小应设为3
        int[][][] dp = new int[n][3][2];

        for (int i = 0; i < n; i++) {
            for (int k = 1; k <= 2; k++) {
                //1.base case
                if (i - 1 == -1) {
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                if (k == 0) {
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                }

                //2.状态转移 【！！！k在买入的时候变化，一次买卖过程叫一次交易】
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][2][0];
    }
}
