package hot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 438. 找到字符串中所有字母异位词
 * <p>
 * 思路：
 * 1。先确定p的长度为l
 * 2。然后s挨个遍历，双层，外层遍历s的每个字符x，内层遍历以x打头的长度为l的字符
 * 优化点：
 * 1）在外层和内层每遍历一个字符，判断p中是否含有该字符，若没有直接跳过
 * 2）内层遍历前先判断若x+l < s.length，直接结束
 * <p>
 * 问题：怎么判断两个字符串是否是异位词？————用map把p的每个字母以及出现次数存下，然后s去判断map中是否存在以及出现次数是否相同
 * 超时 ❌
 * 【反思】：有非常多的重复判断，例如：abcdefabcdefabcdef…… 这种的重复 用滑动窗口解决
 * <p>
 * 思路2 ✅：滑动窗口
 * 左右指针什么时候移动呢？
 * 右指针不断右移，每次遍历都移。左指针只有当窗口内的字符及数量符合要求时开始右移，直到长度相同匹配到结果。
 */
public class Solution438 {

    /**
     * 超时算法。暴力
     */
    public List<Integer> findAnagrams1(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int pLength = p.length();
        //1.存下p的字符出现情况
        Map<Character, Integer> pMap = new HashMap<>();
        for (char c : p.toCharArray()) {
            pMap.put(c, pMap.getOrDefault(c, 0) + 1);
        }

        //2.遍历s
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            //2.1 p中没有该字符
            if (!pMap.containsKey(chars[i])) {
                continue;
            }
            //2.2 p中有该字符，往后遍历l长度
            if (i + pLength > s.length()) {
                break;
            }
            //2.3 判断 s从i到i+pLength 和 p 是否是异位词
            boolean tag = isSame(s.substring(i, i + pLength), p, pMap);
            if (tag) {
                res.add(i);
            }

        }
        return res;
    }

    /**
     * 判断两字符串是否是异位词
     */
    private boolean isSame(String s, String p, Map<Character, Integer> pMap) {
        //1.记录substring中每个字母出现的次数
        Map<Character, Integer> sMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            sMap.put(c, sMap.getOrDefault(c, 0) + 1);
        }
        //2.遍历s中的每个字符，判断p中是否有以及次数是否相同
        for (Map.Entry<Character, Integer> entry : sMap.entrySet()) {
            if (!(pMap.containsKey(entry.getKey()) && pMap.get(entry.getKey()).equals(entry.getValue()))) {
                return false;
            }
        }
        return true;
    }

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();

        //1.存下p的字符出现情况
        Map<Character, Integer> pMap = new HashMap<>();
        for (char c : p.toCharArray()) {
            pMap.put(c, pMap.getOrDefault(c, 0) + 1);
        }

        //2.存窗口内的有效字符
        Map<Character, Integer> window = new HashMap<>(pMap.size());

        //3.开始遍历s
        int left = 0;
        int right = 0;
        //用来记录window中有多少字符符合要求
        int valid = 0;

        while (right < s.length()) {
            //3.1 判断p中是否有该字符
            if (pMap.containsKey(s.charAt(right))) {
                window.put(s.charAt(right), window.getOrDefault(s.charAt(right), 0) + 1);
                //3.2 位置 s.charAt(right) 对应的字符是否已经符合要求
                if (window.get(s.charAt(right)).equals(pMap.get(s.charAt(right)))) {
                    valid++;
                }
            }

            //3.3 window中的字符已经符合p的要求（每个字符以及数量）
            while (valid == pMap.size()) {
                // 3.4 两字符串长度相等，加入结果集
                if (right - left + 1 == p.length()) {
                    res.add(left);
                }
                //3.5 移动左指针：修改有效字符量、修改window窗口的量
                if (pMap.containsKey(s.charAt(left))) {
                    if (pMap.get(s.charAt(left)).equals(window.get(s.charAt(left)))) {
                        valid--;
                    }
                    window.put(s.charAt(left), window.get(s.charAt(left)) - 1);
                }
                left++;
            }
            //3.6 移动右指针
            right++;
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "baa";
        String p = "aa";
        findAnagrams(s, p);
    }

}
