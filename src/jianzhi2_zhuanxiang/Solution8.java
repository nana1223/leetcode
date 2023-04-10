package jianzhi2_zhuanxiang;

/**
 * 剑指 Offer II 008. 和大于等于 target 的最短子数组
 * <p>
 * 思路：双指针
 * 1.两指针都从最左开始
 * 2.如果滑动窗口里的和大于等于target，左指针右移；如果小于，右指针右移；如果等于，更新滑动窗口的长度值。O(n)
 * <p>
 *
 * 反思：认真审题，是大于等于！！！
 *
 */
public class Solution8 {

    public static int minSubArrayLen(int target, int[] nums) {
        int res = Integer.MAX_VALUE;

        int tempSum = 0;
        int left, right;
        left = right = 0;
        while (right < nums.length) {

            tempSum += nums[right++];
            while (tempSum >= target) {
                res = Math.min(res, right - left + 1);
                tempSum -= nums[left++];
            }
        }

        return res == Integer.MAX_VALUE ? 0 : res;

    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 2, 4, 3};
        minSubArrayLen(7, nums);
    }
}
