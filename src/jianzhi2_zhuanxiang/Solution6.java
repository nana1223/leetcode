package jianzhi2_zhuanxiang;

/**
 * 剑指 Offer II 006. 排序数组中两个数字之和
 * <p>
 * 【思路】：首先，数组是有序的，可以利用这一特点优化。从左右两边开始试试【左右指针】
 * 若target大于两数之和，则左指针右移右指针左移
 * 若小于，则右指针左移
 * 若相等，可直接返回
 */
public class Solution6 {
    public static int[] twoSum(int[] numbers, int target) {

        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            if ( target == numbers[left] + numbers[right]) {
                break;
            } else if (target > numbers[left] + numbers[right]) {
                left++;
            } else if (target < numbers[left] + numbers[right]) {
                right--;
            }
        }
        return new int[]{left, right};
    }

    public static void main(String[] args) {
        twoSum(new int[]{1,2,4,6,10},8);
    }
}
