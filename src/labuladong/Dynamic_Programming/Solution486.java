package labuladong.Dynamic_Programming;

/**
 * 486. 预测赢家————博弈类问题
 * https://labuladong.github.io/algo/3/28/93/
 * <p>
 * 博弈问题的前提一般都是在两个聪明人之间进行，编程描述这种游戏的一般方法是二维 dp 数组，数组中通过元组分别表示两人的最优决策。
 * 之所以这样设计，是因为先手在做出选择之后，就成了后手，后手在对方做完选择后，就变成了先手。这种角色转换使得我们可以重用之前的结果，典型的动态规划标志。
 * <p>
 * dp 数组含义的解释：
 * dp[i][j].fir = x 表示，对于 piles[i...j] 这部分石头堆，先手能获得的最高分数为 x。
 * dp[i][j].sec = y 表示，对于 piles[i...j] 这部分石头堆，后手能获得的最高分数为 y。
 * <p>
 * base case:当面前只有一个石头堆的时候，对先手来说就是dp[i][i].fir = piles[i]；对后手来说dp[i][i].sec = 0
 */
public class Solution486 {

    public boolean PredictTheWinner(int[] nums) {
        //计算先手和后手分差的 stoneGame 函数
        // 先手的分数大于等于后手，则能赢
        return stoneGame(nums) >= 0;

    }

    class Pair {
        int first;
        int second;

        public Pair(int fir, int sec) {
            this.first = fir;
            this.second = sec;
        }
    }

    private int stoneGame(int[] nums) {
        int n = nums.length;
        //1.定义dp数组 + 初始化dp数组
        Pair[][] dp = new Pair[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                dp[i][j] = new Pair(0, 0);
            }
        }
        //2.base case
        for (int i = 0; i < n; i++) {
            dp[i][i].first = nums[i];
            dp[i][i].second = 0;
        }
        //3.状态转移：推算 dp[i][j] 时需要用到 dp[i+1][j] 和 dp[i][j-1]
        //i为什么从n-2开始往前遍历，建议画出二维的dp数组即可发现【其实动态规划很多时候都需要动手画一下转移过程】
        //j为什么从i+1开始往后遍历，考虑一下dp的定义就知道j>i
        //i,j即不同状态下
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                //先手做选择：先手选择最左边或最右边的分数，【计算时可以由dp的定义写出】，先手是下一局的后手
                int left = dp[i + 1][j].second + nums[i];
                int right = dp[i][j - 1].second + nums[j];
                //先手做完选择，后手的选择随之变化【先手肯定选分高的】
                if (left > right) {
                    dp[i][j].first = left;
                    dp[i][j].second = dp[i + 1][j].first;
                } else {
                    dp[i][j].first = right;
                    dp[i][j].second = dp[i][j - 1].first;
                }
                //反思：博弈问题：1两个人都要选最优的，而且第一个人选完最优的之后，会影响第二个人做选择；
                //2博弈问题中，两个人角色会不断对调，先手在下一局就变成后手，后手下一局变先手
            }
        }
        Pair res = dp[0][n - 1];
        return res.first - res.second;
    }
}
