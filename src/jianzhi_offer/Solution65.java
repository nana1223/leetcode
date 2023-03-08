package jianzhi_offer;

/**
 * 剑指 Offer 65. 不用加减乘除做加法
 */
public class Solution65 {

    public static int add(int a, int b) {

        // 当进位为 0 时跳出
        while (b != 0) {
            //1.计算进位
            int carry = a & b;
            //2.计算本位
            a = a ^ b;
            //3.把上一次的进位当作下一次的本位输入
            b = carry << 1;
        }
        return a;
    }

    public static void main(String[] args) {
        add(1, 3);
    }
}
