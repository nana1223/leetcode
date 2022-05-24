package labuladong.二叉树;

import java.util.ArrayList;
import java.util.List;

/**
 * 315. 计算右侧小于当前元素的个数
 * 思路一：双层循环（会超出时间限制）
 * 思路二：因为暴力会超时，即数组过长，就用分治的思路：把一整个数组拆分成短的，分别处理结果 最后再合并。——即归并排序的思路
 * 在合并的时候，左半部分nums[i]大于右半部分的nums[j], 则左半部分nums[i]大于右半部分nums[j]~nums[right]所有，就可以更新count值
 * （目前：思路懂了 代码还不是很懂）
 */
public class Solution315 {

    //原数组的索引数组
    private int[] index;
    //记录结果
    private int[] count;


    public List<Integer> countSmaller(int[] nums) {

        List<Integer> res = new ArrayList<>();
        count = new int[nums.length];

        //1.构建索引数组。把原数组中的下标与数据的对应关系存下，（因为分治归并排序顺序会打乱）。这里不用map的原因是元素有可能会重复
        index = new int[nums.length];
        for (int i = 0; i < index.length; i++) {
            index[i] = i;
        }

        //2.分治 + 合并
        sort(nums, 0, nums.length - 1);

        for (int i = 0; i < count.length; i++) {
            res.add(count[i]);
        }
        return res;

    }

    private void sort(int[] nums, int left, int right) {
        //在区间只剩一个数字的时候 没有比较，则终止条件
        if (left == right) {
            return;
        }

        int mid = (left + right) / 2;
        //将无序数组不断划分 直至数组只有一个元素时
        sort(nums, left, mid);
        sort(nums, mid + 1, right);

        //将两个有序数组合并，并且合作的过程中要统计count[i]
        merge(nums, left, mid, right);

    }

    private void merge(int[] nums, int left, int mid, int right) {

        //临时数组，用来放排好序的数
        int[] temp = new int[right - left + 1];
        //临时的索引数组，放临时数组中每个元素对应的下标
        int[] tempIndex = new int[right - left + 1];

        //用双指针操作合并
        int i = left, j = mid + 1;
        //k用来标识临时数组的下标
        int k = 0;

        while (i <= mid && j <= right) {

            if (nums[i] > nums[j]) {

                //左半部分nums[i]大于右半部分的nums[j], 则左半部分nums[i]大于右半部分nums[j]~nums[right]所有 ，更新count数组
                count[index[i]] += right - j + 1;

                //降序排列
                tempIndex[k] = index[i];
                temp[k++] = nums[i++];

            } else {

                tempIndex[k] = index[j];
                temp[k++] = nums[j++];
            }
        }
        //左半部分还有剩余数据
        while (i <= mid) {
            tempIndex[k] = index[i];
            temp[k++] = nums[i++];

        }
        //右半部分还有剩余数据
        while (j <= right) {
            tempIndex[k] = index[j];
            temp[k++] = nums[j++];
        }

        //把临时数组赋回原值
        for (int l = 0; l < right - left + 1; l++) {
            nums[l + left] = temp[l];
            index[l + left] = tempIndex[l];
        }
    }

//    //这种方法会超出时间限制
//    public List<Integer> countSmaller1(int[] nums) {
//
//        List<Integer> res = new ArrayList<>();
//        int tempTimes;
//        for (int i = 0; i < nums.length; i++) {
//            tempTimes = 0;
//            for (int j = i + 1; j < nums.length; j++) {
//                if (nums[j] < nums[i]) {
//                    tempTimes++;
//                }
//            }
//            res.add(tempTimes);
//        }
//        return res;
//    }
}
