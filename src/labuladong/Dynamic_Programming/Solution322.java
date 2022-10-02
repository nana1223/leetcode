package labuladong.Dynamic_Programming;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 * <p>
 * 一、理解问题：这个问题是动态规划问题，因为它具有「最优子结构」的。要符合「最优子结构」，子问题间必须互相独立
 * 注：为什么符合最优子结构？——
 * 假设你有面值为 1, 2, 5 的硬币，你想求 amount = 11 时的最少硬币数（原问题），如果你知道凑出 amount = 10, 9, 6 的最少硬币数（子问题），
 * 你只需要把子问题的答案加一（再选一枚面值为 1, 2, 5 的硬币），求个最小值，就是原问题的答案。因为硬币的数量是没有限制的，所以子问题之间没有相互制，是互相独立的。
 * 二、已知动态规划问题，就要思考如何列出正确的状态转移方程？
 * <p>
 * 1、确定 base case，这个很简单，显然目标金额 amount 为 0 时算法返回 0，因为不需要任何硬币就已经凑出目标金额了。还有amount<0时返回-1
 * <p>
 * 2、确定「状态」，也就是原问题和子问题中会变化的变量。由于硬币数量无限，硬币的面额也是题目给定的，只有目标金额会不断地向 base case 靠近，所以唯一的「状态」就是目标金额 amount。
 * <p>
 * 3、确定「选择」，也就是导致「状态」产生变化的行为。目标金额为什么变化呢，因为你在选择硬币，你每选择一枚硬币，就相当于减少了目标金额。所以说所有硬币的面值，就是你的「选择」。
 * <p>
 * 4、明确 dp 函数/数组的定义。我们这里讲的是自顶向下的解法，所以会有一个递归的 dp 函数，一般来说函数的参数就是状态转移中会变化的量，也就是上面说到的「状态」；函数的返回值就是题目要求我们计算的量。就本题来说，状态只有一个，即「目标金额」，题目要求我们计算凑出目标金额所需的最少硬币数量。
 * <p>
 * 所以我们可以这样定义 dp 函数：dp(n) 表示，输入一个目标金额 n，返回凑出目标金额 n 所需的最少硬币数量。
 */
public class Solution322 {

    public int coinChange1(int[] coins, int amount) {

        return dp1(coins, amount);
    }

    /**
     * 法一：暴力解法（超时）
     */
    private int dp1(int[] coins, int amount) {
        //base case
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            //计算子问题的结果，把当前的硬币用一个
            int subProblem = dp1(coins, amount - coin);
            //子问题无解则跳过
            if (subProblem < 0) {
                continue;
            }
            //在子问题中选择最优解，然后加一
            //理解为什么加一：因为dp函数的返回值代表凑出目标金额所需的最少数量，所以要给子问题加上当前这个coin数量1
            res = Math.min(res, subProblem + 1);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     * 法二：（针对暴力解法消除重叠子问题）带备忘录的递归，自顶向下
     */
    int[] memo;

    public int coinChange2(int[] coins, int amount) {

        //理解：amount+1：应该是说总共需要的数量不会超过？？？还没理解
        memo = new int[amount + 1];
        //备忘录初始化为一个不会被取到的特殊值，代表还未被计算
        Arrays.fill(memo, -666);

        return dp2(coins, amount);
    }

    private int dp2(int[] coins, int amount) {
        //base case
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        //查备忘录，防止重复计算
        if (memo[amount] != -666) {
            return memo[amount];
        }

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {

            //递归，计算子问题的结果
            int subProblem = dp2(coins, amount - coin);
            if (subProblem == -1) {
                continue;
            }
            res = Math.min(res, subProblem + 1);
        }
        //把计算结果存入备忘录
        memo[amount] = (res == Integer.MAX_VALUE) ? -1 : res;
        return memo[amount];
    }

    /**
     * 法三：dp数组的迭代解法，自底向上
     * dp 数组的定义：当目标金额为 i 时，至少需要 dp[i] 枚硬币凑出。
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        //数组大小为amount+1，初始值也为amount+1
        //为啥 dp 数组中的值都初始化为 amount + 1 呢，因为凑成 amount 金额的硬币数最多只可能等于 amount（全用 1 元面值的硬币），
        // 所以初始化为 amount + 1 就相当于初始化为正无穷，便于后续取最小值。
        // 为啥不直接初始化为 int 型的最大值 Integer.MAX_VALUE 呢？因为后面有 dp[i - coin] + 1，这就会导致整型溢出。
        Arrays.fill(dp, amount + 1);

        //base case
        dp[0] = 0;
        //外层for循环在遍历所有状态的所有取值
        for (int i = 0; i < dp.length; i++) {
            //内层for循环在求所有选择的最小值
            for (int coin : coins) {
                //子问题无解，跳过
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }
}
