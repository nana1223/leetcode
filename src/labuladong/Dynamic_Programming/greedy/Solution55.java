package labuladong.Dynamic_Programming.greedy;

/**
 * 55. 跳跃游戏
 * <p>
 * 有关动态规划的问题，大多是让你求最值的，比如最长子序列，最小编辑距离，最长公共子串等等等。这就是规律，因为动态规划本身就是运筹学里的一种求最值的算法。
 * <p>
 * 那么贪心算法作为特殊的动态规划也是一样，也一定是让你求个最值。这道题表面上不是求最值，但是可以改一改：
 * <p>
 * 请问通过题目中的跳跃规则，最多能跳多远？如果能够越过最后一格，返回 true，否则返回 false。
 * <p>
 * 思路：不断更新最远距离
 */
public class Solution55 {

    public static boolean canJump(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return true;
        }
        int furthest = 0;
        for (int i = 0; i < n - 1; i++) {
            furthest = Math.max(furthest, nums[i] + i);
            //若碰到数字0
            if (furthest <= i) {
                return false;
            }
        }
        return furthest >= n - 1;
    }

    public static void main(String[] args) {
        canJump(new int[]{0, 2, 3});
    }
}
