package jianzhi_offer;

import java.util.Arrays;

/**
 * 剑指 Offer 63. 股票的最大利润
 * <p>
 * 自己的思考过程：xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
 * 1.状态，选择：状态指天数，选择指当天选择卖或买或什么都不做
 * 2.dp数组的定义：dp[i]表示前i天可以到达的最大利润
 * 3.base case:dp[0]=0。所求：dp[prices.length]
 * 4.状态转移：在第i天的时候：
 * 4.1.选择卖：dp[i] = dp[i-1] + prices[i]
 * 4.2.选择买：dp[i] = dp[i-1] - prices[i]
 * 4.3.什么都不做：dp[i] = dp[i-1]
 * 把三种选择的可能性整合成状态转移方程————像本题，求得就是最大利润，所以就找所有可能的选择中的最大的
 * q：怎么控制买在卖前面？
 * 5.重叠子问题
 * <p>
 * <p>
 * 答案理解：
 * 1.dp[i]：表示前i日的最大利润。注意下标0是第一天
 * 2.base case: dp[0] = 0
 * 3.状态转移方程：
 * 由于限定了只可以买卖一次，所以前i日最大利润dp[i] = max( 前i-1日最大利润， 第i日价格 - 前i日最低价格)
 */
public class Solution63 {

    public int maxProfit(int[] prices) {
        int n = prices.length;

        if (n == 0) {
            return 0;
        }
        int minPrice = prices[0];

        int[] maxPrice = new int[n];


        for (int i = 1; i < n; i++) {
            maxPrice[i] = Math.max(maxPrice[i - 1], prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }

        return Arrays.stream(maxPrice).max().getAsInt();
    }


}
