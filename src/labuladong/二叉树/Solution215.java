package labuladong.二叉树;

/**
 * 215. 数组中的第K个最大元素
 * 思路：用快速排序实现倒叙序列
 */
public class Solution215 {

    public int findKthLargest(int[] nums, int k) {
        quickSort(nums, 0, nums.length - 1);
        return nums[k - 1];
    }

    private void quickSort(int[] nums, int left, int right) {

        if (left > right) {
            return;
        }

        //设基准数
        int base = nums[left];

        //设左右指针开始遍历进行排序，一趟下来base左边的数比base大，右边的数比base小
        int i = left, j = right;
        while (i < j) {

            //注意！！！！！！！！！！！！！！！！！！！！！！！！！必须右指针写在左指针前面 （若左指针写在前面无法实现base左边的数比base大，右边的数比base小）
            while (j > i && nums[j] <= base) {
                j--;
            }
            while (i < j && nums[i] >= base) {
                i++;
            }

            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        //一趟循环结束时，左右指针相遇，交换base和指针相遇处的数
        nums[left] = nums[i];
        nums[i] = base;

        //再对base左右分别进行快排
        quickSort(nums, left, i - 1);
        quickSort(nums, i + 1, right);
    }

    public static void main(String[] args) {
        Solution215 solution215 = new Solution215();

        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        System.out.println(solution215.findKthLargest(nums, 2));
        for (int i : nums) {
            System.out.print(i + " ");
        }
    }
}
