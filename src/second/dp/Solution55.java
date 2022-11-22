package second.dp;

/**
 * 55. 跳跃游戏
 * （只要能到就行，不考虑多少跳，那就不断维护总的最远）
 * 【不建议递归，迭代试试】
 * 1.定义状态和选择：状态就是到哪个下标了；选择就是在当前下标跳最远处
 * 2.定义dp：dp(i)=x表示在i处可以跳的最远的地方。这道题可以空间优化，只记一个不断更新究极最远的
 * 3.base case：dp(0)=nums[0]
 * 4.所求就是 i>=n-1
 * 5.状态转移：dp[i] = nums[i]+i。 fur = Math.max(fur, dp[i])
 *
 *
 * 写完发现，其实是贪心的思路。——可以把贪心看作特殊的动态规划，中间省略了记录dp的过程。（可以理解成能用贪心的大部分都能用动态规划？）
 * 贪心选择性质呢，简单说就是：每一步都做出一个局部最优的选择，最终的结果就是全局最优。
 */
public class Solution55 {

    //[3,2,1,0,4]
    public boolean canJump(int[] nums) {
        int n = nums.length;
        //处理特殊情况
        if (n == 1) {
            return true;
        }
        int[] dp = new int[n + 1];
        int furthest = 0;
        for (int i = 0; i < n - 1; i++) {
            //挨个遍历，不断地算每一个位置上的最远距离。furthest用来不断更新总的最远可达距离
            furthest = Math.max(furthest, nums[i] + i);
            //碰到数字0，即每个位置上最远能到达的那个位置都无法越过数字0那个位置（注意判断0不能nums[i]==0，因为有的是可以跳过数字0的）
            if (furthest <= i) {
                return false;
            }
        }
        //最后看最远是否>=n-1
        return furthest >= n - 1;
    }


}
