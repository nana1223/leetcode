package labuladong.Dynamic_Programming;

/**
 * 28. 找出字符串中第一个匹配项的下标
 */
public class Solution28 {

    /**
     * 法一：暴力解法
     * 暴力法比较蠢的地方在于：如果出现不匹配字符，同时回退 txt 和 pat 的指针，嵌套 for 循环，时间复杂度 O(MN)，空间复杂度O(1)。
     * 最主要的问题是，如果字符串中重复的字符比较多，该算法就显得很蠢。比如 txt = "aaacaaab", pat = "aaab"
     */
    public int strStr1(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();

        for (int i = 0; i <= m - n; i++) {

            int j;
            for (j = 0; j < n; j++) {
                //若有遇到一个字符不匹配，就退出当前匹的这串开头
                if (needle.charAt(j) != haystack.charAt(i + j)) {
                    break;
                }
            }
            //所有字符都匹配上了
            if (j == n) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 法二：KMP
     * 1.KMP 算法永不回退 txt 的指针 i，不走回头路（不会重复扫描 txt），而是借助 dp 数组中储存的信息把 pat 移到正确的位置继续匹配，
     * 时间复杂度只需 O(N)，用空间换时间，所以我认为它是一种动态规划算法。
     * <p>
     * 2.KMP 算法的难点在于，如何计算 dp 数组中的信息？如何根据这些信息正确地移动 pat 的指针？这个就需要确定有限状态自动机来辅助了，其实和动态规划的 dp 数组如出一辙。
     * <p>
     * 3.还有一点需要明确的是：计算这个 dp 数组，只和 pat 串有关。
     * 意思是说，只要给我个 pat，我就能通过这个模式串计算出 dp 数组，然后你可以给我不同的 txt，我都不怕，利用这个 dp 数组我都能在 O(N) 时间完成字符串匹配。
     * <p>
     * 4.KMP 算法最关键的步骤就是构造这个状态转移图。要确定状态转移的行为，得明确两个变量，一个是当前的匹配状态，另一个是遇到的字符；
     * 确定了这两个变量后，就可以知道这个情况下应该转移到哪个状态。
     * <p>
     * 5.定义一个二维dp数组
     * dp[j][c] = next
     * 0 <= j < M，代表当前的状态
     * 0 <= c < 256，代表遇到的字符（ASCII 码）
     * 0 <= next <= M，代表下一个状态
     */
    public int strStr(String haystack, String needle) {
        //1.通过模式串构建dp数组
        KMP kmp = new KMP(haystack);

        //2.通过dp数组匹配子串
        return kmp.search(needle);
    }
}
