package jianzhi_offer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 剑指 Offer 50. 第一个只出现一次的字符
 * <p>
 * 思路：至少要全部遍历完才能知道出现几次，所以至少是 O(n)。
 * 一次遍历的过程中用set记录，若set表里已经有该数的话直接删了就行，没用了。然后set表的第一个数就是答案 （因为不用知道出现几次，所以set就够用了不用哈希）
 * 反思：不能用set，因为你删了万一再出现第三次！所以还是要用【哈希表】
 * <p>
 * 正确解法：用哈希表，因为不用记录出现几次，所以就用false true来代表是否是多次出现
 */
public class Solution50 {
    public char firstUniqChar(String s) {
        Map<Character, Boolean> res = new HashMap<>();
        char[] ss = s.toCharArray();
        for (char index : ss) {
            if (res.containsKey(index)) {
                res.put(index, false);
            } else {
                res.put(index, true);
            }
        }
        //然后遍历哈希表找出第一个为true的字符
        for (char index : ss) {
            if (res.get(index) == true) {
                return index;
            }
        }
        return ' ';
    }
}
