package labuladong.数组.前缀和技巧;

public class NumMatrix304 {
    //preSum表示的是[0，0，i,j]区域的元素和
    int[][] preSum;

    public NumMatrix304(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        //preSum的长度得是[m+1][n+1]，否则sumRegion算的时候边界会溢出 x-1
        preSum = new int[m+1][n+1];

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                preSum[i][j] = preSum[i-1][j] + preSum[i][j-1] - preSum[i-1][j-1] + matrix[i-1][j-1];
            }
        }

    }
//小学数学，田字形，已知整体面积，上面面积，左边面积，左上面积，求右下角矩形的面积。 右下角矩形的面积=整体面积-上面面积-左边面积+左上面积
    public int sumRegion(int x1, int y1, int x2, int y2) {
        int res = 0;
        res = preSum[x2+1][y2+1] + preSum[x1][y1] - preSum[x1][y2+1] - preSum[x2+1][y1];
        return res;
    }
}
