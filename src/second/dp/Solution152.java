package second.dp;

import java.util.Arrays;

/**
 * 152. 乘积最大子数组
 * 连续的子数组
 * 【迭代】
 * 1.定义状态和选择，状态就是下标i，选择就是到下标i要乘下标i的时候算不算上之前的乘积和
 * 2.定义dp：dp(i)=x表示以i为结尾的子数组中最大乘积和的值
 * 3.base case dp(0)=nums[0]
 * 4.状态转移：就是不断看哪个更大，如果不要之前的更大就不要，如果要上更大就要上
 * <p>
 * 注意：这个乘积最大子数组和累加和最大子数组还不一样，因为乘积他有负负得正的情况。eg：[-2,3,-4]
 * 【不满足「最优子结构」】
 * <p>
 * 【思路：根据正负性分类讨论】
 * 如果当前下标i的位置是负数：则希望他前面的连续子数组是负的且越小越好。即要求【乘积最小子数组】
 * 如果当前下标i的位置是整数：则希望他前面的连续子数组是正的且越大越好，即要求【乘积最大子数组】
 *
 * 【反思：】
 * 要考虑的情况越多，要维护的就越多。很多时候都是求最大，再维护个最大和最小？
 */
public class Solution152 {

    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[] maxDp = new int[n];
        int[] minDp = new int[n];

        maxDp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            // 把当前下标的数据加入之前连续的最大子数组和最小子数组和哪个都不加，三者比大小
            maxDp[i] = Math.max(nums[i], Math.max(maxDp[i - 1] * nums[i], minDp[i - 1] * nums[i]));
            minDp[i] = Math.min(nums[i], Math.min(maxDp[i - 1] * nums[i], minDp[i - 1] * nums[i]));
        }
        return Arrays.stream(maxDp).max().getAsInt();
    }
}
