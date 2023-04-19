package hot100;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 1. 两数之和
 * <p>
 * 思路：
 * 1.暴力法：两层for O(n2)
 * 2.优化：排序+双指针。排序选用快排。O()
 * 3.怎么能在一次遍历的时候找到两数？空间换时间？怎么换？————哈希表（值，下标）
 * 在遍历的时候去哈希表中找有无等于target-x的，若有匹配成功；若没有把x存入
 *
 * 反思：数组链表优化时间复杂度时，多多考虑空间换时间————哈希表
 */
public class Solution1 {

    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> hashMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(target - nums[i])) {
                return new int[]{i, hashMap.get(target - nums[i])};
            } else {
                hashMap.put(nums[i], i);
            }
        }
        return new int[2];
    }
}
