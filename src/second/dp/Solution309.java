package second.dp;

/**
 * 309. 最佳买卖股票时机含冷冻期
 * 相比121题：状态转移方程有所区别：卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * <p>
 * 1.定义选择和状态：这道题状态就是天数和是否持有股票以及是否在冷冻期；选择就是是否买卖
 * 2.定义dp：dp[i][j]表示第i天是否持有（j=0表示不持有且不在冷冻期，j=1表示不持有但在冷冻期，j=2表示持有）的状态下 所得累计最大利润
 * 3.base case：
 * dp[0][0] = 0
 * dp[0][1] = 0
 * dp[0][2] = -prices[0] 第1天持有，即第1天就买入了 总利润为-prices[0]
 * 这样定义的话所求是：dp[n-1][0]或者dp[n-1][1]
 * 4.定义状态转移方程
 * 1）如果今天持有股票：要么是昨天就有的，要么是今天买的（今天买的，得昨天不持有且不在冷冻期）
 * dp[i][2] = Math.max( dp[i-1][0] - prices[i], dp[i-1][2] )
 * 2）今天没有股票且在冷冻期，即今天卖出的
 * dp[i][1] = dp[i-1][2] + prices[i]
 * 3）今天没有股票且不在冷冻期，即一直没有
 * dp[i][0] = Math.max( dp[i-1][0], dp[i-1][1] )
 * <p>
 * 怎么体现今天卖了明天不能买？？？
 * 第一反应是用个tag记录一下上一次卖的日期，判断下一次买的时候。但是不如在状态上记录，分成三个状态
 */
public class Solution309 {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        //定义dp
        int[][] dp = new int[n][3];

        //1.base case
        dp[0][0] = 0;
        dp[0][1] = 0;
        dp[0][2] = -prices[0];

        //2.状态转移方程 从1开始迭代
        for (int i = 1; i < n; i++) {
            dp[i][2] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][2]);
            dp[i][1] = dp[i - 1][2] + prices[i];
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);

        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }
}