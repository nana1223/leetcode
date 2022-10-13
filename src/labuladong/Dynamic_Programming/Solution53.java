package labuladong.Dynamic_Programming;

/**
 * 53. 最大子数组和
 */
public class Solution53 {

    /**
     * 法一：滑动窗口。在窗口内元素之和大于等于 0 时扩大窗口，在窗口内元素之和小于 0 时缩小窗口，在每次移动窗口时更新答案。
     */
    public int maxSubArray(int[] nums) {

        int left = 0, right = 0;
        int windowSum = 0;
        int maxSum = Integer.MIN_VALUE;
        while (right < nums.length) {
            //1.更新窗口内的元素和
            windowSum += nums[right];
            //2.看是否需要更新
            maxSum = windowSum > maxSum ? windowSum : maxSum;
            //3.看是否需要左边框右移
            while (windowSum < 0) {
                windowSum -= nums[left];
                left++;
            }
            //4.右边框右移（走到这说明windowsSum >= 0）
            right++;
        }
        return maxSum;
    }

    /**
     * 法二：动态规划
     * 法三：前缀和数组
     */
}
