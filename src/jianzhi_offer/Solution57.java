package jianzhi_offer;

/**
 * 剑指 Offer 57. 和为s的两个数字
 * <p>
 * 因为nums是递增排序的，所以可以用【双指针】
 * 左右两指针之和大于target，就右指针往左走；若小于就左指针往右走
 *
 * 细节注意：【防止溢出】：不建议写 nums[left] + nums[right] > target。而是写成 target - nums[left] >? nums[right]
 * 因为：当运算结果超出机器数所能表示的范围时，与最高位产生的进位区别：自动丢弃。
 * ◆ 什么情况下会产生溢出？
 * ➢ 两个异号数相加或【两个同号数相减】（不会溢出）
 * ➢ 两个【同号数相加】或两个异号数相减（可能溢出）
 * 同理：二分查找，(left + right) / 2 可以用left + ((rigth - left) >> 1))代替
 */
public class Solution57 {

    public static int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        int n = nums.length;

        int left = 0;
        int right = n - 1;
        while (left <= right) {
            if (nums[left] + nums[right] > target) {
                right--;
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                res[0] = nums[left];
                res[1] = nums[right];
                return res;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        twoSum(nums, 9);
    }

}
