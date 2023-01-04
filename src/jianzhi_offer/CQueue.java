package jianzhi_offer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 剑指 Offer 09. 用两个栈实现队列
 * <p>
 * 实现思路：两个栈，一个用作输入栈，一个用作输出栈。当输出栈为空时，把输入栈的全部弹出压入输出栈。这样输出栈从栈顶到站地就是队列从队头到队尾的顺序。
 * 即一个队列具备的两个功能分别由两个栈来完成：栈A实现入队功能，栈B实现出队功能
 */
public class CQueue {

    Deque<Integer> inStack;
    Deque<Integer> outStack;

    public CQueue() {
        inStack = new ArrayDeque<>();
        outStack = new ArrayDeque<>();
    }

    public void appendTail(int value) {
        inStack.push(value);
    }

    public int deleteHead() {
        if (outStack.isEmpty()) {
            if (inStack.isEmpty()) {
                return -1;
            }
            while (!inStack.isEmpty()) {
                Integer last = inStack.pop();
                outStack.push(last);
            }
        }
        return outStack.pop();
    }

}
