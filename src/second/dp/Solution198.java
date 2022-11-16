package second.dp;

import java.util.Arrays;

/**
 * 198. 打家劫舍
 * <p>
 * 1.定义状态和转移：状态：走到第几个屋子了，选择：当前屋子偷或者不偷
 * 2.定义dp：dp(i)=x表示到第i个房子偷到的金额为x。
 * <p>
 * 3.base case:
 * 注意这个dp(0)=nums(0)不是base case，想呢哇，因为节点0不一定偷！！！
 * 所以真正的base case是i>=n时，dp(n)=0
 * 【定好base case再写所求以及遍历顺序： 从0开始往后遍历】
 * 【如果是从上到下递归的话，就是从另一端往base case遍历； 如果是从下到上迭代的话，就是从base case往另一端遍历。 so，一定要搞好base case】
 * <p>
 * 4.定义状态转移：取当前屋子偷或者不偷的最大值取max
 * 状态转移方程根据选择来写：选择就是当下的房子偷或者不偷，但是有个限制就是上一个房子偷过了这个就不能偷，只能跳过
 * （但是注意她不是隔一个偷一个，也可以隔两个偷，取决于哪种偷金额最大。
 * 但是！站在当前节点上，只需往后考虑两个节点即可，再往后是下一个节点需要考虑的。【注意分解子问题】）
 * 【写状态转移的同时还要考虑dp的定义，dp返回的是到当前i节点所偷的金额】
 * 所以当前不偷的话，就是说到下一个结点的时候金额和当前节点金额一样 rob(i) = rob(i+1)
 * 若偷的话，就需要到下下一个节点，rob(i+2)+nums[i]
 * 然后二者取最大
 * dp(i) = Math.max( rob(i+2)+nums[i], rob(i+1))
 * 5.备忘录
 */
public class Solution198 {

    int[] memo;

    public int rob(int[] nums) {

        int n = nums.length;
        memo = new int[n];
        Arrays.fill(memo, -1);
        return dp(nums, 0);
    }

    private int dp(int[] nums, int i) {

        //1.base case
        if (i >= nums.length) {
            return 0;
        }
        //2.查备忘录
        if (memo[i] != -1) {
            return memo[i];
        }
        //3.状态转移
        memo[i] = Math.max(
                dp(nums, i + 1),
                dp(nums, i + 2) + nums[i]
        );
        return memo[i];
    }
}
