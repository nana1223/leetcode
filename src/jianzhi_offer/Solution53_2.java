package jianzhi_offer;

/**
 * 剑指 Offer 53 - II. 0～n-1中缺失的数字
 * <p>
 * 思路一：暴力解法：挨个检查是否值符合+1递增
 * <p>
 * 思路二：二分法。利用二分法判断在该位置上的值是否应该是本来在该位置上的值。
 * 如果是，则再往右分；如果不是，则再往左分。当left和right相遇时，说明那个位置不是它本来应该出现的数，则
 */
public class Solution53_2 {

    public int missingNumber(int[] nums) {

        int left = 0;
        int right = nums.length - 1;
        //理解：为什么是<=，而不是< ？
        //因为如果是小于的话，那么到等于的时候就处理不到。就会漏情况
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
