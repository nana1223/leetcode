package labuladong.Dynamic_Programming;

import java.util.Arrays;

/**
 * 583. 两个字符串的删除操作
 * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。每步 可以删除任意一个字符串中的一个字符。
 * 理解题意：两个单词最后删的剩下的结果 就是最长公共子序列（1143）
 * <p>
 * 思路：1.先定义dp 2.找到base case 3.针对具体的i j 看看怎么状态转移 列出状态转移方程  4.看有没有重叠子问题优化加备忘录
 * dp:给两个String，返回这两个String相同所需的最小步数
 */
public class Solution583 {

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int lcs = longestCommonSubsequence(word1, word2);
        return m - lcs + (n - lcs);
    }

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
