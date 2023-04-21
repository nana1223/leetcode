package hot100;

import java.util.HashSet;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 * <p>
 * 思路：【滑动窗口】。左右指针，初始化都从左侧开始。
 * 始终保证窗口内是无重复的状态。若无重复右指针右移，有重复左指针右移
 * <p>
 * 关键问题：
 * 怎么判断一个子串是无重复的？——Set表维护（哈希思路）
 * 注意：移动指针时记得维护哈希表
 */
public class Solution3 {

    public static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        char[] chars = s.toCharArray();

        Set<Character> hashTable = new HashSet<>(chars.length);

        int left = 0;
        int right = 0;
        while (right < chars.length) {
            //1.判断右边界所在字符是否是重复的：若是重复的，左指针右移；若不是重复的，右指针右移
            while (hashTable.contains(chars[right])) {
                hashTable.remove(chars[left++]);
            }

            //2.更新最大长度
            maxLength = Math.max(maxLength, right - left + 1);
            //3.右指针右移
            hashTable.add(chars[right++]);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String s ="abcabcbb";
        lengthOfLongestSubstring(s);
    }
}
