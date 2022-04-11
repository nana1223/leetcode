package labuladong.二叉树;

/**
 * 顺时针螺旋排列:
 * 模拟顺时针的过程（行走到头，走列，列走到头，倒着走行，然后走到头再倒着走列，走到初试行的下一行。然后重复循环，结束条件是？循环次数？把n*n填满）
 * 设置上下左右四个边界
 */
public class Solution59 {

    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];

        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;
        int index = 1;
        while (index <= n * n) {
            for (int i = left; i <= right; i++) {
                res[top][i] = index++;
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                res[i][right] = index++;
            }
            right--;
            for (int i = right; i >= left; i--) {
                res[bottom][i] = index++;
            }
            bottom--;
            for (int i = bottom; i >= top; i--) {
                res[i][left] = index++;
            }
            left++;
        }
        return res;
    }
}
