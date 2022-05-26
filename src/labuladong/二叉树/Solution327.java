package labuladong.二叉树;

/**
 * 327. 区间和的个数
 * 题目数据过大 会超时 考虑分治
 * 思路：分治完合并之前 对左右部分进行区间判断
 * 注意点：
 * 1.数据过大要注意的：分治思想 用long存数据（int可能会溢出）
 * 2.要算区间和：考虑用前缀和数组思路, preSum 中的两个元素之差其实就是区间和
 */
public class Solution327 {

    int res = 0;

    public int countRangeSum(int[] nums, int lower, int upper) {

        //前缀和数组
        long[] preSum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = (long) nums[i] + preSum[i];
        }

        //对前缀和数组进行归并排序
        //吐了。。。。。。这个地方注意传参preSum.length - 1不是nums.length-1，粗心写错找bug找了一个小时……
        sort(preSum, 0, preSum.length - 1, lower, upper);
        return res;
    }

    private void sort(long[] preSum, int left, int right, int lower, int upper) {
        if (left == right) {
            //left,right表示的是区间(left, right]内的结果数，因为使用的是前缀和，我猜你想说的是单个数就满足区间和的情况是否能被覆盖，
            // 比如[-2, 3, -7] 要求区间范围3-4， 那么索引1位置的3本身满足条件，它会被left = 1, right 2遍历到
            // preSum数组为 [0, -2, 1, -6], preSum[2] - preSum[1] 为区间 (1,2]的值，也就是元素值 3，即preSum[2] - preSum[1] = nums[1] 此时left = right - 1，
            // left == right的情况在这种方式下视为区间为空，所以可以直接返回0
            return;
        }

        int mid = (left + right) / 2;
        sort(preSum, left, mid, lower, upper);
        sort(preSum, mid + 1, right, lower, upper);

        calculate(preSum, left, mid, right, lower, upper);

        merge(preSum, left, mid, right);

    }

    //目前不是很看得懂！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
    private void calculate(long[] preSum, int left, int mid, int right, int lower, int upper) {

        // 维护左闭右开区间 [start, end) 中的元素和 preSum[i] 的差在 [lower, upper] 中
        int start = mid + 1, end = mid + 1;
        for (int i = left; i <= mid; i++) {
            // 如果 preSum[i] 对应的区间是 [start, end)，
            // 那么 preSum[i+1] 对应的区间一定会整体右移，类似滑动窗口
            while (start <= right && preSum[start] - preSum[i] < lower) {
                start++;//向右寻找最小下限坐标
            }
            while (end <= right && preSum[end] - preSum[i] <= upper) {
                end++;//向右寻找最大上限坐标
            }
            res += end - start;

        }
    }

    private void merge(long[] nums, int left, int mid, int right) {
        // 存排好序的
        long[] temp = new long[right - left + 1];
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
}
