package jianzhi_offer;

/**
 * 剑指 Offer 05. 替换空格
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 * <p>
 * 思路：首先，String是不变的
 * 所以要怎么处理字符串：
 * 想法一：把String转成数组处理，但是，转成数组是 char[]，然而要替换的 %20 不是char。不好处理
 * 想法二：用可变的StringBuilder来处理
 */
public class Solution05 {
    /**
     * 思路一：直接用String提供的replace函数。但是注意：String是不可变的。所以String每次的更改都是复制全部然后到一个新的String。所以
     * 若写成
     * s.replace(" ", "%20");
     * return s;（这里的s还是原始的s）
     * 是毫无变化的。
     * 需要是直接的：return s.replace(" ", "%20");
     */

    public String replaceSpace1(String s) {

        return s.replace(" ", "%20");
    }

    public String replaceSpace(String s) {

        StringBuilder sb = new StringBuilder();

        for (Character c : s.toCharArray()) {
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return new String(sb);
    }
}
