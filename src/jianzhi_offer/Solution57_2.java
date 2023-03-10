package jianzhi_offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer 57 - II. 和为s的连续正数序列
 * <p>
 * 序列要连续
 */
public class Solution57_2 {

    /**
     * 本题：若一开始定成 int[][] 这个长度设的长了就会超出内存限制；
     * 所以弄成 List<int[]>，然后再把List转成数组
     */
    public static int[][] findContinuousSequence1(int target) {

        List<int[]> sumList = new ArrayList<>();
        for (int i = 1; i <= target; i++) {
            int temp = i;
            int firstNum = i;
            //res用来记录这个以i为起点序列的和
            int sum = 0;
            //count用来计数这个序列的长度
            int count = 0;
            //和小于target的时候，一直加
            while (sum < target) {
                sum = sum + temp;
                temp++;
                count++;
            }
            //和若等于target，就生成一个序列，并加到最终结果中
            if (sum == target) {
                int[] res = new int[count];
                for (int j = 0; j < count; j++) {
                    res[j] = firstNum++;
                }
                sumList.add(res);
            }
        }
        return sumList.toArray(new int[sumList.size()][]);
    }


    /**
     * 思路二：滑动窗口
     * 窗口内的所有值的和大于target，左边界右移；
     * 小于target，右边界右移
     * 等于target，记录下
     * 【注意细节】：窗口是左闭右开
     */
    public static int[][] findContinuousSequence(int target) {
        //窗口左右边界，初始位置记为1
        int left = 1;
        int right = 1;
        //记录窗口内数字的和
        int sum = 0;

        List<int[]> res = new ArrayList<>();

        while (left <= target / 2) {

            //右边界右移
            if (sum < target) {
                sum += right;
                right++;
            } else if (sum > target) {
                // 左边界右移
                sum -= left;
                left++;
            } else {
                //符合条件。加入结果集
                int[] arr = new int[right - left];
                for (int i = left; i < right; i++) {
                    arr[i - left] = i;
                }
                res.add(arr);

                //左边界向右移动
                sum -= left;
                left++;

            }
        }
        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args) {
        findContinuousSequence(9);
    }

}
