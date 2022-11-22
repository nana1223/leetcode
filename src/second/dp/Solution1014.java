package second.dp;

import java.util.Arrays;

/**
 * 1014. 最佳观光组合
 * <p>
 * 1.定义状态和选择
 * 2.dp[i]=x表示以i为起点的对儿景点中的最大值
 * 3.状态转移：
 */
public class Solution1014 {

    /**
     * 法一：暴力解法
     * 超时了
     */
    public int maxScoreSightseeingPair1(int[] values) {
        int n = values.length;

        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = values[i] + values[j] + i - j;
            }
        }
        int res = Integer.MIN_VALUE;
        for (int[] row : dp) {
            res = Math.max(res, Arrays.stream(row).max().getAsInt());
        }
        return res;
    }

    /**
     * 法二：动态规划
     * 【优化思路】：
     * 原公式是：values[i] + values[j] + i - j，有两个变量在变i和j，所以要双层循环来维护。
     * 想办法能怎么处理，让只有一个变量需要变动，时间复杂度变成O(n)
     * 把公式分成 values[i] + i和 values[j] - j，对于每一个values[j]来说，要想求values[j]的最大值，即求j之前的values[i] + i的最大值。
     * 所以用一个变量 pre_max 记录当前元素 A[j] 之前的 A[i] + i 的最大值，这样对于每个 A[j] 来说，都有 最大得分 = pre_max + A[j] - j
     *
     * 【反思：】
     * 当需要两个变量变化，时间复杂度O(n^2)的时候，优化思路：
     * 看看能否这两个变量能在一次遍历过程中同时维护
     */
    public int maxScoreSightseeingPair(int[] values) {
        int n = values.length;
        int res = 0;
        int preMax = values[0] + 0;
        for (int j = 1; j < n; j++) {
            //边遍历j，边维护i
            res = Math.max(res, preMax + values[j] - j);
            preMax = Math.max(preMax, values[j] + j);
        }
        return res;
    }
}
