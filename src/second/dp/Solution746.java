package second.dp;

import java.util.Arrays;

/**
 * 746. 使用最小花费爬楼梯
 * <p>
 * 1.定义状态和选择，状态就是当前楼梯上的目前的花费；选择就是下一个是一阶还是两阶 / 以及初始状态下选择从0开始还是1开始
 * 2.定义dp：dp(i) = x 表示站在台阶i上时所花销费用是x；所求即dp(n)
 * 3.base case: dp(0)=0, dp(1)=0 （因为可以从0或者1开始，即站到0或者1的位置上是不需要花费的）
 * 4.状态转移：dp(n) = min(dp(n-1)+cost[n-1], dp(n-2)+cost[n-2])
 * 5.memo优化
 */
public class Solution746 {

    int[] memo;

    public int minCostClimbingStairs(int[] cost) {

        int n = cost.length;
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return dp(cost, n);
    }

    private int dp(int[] cost, int n) {
        //1.base case
        if (n == 0 || n == 1) {
            return 0;
        }
        //2.查备忘录
        if (memo[n] != -1) {
            return memo[n];
        }
        //3.状态转移
        memo[n] = Math.min(
                dp(cost, n - 1) + cost[n - 1],
                dp(cost, n - 2) + cost[n - 2]
        );
        return memo[n];
    }
}
