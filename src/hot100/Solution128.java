package hot100;

import java.util.Arrays;

/**
 * 128. 最长连续序列
 * <p>
 * 题意要求：O(n)
 * <p>
 * 思路1：新建一个下标数组，若1处有值设为true，没有值设为false；统计该数组中连续的true的长度。
 * 试过了，这样不太可行，因为这个下标数组的长度是无法提前得知的，而且会非常浪费空间
 *
 * 思路2：先排序，后遍历。这样没办法实现题目要求的复杂度
 *
 * 思路3：
 */
public class Solution128 {

    public int longestConsecutive(int[] nums) {
        //1。排序

        //2。遍历记录
    }
}
