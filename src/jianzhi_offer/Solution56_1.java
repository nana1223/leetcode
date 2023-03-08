package jianzhi_offer;

import java.util.*;

/**
 * 剑指 Offer 56 - I. 数组中数字出现的次数
 * <p>
 * 找出这两个只出现一次的数字
 * <p>
 * 因为时间复杂度要O(n)，则首先排除暴力和哈希表统计
 * 思路：【异或运算】：两个相同数字异或为0。所以把数组中所有数字全部异或，然后不为0的就是那两只出现了一次的数字
 * 【算法】：
 * 先对所有数字进行一次异或，得到两个出现一次的数字的异或值。
 * 在异或结果中找到任意为1的位。
 * 根据这一位对所有的数字进行分组。
 * 在每个组内进行异或操作，得到两个数字。
 */
public class Solution56_1 {

    public int[] singleNumbers(int[] nums) {
        //1.所有数字的异或值x
        int x = 0;
        for (int num : nums) {
            x ^= num;
        }
        // 2.在x中找到第几位为1
        int target = 1;
        //如果target第一个二进制位不为1，就将target左移一位位0010，然后与相与，判断x第二位是否为一.按此循环，直到找到x的第一个为1的二进制位
        while ((target & x) == 0) {
            target = target << 1;
        }
        // 3.根据该位把所有数组分成两部分。（使得连个只出现一次的数字在不同的组，相同的数字在相同的组）
        int a = 0, b = 0;
        for (int num : nums) {
            if ((num & target) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        return new int[]{a, b};
    }

}
