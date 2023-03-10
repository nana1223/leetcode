package jianzhi_offer;

/**
 * 剑指 Offer 29. 顺时针打印矩阵
 * 顺时针，从外向里，即【从左向右，从上到下，从右到左，从下到上】的顺序，【每次走完一个方向，然后更新边界值】
 * <p>
 * 思路：定义4个边界：top, bottom, left, right
 */
public class Solution29 {

    public int[] spiralOrder(int[][] matrix) {

        int m = matrix.length;
        if (m == 0) {
            return new int[]{};
        }
        int n = matrix[0].length;
        if (n == 0) {
            return new int[]{};
        }

        int[] res = new int[m * n];
        int idx = 0;
        //1.定义4个边界
        int left = 0, right = n - 1, top = 0, bottom = m - 1;

        //2.开始模拟
        while (idx < m * n) {

            //2.1从左到右
            for (int i = left; i <= right; i++) {
                res[idx++] = matrix[top][i];
            }
            //更新上边界
            top++;
            //判断是否越界
            if (top > bottom) {
                break;
            }

            //2.2从上到下
            for (int i = top; i <= bottom; i++) {
                res[idx++] = matrix[i][right];
            }
            //更新右边界
            right--;
            if (right < left) {
                break;
            }

            //2.3从右到左
            for (int i = right; i >= left; i--) {
                res[idx++] = matrix[bottom][i];
            }
            //更新下边界
            bottom--;
            if (bottom < top) {
                break;
            }

            //2.3从下到上
            for (int i = bottom; i >= top; i--) {
                res[idx++] = matrix[i][left];
            }
            //更新左边界
            left++;
            if (left > right) {
                break;
            }
        }
        return res;
    }
}
