package jianzhi_offer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 剑指 Offer 30. 包含min函数的栈
 * <p>
 * 思路：求栈中数据的最小值：465 ms
 * <p>
 * 思路二：原始栈A，就是用来存顺序的数据。再搞一个辅助栈B，使得在任意一个时刻，栈内元素的最小值就存储在辅助栈的栈顶元素中
 * 数据x入栈时，x入栈A，包含x在内的A中的所有元素的最小值y入栈B。使得栈B的栈顶始终是栈内的最小值。跟着栈A进站出站
 * 12ms
 *
 * <p>
 * 反思：归根到底是空间换时间的想法！！！用栈来负责空间上的存储。
 * （一开始那个思路：用一个最小值minNum来存储栈中的最小数，是不行的。
 * 因为你想，栈顶出栈之后，最小数就得不到了。所以要动态的的存下每一个数据入栈之后的最小数。用栈来存。
 * 反思：用一个数据存不行的时候，就考虑用一个数据结构存下每一个最小）
 */
public class Solution30 {


    /**
     * 存原始数据
     */
    Deque<Integer> numStack;
    /**
     * 辅助栈：存栈中动态的最小值
     */
    Deque<Integer> minStack;


    public Solution30() {
        numStack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {

        numStack.push(x);
        //若当前的数小于栈顶元素，就入当前的数；否则，再次入栈顶元素（保证栈顶始终是最小数）
        if (x < minStack.peek()) {
            minStack.push(x);
        } else {
            minStack.push(minStack.peek());
        }
    }

    public void pop() {
        numStack.pop();
        minStack.pop();
    }

    public int top() {
        return numStack.peek();
    }

    public int min() {
        return minStack.peek();
    }

}
