package jianzhi_offer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 剑指 Offer 58 - I. 翻转单词顺序
 * <p>
 * 思路：遍历s，遇到空格就把上一个单词记录到栈里。还有个问题，两个单词中间可能有多个空格。所以直到下一个是字符就开始记录起始位置
 */
public class Solution58_1 {

    public static String reverseWords1(String s) {
        Deque<String> stack = new ArrayDeque<>();

        int length = s.length();

        // 1. 把s处理成每个单词放入栈
        //单词的开始索引和结束索引
        int startIndex = 0;
        int i = 0;
        while (i < length) {
            if (s.charAt(i) != ' ' && i == length - 1) {
                //走到s的最后一个字符
                stack.push(s.substring(startIndex, ++i));
                break;
            }
            //若当前字符不是空格：记录单词的结束索引，并前进遍历
            if (s.charAt(i) != ' ') {
                i++;
            } else {
                //若当前字符是空格：把上一个单词放在stack中，然后前进遍历，更新开始和结束索引【注意：防止最开始的空格】
                if (startIndex != i) {
                    stack.push(s.substring(startIndex, i));
                }
                while (i < length && s.charAt(i) == ' ') {
                    i++;
                }
                startIndex = i;
            }

        }
        stack.stream().forEach(s3 -> System.out.println(s3));
        // 2.从栈中依次取出并拼接空格
        StringBuilder sb = new StringBuilder();
        int stackSize = stack.size();
        for (int j = 0; j < stackSize; j++) {
            sb.append(stack.pop());
            if (j != stackSize - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    /**
     * 思路二：用String类的一些api方法：s.trim();去掉首尾空格
     */
    public static String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        //1.去掉首尾空格
        s = s.trim();
        //2.双指针记录单词的左右边界，倒叙遍历s
        int j = s.length() - 1, i = j;
        while (i >= 0) {
            // 搜索首个空格
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }
            //添加单词
            sb.append(s.substring(i + 1, j + 1) + " ");

            //跳过单词之间的空格，把双指针都往前指
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }
            j = i;
        }

        return sb.toString().trim();
    }

    public static void main(String[] args) {
        String s = "  hello world!  ";
        String s1 = reverseWords(s);
        System.out.println(s1);
    }
}
