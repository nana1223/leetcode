package hot100;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 239. 滑动窗口最大值
 * <p>
 * 思路：双向队列
 * 新增数据结构【双向队列】，用来存数值对应的数组下标位置，并且对应的数组中的数从队首到队尾是从大到小排列
 *
 * 在遍历原数组的过程中：
 * 1）判断当前遍历的数和队列队尾的数，若大于队尾，则需要弹出队尾值。直到队列重新满足从大到小的要求，再把当前数的下标加入队尾
 * 2）刚开始遍历时，L 和 R 都为 0，有一个形成窗口的过程，此过程没有最大值，L 不动，R 向右移
 * 3）当窗口大小形成时，L 和 R 一起向右移。每次移动时，判断队首的值的数组下标是否在 [L,R] 中，如果不在则需要弹出队首的值，当前窗口的最大值即为队首的数。
 *
 * 在遍历的每一次移动过程中，队列进进出出，在形成窗口之后的每一次队首的下标对应的数据就是当前窗口的最大值。
 *
 * 1。解释一下为什么队列中要存放数组下标的值而不是直接存储数值，因为要判断队首的值是否在窗口范围内，由数组下标取值很方便，而由值取数组下标不是很方便。
 * 2。通过示例发现 R=i，L=k-R。由于队列中的值是从大到小排序的，所以每次窗口变动时，只需要判断队首的值是否还在窗口中就行了。
 * <p>
 * 【深刻理解】：对于新增的数据结构，在每次更改数据的时候都要有对应的维护相关数据结构的行为
 */
public class Solution239 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int resLength = nums.length - k + 1;
        int[] result = new int[resLength];
        //1，定义队列
        Deque<Integer> deque = new ArrayDeque<>();
        //2，遍历原数组
        for (int i = 0; i < nums.length; i++) {
            //2.1 判断当前数是否大于队尾下标对应的值，若大于，则需要弹出队尾值后再把当前数对应的下标值加入队尾
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            //2.2 把当前数的下标入队列队尾
            deque.addLast(i);
            //2.3 判断当前队列中队首的值还是否在窗口中
            if (!deque.isEmpty() && deque.peek() <= i - k) {
                deque.poll();
            }
            //2.4 保存结果，在形成窗口大小之后开始保存
            if (!deque.isEmpty() && i + 1 >= k) {
                result[i + 1 - k] = nums[deque.peek()];
            }
        }
        return result;
    }
}
