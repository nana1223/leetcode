package labuladong.二叉树;

/**
 * 493
 * 思路类似315的归并排序
 * <p>
 * 反思：超时的时候就要想想 分治 的思想
 */
public class Solution493 {

    //结果值
    private int count;

    public int reversePairs(int[] nums) {

        sort(nums, 0, nums.length - 1);
        return count;
    }

    private void sort(int[] nums, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = (left + right) / 2;
        sort(nums, left, mid);
        sort(nums, mid + 1, right);

        //1.先统计左右两半部分中翻转对的数量，排序之前统计 符合i<j
        calculate(nums, left, mid, right);
        //2.把左右两半部分合并
        merge(nums, left, mid, right);
    }

    private void calculate(int[] nums, int left, int mid, int right) {

        int i = left, j = mid + 1;
        while (i <= mid) {
            while (j <= right && nums[i] > 2L * nums[j]) {//数据过大 需要long类型
                j++;
            }
            count += j - (mid + 1);
            i++;
        }
    }

    private void merge(int[] nums, int left, int mid, int right) {

        // 存排好序的
        int[] temp = new int[right - left + 1];
        int index = 0;

        int i = left, j = mid + 1;

        while (i <= mid && j <= right) {

            if (nums[i] < nums[j]) {
                temp[index++] = nums[i++];
            } else {
                temp[index++] = nums[j++];
            }
        }
        while (i <= mid) {
            temp[index++] = nums[i++];
        }
        while (j <= right) {
            temp[index++] = nums[j++];
        }
        //把排好序的数组赋值给原数组
        for (int k = 0; k < temp.length; k++) {
            nums[left + k] = temp[k];
        }
    }

    public static void main(String[] args) {
        int[] nums = {5, 4, 3, 2, 1};
        Solution493 solution493 = new Solution493();
        int i = solution493.reversePairs(nums);
        System.out.println(i);
    }
}
