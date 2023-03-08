package jianzhi_offer;

/**
 * 剑指 Offer 15. 二进制中1的个数
 * <p>
 * 思路：n右移，挨个和1按位与，若结果是1则加一，若结果是0则continue
 */
public class Solution15 {
    public int hammingWeight(int n) {
        int sum = 0;
        while (n != 0) {
            sum += n & 1;
            //本题要求把数字n 看作无符号数，因此使用 无符号右移>>> 操作
            n >>>= 1;
        }
        return sum;
    }
}
