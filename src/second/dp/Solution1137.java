package second.dp;

import java.util.Arrays;

/**
 * 1137. 第 N 个泰波那契数
 */
public class Solution1137 {

    int[] memo;

    public int tribonacci1(int n) {
        //1.【细节一】：memo的大小要设置成n+1，因为举例：比如说n=2，那就要把2的值存到memo[2]中，那memo大小就得设置成3
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return dp(n);
    }

    private int dp(int n) {
        //1.base case
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        //2.备忘录
        if (memo[n] != -1) {
            return memo[n];
        }
        //3.状态转移
        memo[n] = dp(n - 1) + dp(n - 2) + dp(n - 3);
        return memo[n];
    }

    /**
     * 空间优化：只存三个数。从base case往后遍历
     */
    public int tribonacci(int n) {
        //1.base case
        if (n == 0 || n == 1) {
            return n;
        }
        if (n == 2) {
            return 1;
        }
        //2.定义备忘录
        int lastNum = 1;
        int lastLastNum = 1;
        int lastLastLastNum = 0;
        int curNum = -1;
        //3.状态转移
        for (int i = 3; i <= n; i++) {
            curNum = lastNum + lastLastNum + lastLastLastNum;
            lastLastLastNum = lastLastNum;
            lastLastNum = lastNum;
            lastNum = curNum;
        }
        return curNum;
    }
}
