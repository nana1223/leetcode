package 滑动窗口;

import java.util.HashMap;
import java.util.Map;

/**
 * 76.最小覆盖子串
 */
public class Solution76 {

    public String minWindow(String s, String t) {

        if (s == null || s == "" || t == null || t == "") {
            return "";
        }
        //因为子串中可能有重复字符, 需要以k,v的形式, 记录每个字符的出现次数
        HashMap<Character, Integer> needs = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            needs.put(t.charAt(i), needs.getOrDefault(t.charAt(i), 0) + 1);
        }
        int left = 0, right = 0;
        //valid表示有多少个字符满足条件来（包括数量上），若valid==needs.size()，就认为凑满
        int valid = 0;
        //记录最小覆盖子串的索引及长度
        int start = 0, len = Integer.MAX_VALUE;
        //右指针超出尾部算结束
        while (right < s.length()) {

            char c1 = s.charAt(right);
            // 判断取出的字符是否在字串中
            if (needs.containsKey(c1)) {
                window.put(c1, window.getOrDefault(c1, 0) + 1);

                // 是否凑满need中的一个字符: 一个字符在need中出现了n次, 如果window中也累计了n次, 就认为凑满了
                if (window.get(c1).equals(needs.get(c1))) {
                    valid++;
                }
            }
            right++;

            //判断是否需要收缩
            while (valid == needs.size()) {
                //更新最小子串
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char c2 = s.charAt(left);
                left++;
                if (needs.containsKey(c2)) {
                    window.put(c2, window.getOrDefault(c2, 0) - 1);
                    if (window.get(c2) < needs.get(c2)) {
                        valid--;
                    }
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

}
