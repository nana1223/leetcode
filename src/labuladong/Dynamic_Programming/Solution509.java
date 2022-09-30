package labuladong.Dynamic_Programming;

public class Solution509 {

    public int fib1(int n) {
        //备忘录全初始化0
        int[] memo = new int[n + 1];
        //进行带备忘录的递归
        return helper(n, memo);
    }

    private int helper(int n, int[] memo) {
        //base case
        if (n == 0 || n == 1) {
            return n;
        }
        //备忘录优化：已经计算过，不用再计算了
        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = helper(n - 1, memo) + helper(n - 2, memo);
        return memo[n];
    }

    public int fib2(int n) {
        if (n == 0) {
            return 0;
        }
        //1.建个dp表
        int[] dp = new int[n + 1];
        //2.初始化 base case
        dp[0] = 0;
        dp[1] = 1;
        //3.进行状态转移
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];

    }

    public int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        //分别代表dp[i-1]和dp[i-2]
        int dp_i_1 = 1, dp_i_2 = 0;
        for (int i = 2; i <= n; i++) {
            int dp_i = dp_i_1 + dp_i_2;
            //滚动更新
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i_1;
    }
}
