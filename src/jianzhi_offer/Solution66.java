package jianzhi_offer;

/**
 * 剑指 Offer 66. 构建乘积数组
 * 不能用除法
 * <p>
 * 构建两个数组：L[i] 表示i左侧所有数的乘积，R[i]表示i右侧所有数的乘积。最后结果就是 L[i]*R[i]
 * 特殊的：L[0]=1, R[n-1]=1
 */
public class Solution66 {

    public int[] constructArr(int[] a) {
        int n = a.length;
        if (n == 0) {
            return new int[]{};
        }
        int[] res = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        //初始化边角
        left[0] = 1;
        right[n - 1] = 1;
        //构建left和right数组
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * a[i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] * a[i + 1];
        }

        //构建结果数组
        for (int i = 0; i < n; i++) {
            res[i] = left[i] * right[i];
        }
        return res;
    }
}
