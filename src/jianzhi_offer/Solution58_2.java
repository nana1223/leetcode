package jianzhi_offer;

/**
 * 剑指 Offer 58 - II. 左旋转字符串
 */
public class Solution58_2 {

    public String reverseLeftWords(String s, int n) {
        String leftString = s.substring(0, n);
        String rightString = s.substring(n, s.length());
        return rightString + leftString;
    }
}
