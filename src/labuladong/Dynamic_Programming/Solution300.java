package labuladong.Dynamic_Programming;

import java.util.Arrays;

/**
 * 300. 最长递增子序列
 */
public class Solution300 {

    /**
     * 法一：标准的动态规划解法  O(n^2)
     * <p>
     * 总结一下如何找到动态规划的状态转移关系：
     * 1、明确 dp 数组的定义。这一步对于任何动态规划问题都很重要，如果不得当或者不够清晰，会阻碍之后的步骤。
     * 2、根据 dp 数组的定义，运用数学归纳法的思想，假设 dp[0...i-1] 都已知，想办法求出 dp[i]，一旦这一步完成，整个题目基本就解决了。
     * 但如果无法完成这一步，很可能就是 dp 数组的定义不够恰当，需要重新定义 dp 数组的含义；
     * 或者可能是 dp 数组存储的信息还不够，不足以推出下一步的答案，需要把 dp 数组扩大成二维数组甚至三维数组。
     */
    public int lengthOfLIS(int[] nums) {

        //定义：dp[i]表示以nums[i]为结尾的最长递增子序列的长度（初始化为1）
        int[] dp = new int[nums.length];
        //base case: dp数组全初始化为1
        Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                //最长子序列又可以增长
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        //所求即dp数组中的最大值
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
