package jianzhi_offer;

/**
 * 剑指 Offer 53 - I. 在排序数组中查找数字 I
 * 统计一个数字在排序数组中出现的次数。
 * <p>
 * 思路：因为是排序数组，so 可以考虑二分查找
 * 先用最简单的O(n)暴力法试一下（试过了，超时）
 * 所以还是试一下二分查找，二分查找有两种实现：递归、迭代
 * <p>
 * 注意要统计的是出现的次数。所以其实是用的【两次二分查找】。
 * 利用两次二分法分别确定target的左右边界（左右边界为target值序列的左/右一位，因此最终结果是right-left-1）
 * <p>
 * 查找右边界时，跳出循环时start指向右边界
 * 查找左边界时，跳出循环时end指向左边界
 * <p>
 * 反思：知道根是二分查找，但是因为查找的是出现次数。看怎么把二分查找变形。比如本题：进行两次二分查找
 */
public class Solution53 {

    public int search(int[] nums, int target) {

        if (nums.length == 0) {
            return 0;
        }
        int rightIndex;
        int leftIndex;
        int start = 0;
        int end = nums.length - 1;
        int mid;
        //1. 查找右边界
        while (start <= end) {
            mid = (start + end) / 2;
            if (target >= nums[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        rightIndex = start;
        //2.查找左边界
        start = 0;
        end = nums.length - 1;
        while (start <= end) {
            mid = (start + end) / 2;
            if (target <= nums[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        leftIndex = end;
        return rightIndex - leftIndex - 1;
    }


}
