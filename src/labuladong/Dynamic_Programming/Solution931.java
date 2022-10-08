package labuladong.Dynamic_Programming;

import java.util.Arrays;

/**
 * 931. 下降路径最小和
 * <p>
 * 算法细节问题：
 * 1、对于索引的合法性检测，返回值为什么是 99999？其他的值行不行？
 * <p>
 * 2、base case 为什么是 i == 0？
 * <p>
 * 3、备忘录 memo 的初始值为什么是 66666？其他值行不行？
 * <p>
 * 看题目给出的数据范围：
 * <p>
 * matrix 是 n x n 的二维数组，其中 1 <= n <= 100；对于二维数组中的元素，有 -100 <= matrix[i][j] <= 100。
 * 假设 matrix 的大小是 100 x 100，所有元素都是 100，那么从第一行往下落，得到的路径和就是 100 x 100 = 10000，也就是最大的合法答案。
 * 类似的，依然假设 matrix 的大小是 100 x 100，所有元素是 -100，那么从第一行往下落，就得到了最小的合法答案 -100 x 100 = -10000。
 * 也就是说，这个问题的合法结果会落在区间 [-10000, 10000] 中。
 * <p>
 * 所以，我们 memo 的初始值就要避开区间 [-10000, 10000]，换句话说，memo 的初始值只要在区间 (-inf, -10001] U [10001, +inf) 中就可以。
 * <p>
 * https://labuladong.github.io/algo/3/25/72/
 */
public class Solution931 {

    int[][] memo;

    public int minFallingPathSum(int[][] matrix) {

        int n = matrix.length;
        int res = Integer.MAX_VALUE;

        //带备忘录的递归，备忘录里的值初始化为 66666
        memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], 66666);
        }

        //终点可能在最后一行的任意一列
        for (int i = 0; i < n; i++) {
            res = Math.min(res, dp(matrix, n - 1, i));
        }
        return res;
    }

    /**
     * dp函数定义：从第一行（matrix[0][..]）向下落，落到位置 matrix[i][j] 的最小路径和为 dp(matrix, i, j)。
     * <p>
     * 对于 matrix[i][j]，只有可能从 matrix[i-1][j], matrix[i-1][j-1], matrix[i-1][j+1] 这三个位置转移过来.
     * 那么，只要知道到达 (i-1, j), (i-1, j-1), (i-1, j+1) 这三个位置的最小路径和，加上 matrix[i][j] 的值，就能够计算出来到达位置 (i, j) 的最小路径和
     */
    private int dp(int[][] matrix, int i, int j) {
        //1.索引合法性检查
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length) {
            //返回一个特殊值
            return 99999;
        }

        //2.base case：已经到第一行了
        if (i == 0) {
            return matrix[i][j];
        }

        //3.查找备忘录，防止重复计算
        if (memo[i][j] != 66666) {
            return memo[i][j];
        }

        //4.状态转移
        memo[i][j] = matrix[i][j] + min(
                dp(matrix, i - 1, j - 1),
                dp(matrix, i - 1, j),
                dp(matrix, i - 1, j + 1));

        return memo[i][j];
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
