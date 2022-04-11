package labuladong.数组.滑动窗口;

import java.util.HashMap;
import java.util.Map;

/**
 * 567.字符串的排列
 * 给你两个字符串s1和s2 ，写一个函数来判断 s2 是否包含 s1的排列。如果是，返回 true ；否则，返回 false 。
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 * 示例 1：
 * 输入：s1 = "ab" s2 = "eidbaooo"
 * 输出：true
 * 解释：s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 * 输入：s1= "ab" s2 = "eidboaoo"
 * 输出：false
 */
public class Solution567 {

    public boolean checkInclusion(String s1, String s2) {
        //存所需要的每个字符和出现的个数
        Map<Character, Integer> need = new HashMap<>();
        //存窗口中出现的有效字符和其个数
        Map<Character, Integer> window = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            need.put(s1.charAt(i), need.getOrDefault(s1.charAt(i), 0) + 1);
        }
        //记录有效字符的个数
        int valid = 0;
        int left = 0, right = 0;

        while (right < s2.length()) {
            char c1 = s2.charAt(right);
            //判断当前字符在need中是否存在，若存在个数是否凑够，然后右指针向右滑动
            if (need.containsKey(c1)) {
                window.put(c1, window.getOrDefault(c1, 0) + 1);
                if (window.get(c1).equals(need.get(c1))) {
                    valid++;
                }
            }
            right++;
            //再判断左指针是否需要向右滑动(左指针向右滑动的时机是窗口大小大于s1.length()，因为排列要个数一样)
            while (right - left >= s1.length()) {
                //判断是否找到合法的子串
                if (valid == need.size()) {
                    return true;
                }
                char c2 = s2.charAt(left);
                left++;
                if (need.containsKey(c2)) {
                    if (window.get(c2).equals(need.get(c2))) {
                        valid--;
                    }
                    window.put(c2, window.getOrDefault(c2, 0) - 1);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "eibdoaoo";
        Solution567 solution567 = new Solution567();
        solution567.checkInclusion(s1, s2);
    }
}
