package second.dp;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * 740. 删除并获得点数
 * <p>
 * 思路分析过程：
 * 首先不能用贪心：因为选择第一个数若是最大的话。不能保证最后总点数最大
 * 和打家劫舍有异曲同工之妙，如果先把nums从小到大排序，就变成了间隔偷东西，但是间隔是间隔所有相同的nums[i]+1和nums[i]-1
 * <p>
 * <p>
 * 1.定义状态和选择：状态就是走到的数字i，选择就是：选是否主动删掉nums[i]
 * 2.定义dp: dp(i) = x 表示在数字i的时候已经获得的点数
 * 3.base case：dp(0), dp(1)。
 * 所求为：dp(n)
 * 4.状态转移（根据选择，看当前的i是否要主动删除）：dp(i) = Math.max( dp(i+1 ), dp(i+2)+nums[i])，【这个肯定是不对的】
 * 若删：加当前的点数 + map中的个数减一 + 判断前后是否符合等值
 * 【理解：若选择了 xx，所有等于 xx 的元素也应一同被选择，以尽可能多地获得点数。】
 * 所以，若删：应该要加的总点数是当前点数*map中的个数 + 判断前后值是否符合符合就被动删
 * 若不删，则点数不变继续往下遍历
 * 然后状态选择就是找删或不删中的较大值
 * dp(i) = Math.max(dp(i-1), dp(i-2)+dp(i)*map.get(nums[i]))
 * 考虑用一个map把个数存下试试？？？
 * 但是要注意状态转移的时候要考虑到题干附加条件
 * 5.memo
 * <p>
 * 反思：
 * 1.这道题用递归好像不太好做，因为你在递归下一次之前需要先把符合题意的点删掉，但是递归直接就递归下一次了
 * 2.“删除大小相邻的数” 可以视为不选择大小相邻的数【不选择来代替remove操作，不选择即删除，不必真的删除】
 *
 * 究极反思：
 * 1.因为要删除的是大小相邻的数字，不是位置相邻的数字，所以需要把没有的那些数字也存下，存成0，方便使用位置来跳过代替删除操作！！！！！！
 * 2.迭代的dp思路注意：
 * 1）定义dp
 * 2）写出dp的base case
 * 3）从base case往另一个方向进行迭代，写状态方程
 * 4）最后所求就是dp另一个方向的值
 */
public class Solution740 {


    public int deleteAndEarn(int[] nums) {

        //1.找出所有数字中的最大数字
        int maxNum = Arrays.stream(nums).max().getAsInt();
        //2.定义dp数组和 对应数字的总点数（数字大小*出现次数）
        int[] dp = new int[maxNum + 1];
        int[] mp = new int[maxNum + 1];
        //3.填充对应数字的总点数（数字大小*出现次数）
        //比如说：nums:[2,2,3,3,3,4]，那么mp:[0,0,4,9,4]即2的位置是2*2，3的位置上是3*3，4的位置上是4*1
        for (int x : nums) {
            mp[x] += x;
        }
        //4.base case dp的
        dp[1] = mp[1];
        //5.迭代的状态转移【细节注意是 i <= maxNum】
        for (int i = 2; i <= maxNum; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + mp[i]);
        }
        //6.最后所求
        return dp[maxNum];

    }

    /**
     * 法二思路：
     * 法一是把所有的数字都存下，即使没有的
     * 是否考虑只存有的数字，然后判断下一位是否是数字+1然后是的话就跳过，挺麻烦的好像，空间节省，但是也可以压缩dp来节省空间
     */


}