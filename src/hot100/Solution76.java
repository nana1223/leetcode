package hot100;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小覆盖子串
 * 求s中涵盖t所有字符的最小子串
 *
 * 思路：【滑动窗口】
 *
 * 用i j 表示窗口的左右边界，步骤：
 * 1）步骤一：不断增加j使滑动窗口增大，直到包含了t所有元素
 * 2）步骤二：不断增加i使滑动窗口缩小，因为是要求最小字串，所以将不必要的元素排除在外，使长度减小，直到碰到一个必须包含的元素，这个时候不能再扔了，记录此时滑动窗口的长度，并保存最小值
 * 3）步骤三：让i再增加一个位置，这个时候滑动窗口肯定不满足条件了，那么继续从步骤一开始执行，寻找新的满足条件的滑动窗口，如此反复，直到j超出了字符串S范围。
 *
 * 【问题】：如何判断滑动窗口包含了t的所有元素？
 * 用一个【哈希表】need，来维护当前滑动窗口中需要的各元素数量。
 * 当滑动窗口包含某个元素时，就让对应需要的数量减一。注意是可能到负数的，这样在步骤2中可以排除不必要元素，负数的就是不必要的元素
 * 【结论】：当need中所有元素的数量都小于等于0时，表示当前滑动窗口不再需要任何元素。
 *
 * 【优化】：在判断滑动窗口中是否包含T的所有元素时，每次都遍历一遍need的所有元素是否小于等于0会耗时。
 * 维护一个额外的变量needCnt来记录所需元素的总数量。当我们碰到一个所需元素c，不仅need[c]的数量减少1，同时needCnt也要减少1。
 * 这样我们通过needCnt就可以知道是否满足条件，而无需遍历字典了。
 *
 * 【理解】：哈希表中是要把s的每个字符都存下还是只存t的每个字符？需要存下s的每个字符，因为还要根据need缩小窗口排除不必要的元素
 *
 */
public class Solution76 {

    public String minWindow(String s, String t) {
        int minSize = Integer.MAX_VALUE;
        int resStart = 0;
        // 构建哈希表并初始化
        int needCnt = t.length();
        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        // 遍历s
        int left = 0;
        int right = 0;
        while (right < s.length()) {
            // 步骤一：不断增加j使滑动窗口增大，直到窗口包含了T的所有元素
            // 1 判断当前字符是否需要
            char c = s.charAt(right);
            if (need.get(c) != null && need.get(c) > 0) {
                needCnt--;
            }
            // 理解：右边界无论是否需要都会加入
            need.put(c, need.getOrDefault(c, 0) - 1);

            // 2 判断是否已包含所有字符
            if (needCnt == 0) {
                // 步骤二，增加左边界，扔掉不需要的元素
                char leftC = s.charAt(left);
                while (need.get(leftC) < 0) {
                    need.put(leftC, need.get(leftC) + 1);
                    left++;
                    leftC = s.charAt(left);
                }
                // 3 更新最短
                if (right - left + 1 < minSize) {
                    minSize = right - left + 1;
                    resStart = left;
                }
            }
            // 4 右边界右移
            right++;
        }
        return minSize == Integer.MAX_VALUE ? "" : s.substring(resStart, resStart + minSize);
    }

}
