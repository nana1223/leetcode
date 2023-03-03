package jianzhi_offer;

/**
 * 面试题13. 机器人的运动范围
 * <p>
 * 思路：dfs + 剪枝
 * 1.递归参数：机器人当前所在方格的行列ij，k
 * 2.递归结束条件：1.行列之和大于k回退 2.越界回退 3.数位和大于k 4.什么时候算结束？走到m-1 n-1但是可以优化的是有的格子之后都会大于k就不用走了
 * 3.递推工作：
 * 1）走过的格子标记为true（ boolean[][] board）
 * 2）【可达解】往右和往下走，用 + 来连接 因为要求的是走过格子的总数
 * 【因为本题有k的限制。根据可达解的结构和连通性，易推出机器人可 仅通过向右和向下移动，访问所有可达解 。】
 */
public class Solution13 {
    public int movingCount(int m, int n, int k) {

        boolean[][] board = new boolean[m][n];
        return dfs(board, 0, 0, k);
    }

    private int dfs(boolean[][] board, int i, int j, int k) {

        //1.递归结束条件：1.行列之和大于k回退 2.越界回退 3.访问过的格子回退
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || bitSum(i, j) > k || board[i][j]) {
            return 0;
        }

        //2. 走过的要标记
        board[i][j] = true;

        //3.往右和往下走【注意：还得+1，把当前格子算上】
        int res = dfs(board, i + 1, j, k) + dfs(board, i, j + 1, k) + 1;
        return res;
    }

    /**
     * 算方格ij的数位和
     */
    private int bitSum(int i, int j) {
        int sum = 0;
        while (i != 0) {
            sum += i % 10;
            i /= 10;
        }
        while (j != 0) {

            sum += j % 10;
            j = j / 10;
        }
        return sum;

    }
}
