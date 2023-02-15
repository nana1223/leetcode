package jianzhi_offer;

/**
 * 剑指 Offer 04. 二维数组中的查找
 * <p>
 * 思路：要找出数组中是否有target值。查找算法其实就是遍历过程
 * 因为这个二维数组有结构特性，所以要思考利用结构特性看看二维数组的高效遍历算法
 * （从左到右从上到下都是递增的）
 * 思路：【坐标轴法】用两个指针，分别是横的走的和竖的走的。遇到一个数先横的走，若大于target则再竖着走，若还是大 则返回false；以及若走到边界也要判断
 * 错误想法：一开始想的是从左上角开始，先往右，没有合适的再回退一步再往下（但其实回退两步的下面也有可能）
 * 纠正：【以左下角为坐标原点开始】若当前数大于target，则往上；若当前数小于target，则往右
 */
public class Solution04 {

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {

        //注意：必须把matrix.length == 0的判断写在matrix[0].length == 0的前面（因为第一个符合第二个就不看了 不然就没有matrix[0]会报错）
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int n = matrix.length;
        int m = matrix[0].length;

        //从左下角开始
        int row = n - 1;
        int column = 0;
        while (row >= 0 && column < m) {
            if (matrix[row][column] == target) {
                return true;
            } else if (matrix[row][column] > target) {
                row--;
            } else if (matrix[row][column] < target) {
                column++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
//        findNumberIn2DArray(matrix, 20);
        System.out.println(matrix[4][0]);
    }
}
