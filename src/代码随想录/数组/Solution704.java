package 代码随想录.数组;

/**
 * 二分查找法 704
 *
 * 法一：用非递归实现，while + 左右指针 实现
 * 法二：用递归实现，递归注意两个问题：1调用递归函数需要注意加return  2调用递归函数传参需要注意
 *
 * 比较而言，二者时间好像差不多，但空间复杂度非递归更胜一筹
 */
public class Solution704 {
    /**
     * 用非递归的方法实现二分查找
     *
     * @param nums
     * @param target
     * @return
     */
    public int search1(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (target == nums[middle]) {
                return middle;
            }
            if (target < nums[middle]) {
                right = middle - 1;
            }
            if (target > nums[middle]) {
                left = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 用递归要小心！！！！！！
     * q1: 递归中调用searchBinary前面也得加return，因为return只是结束了本次调用，就像嵌套循环里面的break只会打破里面一层循环，不会一次打破两层循环。
     * 而递归函数不return的话上一次调用结束回来这次之后就会接着往下执行（执行本次调用语句之后的代码），最后所有运行结果都是-1
     * q2:每次递归使用searchBinary时传参，注意left和right是照旧传送，每次改一个边，就是靠近middle那个边 （注意是left和right 不是0和nums.length）递归递归
     */
    public int search(int[] nums, int target) {
        return searchBinary(nums, 0, nums.length - 1, target);
    }

    public int searchBinary(int[] nums, int left, int right, int target) {
        int middle = left + (right - left) / 2;
        if (left <= right) {
            if (target == nums[middle]) {
                return middle;
            }
            if (target < nums[middle]) {
                return searchBinary(nums, left, middle - 1, target);
            }
            if (target > nums[middle]) {
                return searchBinary(nums, middle + 1, right, target);
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Solution704 solution704 = new Solution704();
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int search = solution704.search(nums, 2);
        System.out.println(search);
    }


}
