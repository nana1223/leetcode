package hot100;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. 和为 K 的子数组
 * <p>
 * 题意理解：数组有负数。而且要返回的是连续子数组，所以不能打乱原数组排序
 * 注意⚠️：这题不能用双指针滑动窗口，因为有负数。不能保证右指针右移可以增大窗口和，也不能保证左指针右移可以减小。
 * <p>
 * 思路：【前缀和】定义前缀和数组 pre[x] 表示 从0到x所有数的和, nums[i] = pre[i] - pre[i-1]
 * 即要求的是pre数组的差等于k的。O(n2)
 * <p>
 * 继续优化：
 * 思路2✅：前缀和 + 哈希表
 * 我们最终要求的只有pre数组的差为k的次数，并不关心具体是哪两项。由于只关心次数，不关心具体的解，我们可以使用哈希表加速运算；
 * 所以，在一边遍历的时候，一边用map存下 每个前缀和 和 对应出现的次数。
 * 然后查map中是否存在key为【当前前缀和-k】的，若有的话，说明满足一次要求，累加次数
 *
 * 【反思】⚠️：前缀和数组的构造：切记构造成 pre[x]表示从0到x-1的形式。可以避免很多边界问题！
 */
public class Solution560 {

    public static int subarraySum1(int[] nums, int k) {
        int res = 0;

        //1.构造nums的前缀和数组
        int[] preSum = new int[nums.length];
        preSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }

        //2.遍历前缀和数组，找数据差为k的然后增加次数
        for (int i = 0; i < preSum.length; i++) {
            //【反思】：如果前缀和数组错位存的话，就不用单独判断这么一下；如果正对位存，就会漏情况
            if (preSum[i] == k) {
                res++;
            }
            for (int j = i + 1; j < preSum.length; j++) {
                if (preSum[j] - preSum[i] == k) {
                    res++;
                }
            }
        }
        return res;
    }

    public static int subarraySum(int[] nums, int k) {
        int res = 0;

        //1.构造nums的前缀和数组
        int[] preSum = new int[nums.length + 1];
        preSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        //记录每个前缀和以及出现的次数。key 为前缀和，value为前缀和为key的个数
        Map<Integer, Integer> hash = new HashMap<>();

        //2. 遍历前缀和，在遍历的过程中，1统计最后符合条件的个数 2统计相同前缀和的个数
        for (int i = 0; i < preSum.length; i++) {
            res += hash.getOrDefault(preSum[i] - k, 0);
            hash.put(preSum[i], hash.getOrDefault(preSum[i], 0) + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1};
        subarraySum(nums, 2);
    }

}
