package jianzhi_offer;

import java.util.Arrays;

/**
 * 剑指 Offer 46. 把数字翻译成字符串
 * <p>
 * 求的是有多少种翻译方式
 * 英文字母对应的数字范围是（0-25）
 * 遍历数字的每一位
 * dp[i]表示从0到i可以翻译成英文字母的种类
 * 若i和i-1两位组成的数字可以被翻译，则dp[i] = dp[i-1] + dp[i-2]
 * 若不可以，则dp[i] = dp[i-1]
 * dp[0]表示没有数字，dp[1]表示1位数字
 *
 * 用到的api：
 * 1. Java compareTo() 方法：https://www.runoob.com/java/java-string-compareto.html
 */
public class Solution46 {

    public int translateNum(int num) {
        //1.先把num转换成可以遍历的数据类型（选String）
        String str = String.valueOf(num);

        int n = str.length();

        int[] dp = new int[n + 1];
        //2.迭代，状态转移
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            String temp = str.substring(i - 2, i);
            if (temp.compareTo("10") >= 0 && temp.compareTo("25") <= 0) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[n];
    }
}
