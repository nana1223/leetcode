package second.dp;

import java.util.Arrays;

/**
 *
 * 70. 爬楼梯
 *
 * 1.状态和选择：状态就是当前所在楼梯n， 选择就是站在当前楼梯上往上爬1或者2个，
 * 2.定义dp：dp(n)=x表示总共有n阶台阶，可以有x种不同方法爬到楼顶
 * 3.base case：n=0 n=1
 * 4.状态转移：（根据选择来写）：n个台阶所需几种：n-1个台阶所需的种类+1 或者 n-2个台阶所需种类+2
 * 4.备忘录 优化
 * <p>
 * 按上面这个思路：状态转移方程写起来出问题：不论是从上往下递归还是从下往上递推，都不太写出来
 * 反思：状态转移方程建议用笔在纸上写写画画，找规律！！！！就会发现dp(n) = dp(n-1)+dp(n-2)
 */
public class Solution70 {

    int[] memo;

    public int climbStairs(int n) {
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return dp(n);
    }

    private int dp(int n) {
        //1.base case
        if (n == 1 || n == 2) {
            return n;
        }
        //2.查备忘录
        if (memo[n] != -1) {
            return memo[n];
        }
        //3状态转移
        memo[n] = dp(n - 1) + dp(n - 2);
        return memo[n];
    }
}
