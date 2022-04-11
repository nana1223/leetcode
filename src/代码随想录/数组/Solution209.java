package 代码随想录.数组;

import java.util.Arrays;

/**
 * 长度最小的子数组
 * <p>
 * 法一：labuladong.数组.前缀和技巧 + 二分查找法 ：
 * 申请一个数组 sums[i]表示从sums[0]到sums[i-1]的元素和，所以sums的长度需要nums.length + 1；
 * sums[j] - sums[i]就是[i,j)的总和
 * <p>
 * 启发：元素全为正的数组，它的前缀和数组恰好是有序的，那么二分法就可能可以用得上了
 * 反思：前缀和数组的应用：1 生成前缀和操作， 2 前缀和数组指定的两位置相减 就是原数组这两位置之间子数组的和
 * <p>
 * 法二：labuladong.数组.滑动窗口（也可以理解为双指针，快慢指针），左右指针分别维护窗口的左右
 * 若和小于target，就右指针往右，若和大于等于target，就左指针往右；期间不断更新窗口最小值
 */
public class Solution209 {

    public int minSubArrayLen1(int target, int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        //1.生成前缀和数组
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        //2.因为数组nums是正整数，所以前缀和数组sums是递增的，可以用二分查找法，找到和≥target的长度最小的子数组（怎么用二分查找应用本题？)
        int result = Integer.MAX_VALUE;
        for (int i = 1; i < sums.length; i++) {
            //对于每个开始下标 i，可通过二分查找得到大于或等于 i 的最小下标loc，使得sums[loc]−sums[i−1]≥target
            int t = target + sums[i - 1];
            //用库里的二分查找算法函数：如果找到就会返回值的下标，如果没找到就会返回一个负数，这个负数取反之后就是查找的值应该在数组中的位置
            int loc = Arrays.binarySearch(sums, t);
            if (loc < 0) {
                loc = -loc - 1;
            }
            if (loc < sums.length) {
                result = Math.min(result, loc - (i - 1));
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    public int minSubArrayLen(int target, int[] nums) {

        int left = 0, right = 0;
        int tempSum = 0;
        int res = Integer.MAX_VALUE;

        while (right < nums.length) {
            tempSum += nums[right];
            while (tempSum >= target) {
                res = Math.min(res, right - left + 1);
                tempSum -= nums[left];
                left++;
            }
            right++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

}
