package second.dp;

/**
 * 122. 买卖股票的最佳时机 II
 * 每一天都可以选择是否购买和售出（即可以交易好多好多次）
 * 1.定义状态和选择：状态有：哪天，是否持有； 选择即买卖
 * 2.定义dp：dp[i][j]表示第i天是否持有j（j=0没有，j=1有哦）
 * 3.base case：
 * dp[-1][0] = 0
 * dp[-1][1] = 负无穷（ -prices[i]）
 * 所求即为dp[n-1][0]
 * 4.状态转移
 * 1）今天持有
 * 2）今天没持有
 */
public class Solution122 {

    public int maxProfit(int[] prices) {
        int n = prices.length;

        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            //1.base case：i-1==-1即i==0的时候，即第一天的时候
            if (i - 1 == -1) {
                dp[i][0] = 0;
                //注意这里表示负无穷，不能用Integer.MIN_VALUE。-prices[i]是根据下面的状态转移方程推出来的
                dp[i][1] = -prices[i];
                //continue别忘记！！！
                continue;
            }
            //2.状态转移方程
            // 1）如果今天持有股票：要么是昨天就有的，要么是今天买的（今天买的，今天的利润即之前的利润加上今天支出的股票价格）
            // 因为122题是可以交易很多次的， 所以这里和121题处理的有区别
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
        }
        return dp[n - 1][0];
    }
}
