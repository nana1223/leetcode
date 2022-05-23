package labuladong.二叉树;

/**
 * 912. 排序数组
 * 用快排
 */
public class Solution912 {


    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length + 1);
        return nums;
    }

    /**
     * 归并排序————就是后序遍历
     * 而归并排序属于二叉树中的分解思路，对子树再进行排序和合并
     * 在每个节点的后序位置（左右子节点已经被排好序）的时候执行 merge 函数，合并两个子节点上的子数组
     */
    public static void mergeSort(int[] arr, int left, int right) {
        //先分治再归并。递归结束条件即分治到只有一个值的时候，开始归并
        if (left == right) {
            return;
        } else {
            int middle = (left + right) / 2;
            // 对中间值左边进行排序----不断拆分
            mergeSort(arr, left, middle);
            // 对中间值右边进行排序----不断拆分
            mergeSort(arr, middle + 1, right);
            // 将左右两个排好序的数组进行合并
            merge(arr, left, middle, right);
        }
    }

    /**
     * 一次归并过程，左右两个排好序的数组进行合并，这两个数组在原arr中下标分别为arr[left,middle]和arr[middle+1,right]
     */
    private static void merge(int[] arr, int left, int middle, int right) {

        //新建一个临时数组，用来存放排好序的数
        int[] temp = new int[arr.length];

        //i指向左边数组的第一个下标，j指向右边数组的第一个下标, k指向临时数组的最左边下标
        int i = left, j = middle + 1, k = left;
        while (i <= middle && j <= right) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        //能走到这里有两种情况,或者i>middle了,或者j>right了,也就是左边或右边至少有一个数组已经全部放到新数组temp里了，再把剩余的直接放进去即可
        while (i <= middle) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        //再将排好序的temp数组赋给arr
        while (left <= right) {
            arr[left] = temp[left++];
        }
    }
}
