package jianzhi_offer;

import java.util.Arrays;

/**
 * 剑指 Offer 10- I. 斐波那契数列
 * <p>
 * 动态规划
 *
 * 【重叠子问题】有个优化：空间换时间，把每次算下的数值存下，就不用重复计算了
 *
 * 注意细节：答案需要取模 1e9+7（1000000007），即 % 1000000007
 */
public class Solution10_1 {

    int[] memo;

    public int fib(int n) {
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return dp(n);
    }

    private int dp(int n) {
        //1.结束条件
        if (n == 0 || n == 1) {
            return n;
        }
        //2.查表，表里有就不用重复计算
        if (memo[n] != -1) {
            return memo[n];
        }
        //3.递归，计算过程中还要存表
        memo[n] = (dp(n - 1) + dp(n - 2)) % 1000000007;
        return memo[n];
    }
}
