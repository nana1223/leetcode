package jianzhi_offer;

/**
 * 剑指 Offer 16. 数值的整数次方
 * <p>
 * 递归
 * x的n次方就是n个x相乘。
 * 【问题】：n有负数
 */
public class Solution16 {

    /**
     * 快速幂算法
     */
    public double myPow(double x, int n) {

        //【注意】：n要转成用long表示，因为-2^32转为正数时会溢出
        long N = n;
        if (n < 0) {
            return absPow(1 / x, -N);
        } else {
            return absPow(x, N);
        }

    }

    private double absPow(double x, long n) {
        if (n == 0) {
            return 1;
        }
        double half = absPow(x, n / 2);
        return n % 2 == 0 ? half * half : half * half * x;
    }

    /**
     * 超时算法
     */
    public double myPow1(double x, int n) {

        if (n < 0) {
            return realPow(1 / x, -n);
        } else {
            return realPow(x, n);
        }
    }

    private double realPow(double x, int n) {
        if (n == 1) {
            return x;
        }

        return x * myPow(x, n - 1);
    }
}
