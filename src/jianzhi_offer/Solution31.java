package jianzhi_offer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 剑指 Offer 31. 栈的压入、弹出序列
 * 判断弹出序列是否正确
 */
public class Solution31 {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length == 0) {
            return true;
        }

        //利用Deque，一次遍历过程
        Deque<Integer> stack = new ArrayDeque<>();

        //记录弹出顺序的索引
        int popIdx = 0;
        //记录压入顺序的索引
        int pushIdx = 0;

        //遍历压入栈的顺序
        while (pushIdx < pushed.length) {

            //把push序列的数字入栈stack，同时把压入栈的索引后移
            //【思路】：pushed序列中的每个数字都要入栈，然后通过栈来判断popped
            stack.push(pushed[pushIdx++]);

            //【要循环】若当前压入栈的数和弹出栈的数相等：出栈stack，弹出栈的索引后移
            while (!stack.isEmpty() && stack.peek() == popped[popIdx]) {
                stack.pop();
                popIdx++;
            }


        }
        return stack.isEmpty();
    }
}
