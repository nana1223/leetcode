package jianzhi_offer;

import java.util.Arrays;

/**
 * 剑指 Offer 10- II. 青蛙跳台阶问题
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * <p>
 * 1.状态：所在台阶数i。选择：跳一阶 或者 跳二阶
 * 2.dp数组的定义：dp[i]表示跳到台阶i有几种跳法
 * 3.base case: dp[0]= 1, dp[1]= 1, dp[2] = 2。所求：dp[n]
 * 4.状态转移方程：【注意 根据选择 来写状态转移方程】
 * 分析过程：
 * 第0级：1种方式，就是原地不动；
 *
 * 第1级：1种方式，即从0走一步到第1级；
 *
 * 第2级：2种方式，即从0到1到2，或者直接从0到2；
 *
 * 第3级：考虑每次只能上1或者2级，那么到第三级只有两种情况：从第1级上两步到第3级，或者从第2级走一步到第三级。
 *      上面我们又计算了，到第1级只有一种方式，到第二级有2种方式，所以到第三级的方式就有：1*1+2*1=3。
 *      每一次从前一级或者前二级到当前级都只有一种方式，所以也可以写成：1+2=3。
 * ... ...
 * 第n级：同上，到第n级只有两种方式：从n-2级走两步到第n级，或者从n-1级走一步到第n级。
 * 假设到第n-2级的方式有 f(n-2)种，到第n-1级的方式有f(n-1)种，则到第n级的方式有：f(n-2)*1+f(n-1)*1=f(n-2)+f(n-1)。
 *
 * 5.有重叠子问题
 */
public class Solution10_2 {

    int[] memo;

    public int numWays(int n) {
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return dp(n);
    }

    private int dp(int n) {
        //1.base case
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return n;
        }
        //2.查表
        if (memo[n] != -1) {
            return memo[n];
        }
        //3.递归，并送进memo记录
        memo[n] = (dp(n - 1) + dp(n - 2)) % 1000000007;
        return memo[n];
    }
}