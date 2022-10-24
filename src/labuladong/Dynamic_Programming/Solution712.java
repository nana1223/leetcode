package labuladong.Dynamic_Programming;

import java.util.Arrays;

/**
 * 712.两个字符串的最小ASCII删除和
 * 给定两个字符串s1 和 s2，返回 使两个字符串相等所需删除字符的 ASCII 值的最小和 。
 * <p>
 * 思路：1.先定义dp 2.找到base case 3.针对具体的i j 看看怎么状态转移 列出状态转移方程  4.看有没有重叠子问题优化加备忘录
 * dp定义:将 s1[i..] 和 s2[j..] 删除成相同字符串，最小的 ASCII 码之和为 dp(s1, i, s2, j)。
 *
 * @author zhangna
 */
public class Solution712 {

    int[][] memo;

    public int minimumDeleteSum(String s1, String s2) {

        int m = s1.length();
        int n = s2.length();
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp(s1, 0, s2, 0);
    }

    private int dp(String s1, int i, String s2, int j) {
        int res = 0;

        //1.base case
        if (i == s1.length()) {
            //如果s1到头了，那么s2剩下的都得删除
            for (; j < s2.length(); j++) {
                res += s2.charAt(j);
                //????????????????????????????这样就能获取ASCII码值？？？
            }
            return res;
        }
        if (j == s2.length()) {
            for (; i < s1.length(); i++) {
                res += s1.charAt(i);
            }
            return res;
        }

        //2.备忘录里找
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        //3.状态转移
        if (s1.charAt(i) == s2.charAt(j)) {
            //这个字符在s1和s2中都有，不用删除
            memo[i][j] = dp(s1, i + 1, s2, j + 1);
        } else {
            memo[i][j] = Math.min(
                    s1.charAt(i) + dp(s1, i + 1, s2, j),
                    s2.charAt(j) + dp(s1, i, s2, j + 1)
            );
        }
        return memo[i][j];
    }
}
