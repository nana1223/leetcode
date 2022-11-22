package second.dp;

import java.util.Arrays;

/**
 * 53. 最大子数组和
 * 动态规划
 * 1.定义状态和选择：状态就是下标i，选择就是是否把dp[i-1]加入子数组
 * 2.定义dp；dp[i] 表示以nums[i] 结尾 的 连续最大子数组和
 * 3.base case: dp[0]=nums[0]
 * 4.迭代 从0开始往后。所求即dp数组求最大
 * 5.状态转移：dp[i] = Max( dp[i-1]+nums[i], nums[i] )
 */
public class Solution53 {

    /**
     * 法一：动态规划
     */
    public int maxSubArray1(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    /**
     * 法二：滑动窗口
     * 在窗口内的和大于等于0时扩充，小于0时缩小；
     * 【滑动窗口的核心要素：确定什么时候该扩大窗口，什么时候该缩小窗口】
     */
    public int maxSubArray(int[] nums) {

        int maxSum = Integer.MIN_VALUE;
        int windowsSum = 0;

        int left = 0, right = 0;
        while (right < nums.length) {
            //1.算子数组和
            windowsSum += nums[right];
            //2.更新最大子数组和
            maxSum = maxSum > windowsSum ? maxSum : windowsSum;

            //3.判断是否需要缩小窗口
            while (windowsSum < 0) {
                windowsSum -= nums[left];
                left++;
            }
            //4.扩大窗口（走到这儿说明已经满足窗口扩大的条件）
            right++;
        }
        return maxSum;
    }
}
