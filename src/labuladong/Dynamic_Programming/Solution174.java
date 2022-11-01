package labuladong.Dynamic_Programming;

import java.util.Arrays;

/**
 * 174. 地下城游戏
 * 思路和最小路径和64类似
 * <p>
 * 动归：思路：1.先定义dp 2.找到base case 3.针对具体的i j 看看怎么状态转移 列出状态转移方程  4.看有没有重叠子问题优化加备忘录
 * <p>
 * 1.定义dp：注意这里和64定义dp函数是不同的！！！
 * 因为如果还是dp(grid, i, j)表示从[0][0]到[i][j]的最大路径和，那么
 * 按照 dp 函数的定义，你只知道「能够从左上角到达 B 的最小生命值」，但并不知道「到达 B 时的生命值」。而「到达 B 时的生命值」是进行状态转移的必要参考。
 * <p>
 * 所以：正确的dp定义：从 grid[i][j] 到达终点（右下角）所需的最少生命值是 dp(grid, i, j)。
 * 要求的是dp(0, 0)
 * <p>
 * 2.base case:  1特殊值：i==m-1 j==n-1  2越界：i==m j==n
 * 3.状态转移：想求 dp(0, 0)，那就应该试图通过 dp(i, j+1) 和 dp(i+1, j) 推导出 dp(i, j)，这样才能不断逼近 base case，正确进行状态转移。
 * 4.有重叠子问题，存memo
 * <p>
 * <p>
 * 还不是很懂？？？？？？？？？？？？？？？
 * <p>
 * 这个讲的比较好懂 https://leetcode.cn/problems/dungeon-game/solution/di-xia-cheng-you-xi-by-leetcode-solution/
 * 对于 dp[i][j]，我们只要关心dp[i][j+1] 和 dp[i+1][j] 的最小值 minn。
 * 记当前格子的值为 dungeon(i,j)，那么在坐标 (i,j)(i,j) 的初始值只要达到 minn−dungeon(i,j) 即可。同时，初始值还必须大于等于 1。这样我们就可以得到状态转移方程：
 * dp[i][j]=max(min(dp[i+1][j],dp[i][j+1])−dungeon(i,j),1)
 * 最终答案即为 dp[0][0]。
 * <p>
 */
public class Solution174 {

    int[][] memo;

    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        //1.定义备忘录并初始化
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        // 我们想计算左上角到右下角所需的最小生命值
        return dp(dungeon, 0, 0);
    }

    private int dp(int[][] dungeon, int i, int j) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        //1.base case （1dp[m-1][n-1] 和 2越界值）
        if (i == m - 1 && j == n - 1) {
            return dungeon[i][j] >= 0 ? 1 : -dungeon[i][j] + 1;
        }
        if (i == m || j == n) {
            return Integer.MAX_VALUE;
        }

        //2.查备忘
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        //3.状态转移
        int res = Math.min(
                dp(dungeon, i + 1, j),
                dp(dungeon, i, j + 1)
        ) - dungeon[i][j];
        //骑士的生命值至少为1
        memo[i][j] = res <= 0 ? 1 : res;
        return memo[i][j];
    }
}
