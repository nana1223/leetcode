package labuladong.Dynamic_Programming;

import java.util.Arrays;

/**
 * 72. 编辑距离
 * 通用思路：解决两个字符串的动态规划问题，一般都是用两个指针 i, j 分别指向两个字符串的最后，然后一步步往前移动，缩小问题的规模。
 * <p>
 * 法一：暴力解法（直接啥也不优化的动态规划base case-递归遍历）
 * 法二：带备忘录的动态规划（有重复子问题）
 * 对于子问题 dp(i-1, j-1)，如何通过原问题 dp(i, j) 得到呢？
 * 有不止一条路径，比如 dp(i, j) -> dp(i - 1, j - 1) 和 dp(i, j) -> dp(i, j - 1) -> dp(i - 1, j)。
 */
public class Solution72 {


    public int minDistance1(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();

        return dp1(word1, m - 1, word2, n - 1);
    }

    /**
     * dp函数的定义：word1[0-i]和word2[0-j]之间的最小编辑距离
     */
    private int dp1(String word1, int i, String word2, int j) {

        //1. base case：word1或者word2遍历结束
        // j 走完 word2 时，如果 i 还没走完 word1，那么只能用删除操作把 word1 缩短为 word2
        //如果 i 走完 word1 时 j 还没走完了 word2，那就只能用插入操作把 word2 剩下的字符全部插入 word1
        if (i == -1) {
            return j + 1;
        }
        if (j == -1) {
            return i + 1;
        }

        //2. 从后往前遍历
        //2.1两个字符等于的情况
        if (word1.charAt(i) == word2.charAt(j)) {
            return dp1(word1, i - 1, word2, j - 1);
        }
        //2.2两个字符不等的情况
        //返回增删改三种操作中最小的一种
        //增：直接在 s1[i] 插入一个和 s2[j] 一样的字符，那么 s2[j] 就被匹配了，前移 j，继续跟 i 对比
        //删：接把 s[i] 这个字符删掉，前移 i，继续跟 j 对比
        //改：把s[i]这个字符改成和s[j]一样的，然后i,j都前移
        return min(
                dp1(word1, i, word2, j - 1) + 1,
                dp1(word1, i - 1, word2, j) + 1,
                dp1(word1, i - 1, word2, j - 1) + 1
        );

    }


    /**
     * 法二：带备忘录的动态规划
     */
    int[][] memo;

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dp(word1, m - 1, word2, n - 1);
    }

    /**
     * dp函数的定义：word1[0-i]和word2[0-j]之间的最小编辑距离
     */
    private int dp(String word1, int i, String word2, int j) {

        //1. base case：word1或者word2遍历结束
        // j 走完 word2 时，如果 i 还没走完 word1，那么只能用删除操作把 word1 缩短为 word2
        //如果 i 走完 word1 时 j 还没走完了 word2，那就只能用插入操作把 word2 剩下的字符全部插入 word1
        if (i == -1) {
            return j + 1;
        }
        if (j == -1) {
            return i + 1;
        }

        //2. 看备忘录是否已存在
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        //3. 从后往前遍历
        //3.1两个字符等于的情况
        if (word1.charAt(i) == word2.charAt(j)) {
            memo[i][j] = dp(word1, i - 1, word2, j - 1);
        } else {
            //3.2两个字符不等的情况
            //返回增删改三种操作中最小的一种
            //增：直接在 s1[i] 插入一个和 s2[j] 一样的字符，那么 s2[j] 就被匹配了，前移 j，继续跟 i 对比
            //删：接把 s[i] 这个字符删掉，前移 i，继续跟 j 对比
            //改：把s[i]这个字符改成和s[j]一样的，然后i,j都前移
            //注意：！！记得操作+1
            memo[i][j] = min(
                    dp(word1, i, word2, j - 1) + 1,
                    dp(word1, i - 1, word2, j) + 1,
                    dp(word1, i - 1, word2, j - 1) + 1
            );
        }
        return memo[i][j];
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
