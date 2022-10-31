package labuladong.Dynamic_Programming;

import java.util.HashMap;

/**
 * 494. 目标和
 * <p>
 * <p>
 * 这道题要反复看！！！！！！！！！！！！！！！！！！！！！！！！！！（有很多细节还没弄明白）
 */
public class Solution494 {

    int res = 0;

    /**
     * 法一：回溯法
     */
    public int findTargetSumWays1(int[] nums, int target) {

        if (nums.length == 0) {
            return 0;
        }
        backtrack(nums, 0, 0, target);
        return res;
    }

    //理解：i是路径，+-号是选择列表，参数的sum会根据选择列表的选择而变动
    private void backtrack(int[] nums, int index, int sum, int target) {
        //1.结束条件
        if (index == nums.length) {
            if (sum == target) {
                res++;
            }
            return;
        }
        //2.回溯框架

        //2.1做选择，穷举，撤销选择————选＋号
        sum += nums[index];
        backtrack(nums, index + 1, sum, target);
        sum -= nums[index];

        //2.2做选择、穷举、撤销选择————选-号
        sum -= nums[index];
        backtrack(nums, index + 1, sum, target);
        sum += nums[index];
    }

    /**
     * 法二：动态规划
     * 动态规划之所以比暴力算法快，是因为动态规划技巧消除了重叠子问题。
     * 如何发现重叠子问题？看是否可能出现重复的「状态」。对于递归函数来说，函数参数中会变的参数就是「状态」。
     * 对于 backtrack 函数来说，会变的参数为 i 和 sum。
     * 状态 (i, sum) 可以用备忘录技巧进行优化的。
     * 把「状态」转化为字符串作为哈希表的键，这是一个常用的小技巧。
     */

    public int findTargetSumWays2(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        return dp(nums, 0, 0, target);
    }

    //备忘录：键是(i, sum)，值是对应的计算结果
    HashMap<String, Integer> memo = new HashMap<>();

    private int dp(int[] nums, int index, int sum, int target) {
        //1.base case
        if (index == nums.length) {
            if (sum == target) {
                return 1;
            }
            return 0;
        }
        String key = index + "," + sum;

        //2.查memo，避免重复计算
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        //3.状态转移
        int result = dp(nums, index + 1, sum + nums[index], target) + dp(nums, index + 1, sum - nums[index], target);
        memo.put(key, result);
        return result;
    }

    /**
     * 法三：转换成 动态规划中的 背包问题中的 子集划分问题
     * 理解：
     * 把 nums 划分成两个子集 A 和 B，分别代表分配 + 的数和分配 - 的数，那么他们和 target 存在如下关系：
     * sum(A) - sum(B) = target
     * sum(A) = target + sum(B)
     * sum(A) + sum(A) = target + sum(B) + sum(A)
     * 2 * sum(A) = target + sum(nums)
     * 即sum(A) = (target + sum(nums)) / 2，
     * 也就是把原问题转化成：nums 中存在几个子集 A，使得 A 中元素的和为 (target + sum(nums)) / 2？
     * <p>
     * 1. 明确状态和选择
     * 2.明确dp定义：dp[i][j] = x 表示，若只在 nums 的前 i 个元素中选择，若目标和为 j，则最多有 x 种方法划分子集。
     * 3.base case：显然 dp[0][..] = 0，因为没有物品的话，根本没办法装背包；但是 dp[0][0] 应该是个例外，因为如果背包的最大载重为 0，「什么都不装」也算是一种装法，即 dp[0][0] = 1
     * 4.所求答案是 dp[N][sum]，即使用所有 N 个物品，有几种方法可以装满容量为 sum 的背包。
     * 5.根据选择，确定状态转移：【注意要考虑到背包空间是否足够】
     * 5.1如果不把 nums[i] 算入子集，那么恰好装满背包的方法数就取决于上一个状态 dp[i-1][j]，继承之前的结果。
     * 5.2如果把 nums[i] 算入子集，那么只要看前 i - 1 个物品有几种方法可以装满 j - nums[i-1] 的重量就行了，所以取决于状态 dp[i-1][j-nums[i-1]]。
     * ！！！注意： i 是从 1 开始算的，而数组 nums 的索引时从 0 开始算的。
     * 所以 nums[i-1] 代表的是第 i 个物品的重量，j - nums[i-1] 就是背包装入物品 i 之后还剩下的容量。
     * <p>
     * 由于 dp[i][j] 为装满背包的总方法数，所以应该以上两种选择的结果求和，
     */
    public int findTargetSumWays3(int[] nums, int target) {
        int sum = 0;

        for (int index : nums) {
            sum += index;
        }
        //不存在合法的子集划分的情况
        if (sum < Math.abs(target) || (sum + target) % 2 == 1) {
            return 0;
        }
        return subsets3(nums, (sum + target) / 2);
    }

    private int subsets3(int[] nums, int sum) {
        //1.定义dp数组
        int n = nums.length;
        int[][] dp = new int[n + 1][sum + 1];

        //2.base case
        dp[0][0] = 1;

        //3.状态转移
        //理解：为什么i从1开始j从0开始？？？？？？
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < sum + 1; j++) {

                if (j >= nums[i - 1]) {
                    //背包空间还有
                    //两种选择的结果之和
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                } else {
                    //背包空间不足
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][sum];
    }

    /**
     * 法四：对法三的背包问题做空间优化，把dp数组压缩成一维
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;

        for (int index : nums) {
            sum += index;
        }
        //不存在合法的子集划分的情况
        if (sum < Math.abs(target) || (sum + target) % 2 == 1) {
            return 0;
        }
        return subsets(nums, (sum + target) / 2);
    }

    private int subsets(int[] nums, int sum) {
        //1.定义dp数组
        int n = nums.length;
        int[] dp = new int[sum + 1];

        //2.base case
        dp[0] = 1;

        //3.状态转移
        for (int i = 1; i < n + 1; i++) {
            //j要从后往前遍历。理解？？？？？？？？？？？？？？
            //还要理解为什么 i是1到n+1 而j是0到num？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？
            for (int j = sum; j >= 0; j--) {

                if (j >= nums[i - 1]) {
                    //背包空间还有
                    //两种选择的结果之和
                    dp[j] = dp[j] + dp[j - nums[i - 1]];
                } else {
                    //背包空间不足
                    dp[j] = dp[j];
                }
            }
        }
        return dp[sum];
    }
}
