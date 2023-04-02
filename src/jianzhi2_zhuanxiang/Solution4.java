package jianzhi2_zhuanxiang;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 004. 只出现一次的数字
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 * <p>
 * 【思路一】：用哈希表记录每个元素出现的次数，然后最后找到出现次数为1的那一个
 * <p>
 * 【思路二】：统计所有数字的每一个二进制位中1的个数，然后对3求余，剩下的就是那个只出现一次的数字
 */
public class Solution4 {

    public int singleNumber1(int[] nums) {
        HashMap<Integer, Integer> hash = new HashMap<>(nums.length);
        //1.存哈希表
        for (int num : nums) {
            if (hash.containsKey(num)) {
                hash.put(num, hash.get(num) + 1);
            } else {
                hash.put(num, 1);
            }
        }

        //2.取值
        for (Map.Entry<Integer, Integer> entry : hash.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public int singleNumber(int[] nums) {

        //int型数字位数
        int[] counts = new int[32];

        //1.统计所有数的二进制位中1的个数：元素num每次和1按位与，然后右移
        for (int num : nums) {
            for (int i = 0; i < counts.length; i++) {
                counts[i] += num & 1;
                num >>>= 1;
            }
        }

        //2.对3求余
        for (int i = 0; i < counts.length; i++) {
            counts[i] %= 3;
        }

        //3.换成十进制数：先左移再按位或
        // 为什么这样就可以把二进制转为十进制？？？？？？？？？？？？？？？？？？？？？？？？
        int res = 0;
        for (int i = 0; i < counts.length; i++) {
            res <<= 1;
            res = res | counts[31 - i];
        }
        return res;
    }
}