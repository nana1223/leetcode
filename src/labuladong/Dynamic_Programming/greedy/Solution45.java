package labuladong.Dynamic_Programming.greedy;

import java.util.Arrays;

/**
 * 45. 跳跃游戏 II
 * 使用最少的跳跃次数到达数组的最后一个位置。 假设你总是可以到达数组的最后一个位置。
 */
public class Solution45 {
    /**
     * 法一：动态规划
     * 1.定义dp：dp(nums, p)从索引p跳到最后一格，至少需要dp(nums, p)步
     * 2.所求结果就是dp(nums, 0)
     * 3.base case：当p超过最后一格时，不需要跳跃
     * 4.状态转移
     */
    private int[] memo;

    public int jump1(int[] nums) {
        int n = nums.length;
        memo = new int[n];
        Arrays.fill(memo, n);
        return dp(nums, 0);
    }

    private int dp(int[] nums, int p) {
        int n = nums.length;
        //1.base case
        if (p >= n - 1) {
            return 0;
        }

        //2.查备忘录
        if (memo[p] != n) {
            return memo[p];
        }

        //3.状态转移：根据选择去写状态转移方程，那就考虑有什么选择：即走到当前这步的时候可以跳一步两步nums[i]步
        int stepNum = nums[p];
        for (int i = 1; i <= stepNum; i++) {
            //穷举每一次选择的结果，取所有的最小结果
            int subProblem = dp(nums, p + i);
            //注意这个状态转移方程是subProblem + 1【+1代表跳了一次】
            memo[p] = Math.min(memo[p], subProblem + 1);
        }
        return memo[p];
    }

    /**
     * 法二：贪心算法：
     * 思路：注意应该怎么考虑贪心算法：肯定下意识会想每次都跳当前索引下能跳的最大值，但是这样并不是最优；
     * 最优是最远距离才是最优，所以要考虑的是当前索引能跳到的格子中数字最大的
     * 比如上图这种情况，我们站在索引 0 的位置，可以向前跳 1，2 或 3 步，你说应该选择跳多少呢？[3,1,4,2]
     * 显然应该跳 2 步调到索引 2，因为 nums[2] 的可跳跃区域涵盖了索引区间 [3..6]，比其他的都大。
     * <p>
     * 核心思路：每次在上次能跳到的范围（end）内选择一个能跳的最远的位置（也就是能跳到max_far位置的点）作为下次的起跳点 ！
     * <p>
     * 反思：贪心算法是选局部最优，但是也要考虑哪种局部最优
     * 本道题考虑的局部最优就是局部下一次跳完最远的距离
     */
    public static int jump(int[] nums) {
        int res = 0;
        int n = nums.length;

        int furthest = 0;
        int furthestIndex = 0;
        //起跳点
        int startPoint = 0;
        //注意起跳点不能等于n-1，会多算一个
        while (startPoint < n - 1) {
            int curRange = nums[startPoint];
            //以startPoint为起跳点找出在可跳范围内选一个能跳的最远的位置
            for (int i = 1; i <= curRange; i++) {
                //考虑数组越界情况
                if (startPoint + i >= n - 1) {
                    return ++res;
                }
                if (nums[startPoint + i] + startPoint + i > furthest) {
                    furthest = Math.max(furthest, nums[startPoint + i] + startPoint + i);
                    furthestIndex = startPoint + i;
                }
            }
            startPoint = furthestIndex;
            res++;
        }
        return res;
    }

    public static void main(String[] args) {
        jump(new int[]{2, 3, 1, 1, 4});
    }
}
