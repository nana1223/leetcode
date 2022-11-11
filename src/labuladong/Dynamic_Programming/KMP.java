package labuladong.Dynamic_Programming;

/**
 * 完全没看懂
 * https://labuladong.github.io/algo/3/28/97/
 * 对应28题，而且结果还是错的
 */
public class KMP {

    private int[][] dp;
    private String pat;

    /**
     * 1.通过模式串构建dp数组
     */
    public KMP(String pat) {

        this.pat = pat;
        int m = pat.length();

        //1.定义dp：dp[状态][字符] = 下个状态
        dp = new int[m][256];

        //2.base case：只有遇到 pat[0] 这个字符才能使状态从 0 转移到 1，遇到其它字符的话还是停留在状态 0
        dp[0][pat.charAt(0)] = 1;

        //3.影子状态【理解：动态规划就是用过去的结果解决现在的问题】，状态 X 总是落后状态 j 一个状态，与 j 具有最长的相同前缀。
        //可以这样理解：后者是在 txt 中匹配 pat，前者是在 pat 中匹配 pat[1..end]
        int X = 0;

        //4.状态转移
        for (int j = 1; j < m; j++) {
            for (int c = 0; c < 256; c++) {
                dp[j][c] = dp[X][c];
            }
            dp[j][pat.charAt(j)] = j + 1;
            //更新影子状态
            X = dp[X][pat.charAt(j)];
        }
    }

    /**
     * 2.通过构建的dp数组匹配子串
     */
    public int search(String txt) {
        int m = pat.length();
        int n = txt.length();

        //pat的初始状态为0
        int j = 0;
        for (int i = 0; i < n; i++) {
            //计算pat的下一个状态
            j = dp[j][txt.charAt(i)];
            //到达终止态，返回结果
            if (j == m) {
                return i - m + 1;
            }
        }
        //没到终止态
        return -1;
    }
}
