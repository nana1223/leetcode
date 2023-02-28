package jianzhi_offer;

import java.util.Arrays;

/**
 * 剑指 Offer 42. 连续子数组的最大和
 * <p>
 * 1.定义状态和选择
 * 2.定义dp[i]：dp[i]表示以nums[i]为结尾的连续子数组的最大和
 * 3.base case: dp[0] = nums[0] 所求就是dp数组的最大值
 * 4.状态转移：dp[i]要根据dp[i-1]的正负来分情况
 * 若dp[i-1]<=0，则 dp[i] = nums[i]
 * 若dp[i-1]>0，则 dp[i] = nums[i] + dp[i-1]
 * 相当于dp[i]取这两者的更大
 * <p>
 * 反思：一定要根据题意去构建dp数组，题目问的是连续子数组的最大和
 * <p>
 * 【注意】！：在每次记录dp的时候就去改结果值，不要最后再求dp的最大值。又浪费时间
 */
public class Solution42 {

    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n];
        dp[0] = nums[0];

        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
