package second.dp;

/**
 * 121. 买卖股票的最佳时机
 */
public class Solution121 {

    /**
     * 法一：根据题意：要股票的最低价时候买入，如果不是最低价就尝试卖出，看是否是最大利润。一次遍历，维护两个变量，minPrice, maxProfit
     * 执行用时：1 ms
     * 【反思】：一次遍历，维护两个变量。好像是常用简化思路
     * （这道题暴力法，就是两层循环，不断求profit更新最大值）优化就是这种一次遍历
     */
    public int maxProfit1(int[] prices) {
        int n = prices.length;

        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {

            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                maxProfit = prices[i] - minPrice > maxProfit ? prices[i] - minPrice : maxProfit;
            }
        }
        return maxProfit;
    }

    /**
     * 法二：动态规划：
     * <p>
     * 1.定义选择和状态：这道题状态就是天数和是否持有股票；选择就是是否买卖
     * 2.定义dp：dp[i][j]表示第i天是否持有（j=0表示不持有，j=1表示持有）的状态下，所得利润
     * 3.base case：
     * dp[-1][0] = 0
     * dp[-1][1] = 负无穷 （负无穷怎么表示用状态转移方程推出）
     * 所求是：dp[n-1][0]
     * 4.定义状态转移方程
     * 1）如果今天持有股票：要么是昨天就有的，要么是今天买的（今天买的，即今天的利润支出股票价格）
     * dp[i][1] = Math.max( dp[i-1][0] - prices[i], dp[i-1][1] )
     * 2）今天没有股票
     * dp[i][0] = Math.max( dp[i-1][0], dp[i-1][1] + prices[i])
     * <p>
     * 算i要由i-1获得 而base case在-1处 【迭代】
     */
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
            // 1）如果今天持有股票：要么是昨天就有的，要么是今天买的（今天买的，今天的利润即支出股票价格，不必再把前一天的加上）
            dp[i][1] = Math.max(-prices[i], dp[i - 1][1]);
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
        }
        return dp[n - 1][0];
    }
}
