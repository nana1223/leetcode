package labuladong.Dynamic_Programming;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 354. 俄罗斯套娃信封问题
 * 思考：是动态规划问题，也是最长递增子序列问题LIS，但是问题是怎么把本题二维的数据转成LIS(最长递增子序列算法)可以算的一维的形式？？
 * 解法：先对宽度 w 进行升序排序，如果遇到 w 相同的情况，则按照高度 h 降序排序；之后把所有的 h 作为一个数组，然后在 h 上寻找最长递增子序列，在这个数组上计算 LIS 的长度就是答案。
 */
public class Solution354 {

    public int maxEnvelopes(int[][] envelopes) {

        int n = envelopes.length;

        //1.先对宽度进行升序排序，如果宽度一样，则对高度进行降序排序
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];
            }
        });
        //2.对高度数组寻找LIS算法
        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = envelopes[i][1];
        }
        return lengthOfLIS(height);

    }

    /**
     * 注意：这道题的测试用例有很大的，所以用这个方法会超时（建议用二分法）
     */
    private int lengthOfLIS(int[] height) {

        //1.定义dp数组：dp[i]表示以num[i]为结尾的最长子序列的长度
        int[] dp = new int[height.length];
        //2.初始化dp数组：base case：dp初始化全为1
        Arrays.fill(dp, 1);
        //3.两层for开始计算dp的值
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {

                //这里的逻辑还得再捋一下？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？
                if (height[i] > height[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        //4.出结果：即dp中最大的值
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 二分法
     */
    private int lengthOfLIS2(int[] nums) {
        int[] top = new int[nums.length];
        // 牌堆数 初始化为 0
        int piles = 0;
        for (int i = 0; i < nums.length; i++) {
            // 要处理的扑克牌
            int poker = nums[i];

            /***** 搜索左侧边界的二分查找 *****/
            int left = 0, right = piles;
            while (left < right) {
                int mid = (left + right) / 2;
                if (top[mid] > poker) {
                    right = mid;
                } else if (top[mid] < poker) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            /*********************************/

            // 没找到合适的牌堆，新建一堆
            if (left == piles) {
                piles++;
            }
            // 把这张牌放到牌堆顶
            top[left] = poker;
        }
        // 牌堆数就是 LIS 长度
        return piles;
    }

}
