package second.dp;

import java.util.Arrays;

/**
 * 213. 打家劫舍 II
 * 围成一圈，则偷0或者1为起始点，然后转换成198问题
 * 1.定义状态和选择
 * 2.定义dp：dp(i)=x表示到第i个房子偷到的金额为x。
 * 3.定义base case：i>=n
 * 4.状态转移
 * 5.memo：【这道题不用备忘录优化会超时】
 */
public class Solution213 {

    int[] memo;

    public int rob(int[] nums) {
        int n = nums.length;
        if (nums.length == 1) {
            return nums[0];
        }
        memo = new int[n];
        Arrays.fill(memo, -1);
        int res1 = dp(nums, 0, n - 1);
        Arrays.fill(memo, -1);
        int res2 = dp(nums, 1, n);
        return Math.max(res1, res2);
    }

    private int dp(int[] nums, int start, int end) {
        if (start >= end) {
            return 0;
        }
        if (memo[start] != -1) {
            return memo[start];
        }
        memo[start] = Math.max(
                dp(nums, start + 1, end),
                dp(nums, start + 2, end) + nums[start]
        );
        return memo[start];
    }
}
