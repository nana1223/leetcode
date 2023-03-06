package jianzhi_offer;

import java.util.Arrays;

/**
 * 剑指 Offer 40. 最小的k个数
 * <p>
 * 就是排序【快排】
 * <p>
 * 【优化思路】：在快排的基础上优化，因为快排是哨兵，而哨兵可以把数组划分成 最小的k个数 和 其他数字 两部分。利用哨兵base = arr[i]:
 * 递归时：考虑k和i的大小关系，可以不用全部都遍历
 * 若 k < i，则遍历左侧即可；
 * 若 k > i，则遍历右侧
 * 若 k = i, 即可直接返回
 */
public class Solution40 {

    public int[] getLeastNumbers(int[] arr, int k) {
        if (k >= arr.length) {
            return arr;
        }
        // 快排 + k
        return quickSort(arr, 0, arr.length - 1, k);
    }

    private int[] quickSort(int[] arr, int left, int right, int k) {


        // 排序操作
        int base = arr[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && arr[j] >= base) {
                j--;
            }
            while (i < j && arr[i] <= base) {
                i++;
            }
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        arr[left] = arr[i];
        arr[i] = base;

        // 递归
        if (k < i) {
            return quickSort(arr, left, i - 1, k);

        } else if (k > i) {
            return quickSort(arr, i + 1, right, k);
        } else {
            return Arrays.copyOf(arr, k);
        }

    }

    public int[] getLeastNumbers1(int[] arr, int k) {
        int[] res = new int[k];
        //1.快排
        quickSort1(arr, 0, arr.length - 1);
        //2.拼结果
        for (int i = 0; i < k; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    private void quickSort1(int[] arr, int left, int right) {

        //1.递归结束条件
        if (left > right) {
            return;
        }

        //2.排序操作
        int base = arr[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && arr[j] >= base) {
                j--;
            }
            while (i < j && arr[i] <= base) {
                i++;
            }
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        arr[left] = arr[i];
        arr[i] = base;

        //3.递归
        quickSort1(arr, left, i - 1);
        quickSort1(arr, i + 1, right);
    }
}
