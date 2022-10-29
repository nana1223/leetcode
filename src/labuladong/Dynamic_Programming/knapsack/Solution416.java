package labuladong.Dynamic_Programming.knapsack;

import java.util.Arrays;

/**
 * 416. 分割等和子集
 * 问题描述：给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * 转换成背包问题：给一个可装载重量为 sum / 2 的背包和 N 个物品，每个物品的重量为 nums[i]。现在让你装物品，是否存在一种装法，能够恰好将背包装满？
 * 即————是否可以从输入数组中挑选出一些正整数，使得这些数的和 等于 整个数组元素的和的一半。容易知道：数组的和一定得是偶数。
 * <p>
 * 本题与 0-1 背包问题有一个很大的不同，即：
 * 1.0-1 背包问题选取的物品的容积总量 不能超过 规定的总量；
 * 2.本题选取的数字之和需要 恰好等于 规定的和的一半。
 * 这一点区别，决定了在初始化的时候，所有的值应该初始化为 false
 * <p>
 * dp数组的定义：
 * dp[i][j] = x 表示，对于前 i 个物品（i 从 1 开始计数），当前背包的容量为 j 时，若 x 为 true，则说明可以恰好将背包装满，若 x 为 false，则说明不能恰好将背包装满。
 * <p>
 * 注意！：由于 dp 数组定义中的 i 是从 1 开始计数，而数组索引是从 0 开始的，所以第 i 个物品的重量应该是 nums[i-1]，这一点不要搞混
 */
public class Solution416 {

    /**
     * 法一：基本思路：1定义状态和选择 2定义dp数组 3确定base case 4根据选择进行状态转移
     * <p>
     * 法二：进一步优化：注意到dp[i][j]都是通过上一行dp[i-1][…]得来的，之前的数据都用不着，所以可以压缩dp数组的存储，变成一维.减小空间复杂度
     * （唯一需要注意的是 j 应该从后往前反向遍历，因为每个物品（或者说数字）只能用一次，以免之前的结果影响其他的结果。）
     */
    public boolean canPartition1(int[] nums) {

        //1.算一下所有数的sum
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        //特殊情况判断：sum为奇数铁定不能
        if (sum % 2 != 0) {
            return false;
        }
        //2.定义dp数组
        //dp[i][j] = x 表示，对于前 i 个物品（i 从 1 开始计数），当前背包的容量为 j 时，若 x 为 true，则说明可以恰好将背包装满，若 x 为 false，则说明不能恰好将背包装满。
        boolean[][] dp = new boolean[nums.length + 1][sum / 2 + 1];

        //3.base case
        for (int i = 0; i < nums.length + 1; i++) {
            dp[i][0] = true;
        }
        //4.状态转移
        for (int i = 1; i < nums.length + 1; i++) {
            for (int j = 1; j < sum / 2 + 1; j++) {

                if (j - nums[i - 1] < 0) {
                    //背包容量不够了，不能装入第i个物品
                    dp[i][j] = dp[i - 1][j];
                } else {
                    //背包容量还够，选择是否装入第i个物品
                    //逻辑或||的运算规则是一个为真即为真，后续不再计算，一个为假再计算右边的表达式。
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        //理解最终答案：因为dp数组的定义是：
        // dp[i][j] = x 表示，对于前 i 个物品（i 从 1 开始计数），当前背包的容量为 j 时，若 x 为 true，则说明可以恰好将背包装满，若 x 为 false，则说明不能恰好将背包装满。
        //即对于nums所有的物品，当背包容量恰好为sum/2时，的结果
        return dp[nums.length][sum / 2];
    }

    public boolean canPartition(int[] nums) {

        //1.算一下所有数的sum
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        //特殊情况判断：sum为奇数铁定不能
        if (sum % 2 != 0) {
            return false;
        }
        //2.定义dp数组
        boolean[] dp = new boolean[sum / 2 + 1];
        //3.base case
        dp[0] = true;
        //4.状态转移
        for (int i = 1; i < nums.length; i++) {
            for (int j = sum / 2; j >= 0; j--) {

                if (j - nums[i] >= 0) {
                    dp[j] = dp[j] || dp[j - nums[i]];
                }
            }
        }
        return dp[sum / 2];
    }
}
