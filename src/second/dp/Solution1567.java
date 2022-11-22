package second.dp;

import java.util.Arrays;

/**
 * 1567. 乘积为正数的最长子数组长度
 * 要返回的是子数组的长度。
 * <p>
 * 1.定义状态和选择：状态就是下标i；选择要根据nums[i]的正负情况来进行选择
 * 2.定义dp：dp(i)=x表示以i为结尾的连续最长子数组的长度
 * 3.状态转移：要根据nums[i]的正负情况不同来考虑
 * <p>
 * 和152同思路：
 * 对于nums[i]，要考虑正负两种情况，所以要【存最长为正数 的乘积子数组的长度 和 最长为负数 的乘积子数组 的长度】
 */
public class Solution1567 {

    public int getMaxLen(int[] nums) {

        int n = nums.length;

        //1.定义dp
        int[] positiveDp = new int[n];
        int[] negativeDp = new int[n];

        //2.base case
        if (nums[0] > 0) {
            positiveDp[0] = 1;
        }
        if (nums[0] < 0) {
            negativeDp[0] = 1;
        }

        //3.状态转移
        for (int i = 1; i < n; i++) {

            if (nums[i] > 0) {
                //nums[i]>0 时，之前的乘积乘以 nums[i] 不会改变乘积的正负性。
                positiveDp[i] = positiveDp[i - 1] + 1;
                negativeDp[i] = negativeDp[i - 1] > 0 ? negativeDp[i - 1] + 1 : 0;
            } else if (nums[i] < 0) {
                //nums[i]<0 时，之前的乘积乘以 nums[i] 会改变乘积的正负性。
                positiveDp[i] = negativeDp[i - 1] > 0 ? negativeDp[i - 1] + 1 : 0;
                negativeDp[i] = positiveDp[i - 1] + 1;
            } else {
                positiveDp[i] = 0;
                negativeDp[i] = 0;
            }
        }
        return Arrays.stream(positiveDp).max().getAsInt();
    }
}
