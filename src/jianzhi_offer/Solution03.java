package jianzhi_offer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 剑指 Offer 03. 数组中重复的数字
 * <p>
 * 思路一：暴力解法：双层遍历
 * <p>
 * 思路二：优化：就是一次遍历
 * 怎么做到一次遍历？
 * 想法一：一次排序 + 一次遍历
 * 想法二：能否不排序然后一次遍历找出重复？
 * ————空间换时间。用一个set记录（因为只需要找出一个来就行，如果要找出所有重复的可以考虑map记录每个数字本身以及数字出现的次数）
 * 接着优化就是优化空间复杂度：看怎么能不用额外的set来记录。在原地进行操作
 *
 * 反思：因为要找重复的值。考虑到已封装好的不可重复的数据结构有set和map
 * 反思：这道题虽然是查找，但是过于简单也没用到查找算法
 */
public class Solution03 {
    public int findRepeatNumber(int[] nums) {
        Set<Integer> integerSet = new HashSet<>();
        for (int num : nums) {
            if (integerSet.contains(num)) {
                return num;
            }
            integerSet.add(num);

        }
        return -1;
    }
}
