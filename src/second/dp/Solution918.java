package second.dp;

import java.util.Arrays;

/**
 * 918. 环形子数组的最大和
 * <p>
 * 相比53，来说 分成两种情况
 * 1.这个最大子数组不是环状的，就是说首尾不相连。（就是53那种情况）
 * 2.这个子数组一部分在首部，一部分在尾部，我们可以将这第二种情况转换成第一种情况
 * 第二种情况公式转换过程：
 * max(前面部分的数组+后面部分的数组） = max(数组总和 - 中间部分的数组 ) = 数组总和 + min(中间部分的数组），相当于转换成求最小和数组
 * <p>
 * 【思路反思】：
 * 任何成环的题：想想他不是环的时候怎么处理，然后成环之后就是多一种情况要解决，然后转换问题，转换成不成环的情况
 */
public class Solution918 {

    public int maxSubarraySumCircular(int[] nums) {

        //1.数组总和
        int totalSum = Arrays.stream(nums).sum();
        //2.定义变量。【注意临时的最大最小值记得初始化成0】
        int maxSum = Integer.MIN_VALUE;
        int tempMaxSum = 0;

        int minSum = Integer.MAX_VALUE;
        int tempMinSum = 0;

        for (int i = 0; i < nums.length; i++) {
            //1.求最大子数和
            tempMaxSum = Math.max(tempMaxSum + nums[i], nums[i]);
            maxSum = Math.max(maxSum, tempMaxSum);

            //2.求最小子数和
            tempMinSum = Math.min(tempMinSum + nums[i], nums[i]);
            minSum = Math.min(minSum, tempMinSum);
        }
        //极端情况：如果说这数组的所有数都是负数，要判断一下是否maxSum>0
        return maxSum > 0 ? Math.max(maxSum, totalSum - minSum) : maxSum;
    }
}
