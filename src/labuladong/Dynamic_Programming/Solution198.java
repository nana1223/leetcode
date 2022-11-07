package labuladong.Dynamic_Programming;

import java.util.Arrays;

/**
 * 198. 打家劫舍
 * <p>
 * 1.定义状态和选择：状态就是已经抢的钱在变化；选择就是走到当前这个房子面前抢还是不抢
 * 2.定义dp：dp(i)=x表示走到房子i面前已抢到的钱数x
 * 3.base case：i=n时dp[i]=0
 * 4.状态转移：当前房子抢了或者不抢两者取最大钱数
 * 5.重叠子问题
 */
public class Solution198 {

    int[] memo;

    public int rob(int[] nums) {
        //注意memo的初始化别放在递归函数里面
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return dp(0, nums);
    }

    private int dp(int i, int[] nums) {

        //1.base case
        if (i >= nums.length) {
            return 0;
        }
        //2.查备忘
        if (memo[i] != -1) {
            return memo[i];
        }
        //3.状态转移
        memo[i] = Math.max(
                nums[i] + dp(i + 2, nums),
                dp(i + 1, nums)
        );
        return memo[i];
    }
}
