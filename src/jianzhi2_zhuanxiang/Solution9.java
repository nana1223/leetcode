package jianzhi2_zhuanxiang;

import java.math.BigDecimal;

/**
 * 剑指 Offer II 009. 乘积小于 K 的子数组 的个数
 * <p>
 * 思路：滑动窗口
 * 若窗口乘积大于等于k时，考虑左端点右移。直到窗口乘积不再满足大于等于k时，就可以得到每个右端点的最远左端点
 *
 * 【反思】：思路要清晰，知道一步一步怎么写，不能乱
 * 【反思2】：滑动窗口方法 1要知道两个指针起始位置，2要知道什么情况移动左右指针  3要知道移动左右指针时候有什么要同步更新的变量
 */
public class Solution9 {

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        //特殊边界情况要考虑
        if (k <= 1) {
            return 0;
        }
        int res = 0;
        int window = 1;

        int left, right;
        left = right = 0;

        while (left <= right && right < nums.length) {
            //1.记录当前窗口乘积值
            window *= nums[right];
            while (window >= k) {
                window /= nums[left++];
            }

            //2.记录当前窗口乘积值符合条件时的子数组个数
            //以r为结尾，子数组必须包含nums[r]这个数，因此存在
            //nums[r]
            //nums[r], nums[r - 1]
            //nums[r], nums[r - 2]
            //nums[r], nums[r - 2]... , nums[l]
            //即 r - l + 1个符合条件的子数组
            res += right - left + 1;

            //3.窗口右移
            right++;
        }
        return res;

    }
}
