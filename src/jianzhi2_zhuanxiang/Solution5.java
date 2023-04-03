package jianzhi2_zhuanxiang;

/**
 * 剑指 Offer II 005. 单词长度的最大乘积
 * 解读题意：
 * 1.首先，两个字符串不能有相同的字符
 * 2.然后长度的乘积。维护最大乘积
 * 用n表示数组长度，li表示单词words[i]的长度
 * <p>
 * 思路：两个for？得挨个对比每个字符串，首先它肯定不是最优解，暴力解
 * <p>
 * 【优化思路】：暴力法的时间复杂度来自：挨个比较 + 比较有无公共字符 + 计算乘积长度
 * 看能优化哪个部分？？？
 * 1.首先，挨个比较肯定避免不了
 * 2.比较有无公共字母【优化】：用位运算。按位与
 * 用一个数字二进制表示中的每一位，记录一个字母是否出现，例如 ac：00000000 00000000 00000000 00000101 = 3 表示
 * 比较两个字符串是否存在相同字符时，即比较 word1 & word2 == 0 即表示没有重复字母
 *
 *
 * 4和5还是不懂啊！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
 */
public class Solution5 {

    public int maxProduct(String[] words) {
        int res = 0;

        //1.先把数组中每个字符串表示成二进制数
        int[] wordCode = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                //为什么要(c - 'a')再左移1？？？？？？？？？？？？？？？？？？？？？？？，或运算可以理解，是为了最后拼成这个word的所有位
                wordCode[i] |= 1 << (c - 'a');
            }
        }


        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {

                //2.比较 words[i]和words[j]有无公共字母
                if ((wordCode[i] & wordCode[j]) == 0) {
                    //3.维护最大乘积
                    res = Math.max(res, words[i].length() * words[j].length());
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
