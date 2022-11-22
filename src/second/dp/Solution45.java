package second.dp;

/**
 * 45. 跳跃游戏 II
 * 【迭代】
 * 相比55来说，要求使用最少的跳跃次数。
 * （55是只要能到就行，不管跳数，那就每次跳最远，不断维护那个总的最远即可；
 * 而45呢，还要考虑跳数最少然后到，那就要考虑每次跳（1-nums[i]）步，选哪个跳能让总的跳数最少还能到
 * <p>
 * 1.定义状态和选择：状态就是到哪个下标了，以及跳数；选择就是在当前下标跳几步
 * 2.定义dp：dp(i)=x表示在i处可以跳的最远的地方。这道题可以空间优化，只记一个不断更新究极最远的
 * 3.base case：dp(0)=nums[0]
 * 4.所求就是 i>=n-1
 * 5.状态转移：dp[i] = nums[i]+i。 fur = Math.max(fur, dp[i])
 * <p>
 * <p>
 * 别想那么多了，就挨着跳吧！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
 */
public class Solution45 {

    public int jump(int[] nums) {

        //1.记录当前起跳点的边界下标
        int border = 0;
        //2.记录当前起跳范围内能跳到的最远位置
        int maxPosition = 0;
        //3.记录步数
        int stepNum = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            //挨着往下遍历，统计起跳范围内，哪一格能跳的更远，每走一步就更新一步能跳的最远的下标
            //其实就是在记录下一步的局部最优
            maxPosition = Math.max(maxPosition, nums[i] + i);
            //如果走到当前起跳范围的边界，就必须得跳
            if (i == border) {
                border = maxPosition;
                stepNum++;
            }
        }
        return stepNum;
    }
}
