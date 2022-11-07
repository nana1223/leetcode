package labuladong.Dynamic_Programming;

import java.util.Arrays;

/**
 * 213. 打家劫舍 II
 * 相比198改进的点就在于：房子变成了环，首尾不能一起抢。所以只可能有三种情况：
 * 1要么都不被抢；2要么第一间房子被抢最后一间不抢；3要么最后一间房子被抢第一间不抢。 （但是23肯定比1优，所以只考虑两种情况即可）
 */
public class Solution213 {

    int[] memo;

    public int rob(int[] nums) {
        //注意memo的初始化别放在递归函数里面
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        memo = new int[n];
        Arrays.fill(memo, -1);
        int cond1 = dp(0, n - 1, nums);
        //跑第二种情况时，memo之前存的值会影响，所以得给memo重新赋值
        Arrays.fill(memo, -1);
        int cond2 = dp(1, n, nums);
        return Math.max(cond1, cond2);
    }

    private int dp(int i, int j, int[] nums) {

        //1.base case
        if (i >= j) {
            return 0;
        }
        //2.查备忘
        if (memo[i] != -1) {
            return memo[i];
        }
        //3.状态转移
        memo[i] = Math.max(
                nums[i] + dp(i + 2, j, nums),
                dp(i + 1, j, nums)
        );
        return memo[i];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Solution213 solution213 = new Solution213();
        solution213.rob(nums);
    }
}
