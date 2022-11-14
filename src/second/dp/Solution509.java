package second.dp;

import java.util.Arrays;

/**
 * 509. 斐波那契数
 * <p>
 * 动态规划思路：
 * 1.状态转移方程已经给出：F(n) = F(n - 1) + F(n - 2)
 * 2.定义dp：dp(n)即F(n)的值
 * 4.base case:i=0 i=1
 * 3.有重叠子问题可以优化
 * 4.【第四步：空间优化】：因为当前数之前前两个数有关，没必要用一个n大小的数组来存下所有的数
 * <p>
 * <p>
 * dp几步曲：
 * 1.先找状态和选择
 * 2.根据状态，定义dp数组
 * 3.dp数组定义出来，就可以找出base case和所求返回形式，以及遍历顺序（一般就是从base case的方向开始遍历）
 * 3.根据选择，定义状态转移方程式
 * 4.优化方面：
 * 1）先考虑时间上的优化：是否有重叠子问题（备忘录），
 * 2）再考虑空间上的优化：1是否只需要存几个值，2二维是否可以压缩成一维
 */
public class Solution509 {
    int[] memo;

    public int fib2(int n) {

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
        if (n == 1) {
            return 1;
        }
        //2.备忘录
        if (memo[n] != -1) {
            return memo[n];
        }
        //3.状态转移
        memo[n] = dp(n - 1) + dp(n - 2);
        return memo[n];
    }

    /**
     * 空间优化：只存两个数。从base case往后遍历
     */
    public int fib(int n) {
        //1.base case
        if (n == 0 || n == 1) {
            return n;
        }
        //2.定义备忘录
        int lastNum = 1;
        int lastLastNum = 0;
        int curNum = -1;
        //3.状态转移
        for (int i = 3; i <= n; i++) {
            curNum = lastNum + lastLastNum;
            lastLastNum = lastNum;
            lastNum = curNum;
        }
        return curNum;
    }

}
