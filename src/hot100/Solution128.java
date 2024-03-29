package hot100;

import java.util.*;

/**
 * 128. 最长连续序列
 * <p>
 * 题意要求：O(n)
 * <p>
 * 思路1：新建一个下标数组，若1处有值设为true，没有值设为false；统计该数组中连续的true的长度。
 * 试过了，这样不行，因为这个下标数组的长度是无法提前得知的，而且会非常浪费空间 ❌
 * <p>
 * 思路2：先排序，后遍历。但是这样也没办法实现题目要求的复杂度
 * <p>
 * 思路3：遍历数组中的每个元素num，然后以num为起点，每次+1向后遍历num+1,num+2,num+3...，判断这些元素是否存在于数组中。
 * 假设找到的最大的连续存在的元素为num+x，那么这个连续序列的长度即为x+1。最后将每个num所开始序列长度取个最大值即可。
 * <p>
 * 【反思】：思路2和思路3都是朴素的超时方法。那么就想想针对这两思路怎么进行优化。
 * 首先思路2这个排序就卡死了，没有能达到 O(n) 的排序算法 【TODO】: 再看排序算法
 * <p>
 * 思路4：✅
 * 那么针对思路3怎么能进行优化？————优化的点主要有2个
 * 1）遍历数组中的每个元素num。其实不用每个元素都遍历，会有很多冗余，只需要遍历一个序列中的最小值即可。
 * 例如：[1,2,3,4,5]当2,3,4,5发现均有比自己小1的元素存在，那么它们就不会开始+1遍历，而1是连续序列中最小的元素，没有比自己小1的元素存在，所以会开始+1遍历。通过上述方式便可将时间复杂度优化至O(n)。
 * 2）判断num+1,num+2,num+3…是否在数组中。用直接遍历的方式查找是O(n)，改为用哈希查找 O(1)
 * 以上就可以达到O(n)
 * <p>
 */
public class Solution128 {

    public int longestConsecutive(int[] nums) {
        int res = 0;

        //1.建立哈希表
        Set<Integer> hashTable = new HashSet<>();
        for (int num : nums) {
            hashTable.add(num);
        }

        //2.开始遍历
        for (int i = 0; i < nums.length; i++) {
            //2.1 判断当前要开启遍历的是否是连续序列的最小值：等价于 判断数组中是否有比x小1的数
            if (hashTable.contains(nums[i] - 1)) {
                continue;
            }

            //2.2 是序列最小值，开始判断哈希表中是否存在x+1, x+2 …
            int count = 1;
            while (hashTable.contains(nums[i] + count)) {
                count++;
            }
            //2.3 记录是否是最长的
            res = Math.max(res, count);
        }
        return res;

    }
}
