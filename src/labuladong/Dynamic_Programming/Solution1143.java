package labuladong.Dynamic_Programming;

import java.util.Arrays;

/**
 * 做算法题的技巧就是————，把大的问题细化到一个点，先研究在这个小的点上如何解决问题，然后再通过递归/迭代的方式扩展到整个问题。
 * <p>
 * 1143. 最长公共子序列
 * https://mp.weixin.qq.com/s/ZhPEchewfc03xWv9VP3msg
 */
public class Solution1143 {

    int[][] memo;

    public int longestCommonSubsequence(String text1, String text2) {

        int m = text1.length();
        int n = text2.length();
        memo = new int[m][n];
        for (int[] array : memo) {
            Arrays.fill(array, -1);
        }
        return dp(text1, 0, text2, 0);
    }


    /**
     * dp定义：dp(String s1, int i, String s2, int j)：计算 s1[i..] 和 s2[j..] 的最长公共子序列长度
     * <p>
     * 思路：1.先定义dp 2.找到base case 3.针对具体的i j 看看怎么状态转移 列出状态转移方程  4.看有没有重叠子问题优化加备忘录
     */
    private int dp(String text1, int i, String text2, int j) {

        //1.base case
        if (i == text1.length() || j == text2.length()) {
            return 0;
        }

        //2.备忘录优化
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        //3.相等的情况/不等的情况
        if (text1.charAt(i) == text2.charAt(j)) {
            memo[i][j] = 1 + dp(text1, i + 1, text2, j + 1);
        } else {
            memo[i][j] = Math.max(
                    dp(text1, i, text2, j + 1),
                    dp(text1, i + 1, text2, j)
            );
        }
        return memo[i][j];
    }

}
