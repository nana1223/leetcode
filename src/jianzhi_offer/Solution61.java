package jianzhi_offer;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 面试题61. 扑克牌中的顺子
 * 理解：这道题主要是要处理数字0，
 * <p>
 * 思路：先排序，然后看0的个数够不够填补中间空缺的数字
 * <p>
 * 【nb思路】：5张牌是顺子的充分条件：
 * 1首先除大小王外，所有牌无重复；2其次除大小王外的所有牌中，最大牌为max，最小牌为min。要满足 max-min < 5
 * <p>
 * 【反思】：从数学上证明的角度去看，找得出题干要求的充分条件。nb！！！
 */
public class Solution61 {


    public boolean isStraight(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int max = -1, min = 100;

        for (int num : nums) {

            // 1.遇到0直接跳过
            if (num == 0) {
                continue;
            }
            // 2.记录最大最小牌
            max = Math.max(num, max);
            min = Math.min(num, min);
            // 3.若有重复的牌直接返回
            if (set.contains(num)) {
                return false;
            }
            // 4.加入set
            set.add(num);
        }

        return max - min < 5;
    }


}
