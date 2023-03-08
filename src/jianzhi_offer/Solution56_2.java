package jianzhi_offer;

/**
 * 剑指 Offer 56 - II. 数组中数字出现的次数 II
 * <p>
 * 【思路】：统计所有数字的各二进制位中 1的出现次数，并对3求余，结果则为只出现一次的数字
 */
public class Solution56_2 {

    public int singleNumber(int[] nums) {
        int res = 0;
        //1.遍历统计所有数字的二进制位出现数字1的次数
        int[] counts = new int[32];
        for (int num : nums) {
            for (int j = 0; j < 32; j++) {
                counts[j] += num & 1;
                num >>>= 1;
            }
        }

        //2.将counts各元素对3求余，则结果为只出现一次的数字 的各二进制位
        for (int j = 0; j < 32; j++) {
            counts[j] %= 3;
        }
        //3.将counts中的各二进位换算成十进制（利用左移、或）
        for (int i = 0; i < counts.length; i++) {
            res <<= 1;
            res |= counts[31 - i];
        }
        return res;
    }
}
