package jianzhi_offer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 剑指 Offer 59 - I. 滑动窗口的最大值
 * <p>
 * 【本题难点】：如何在每次窗口滑动后，将 “获取窗口内最大值”的复杂度从O(k)降到O(1)
 * 【思路】：（同30包含min函数的栈）本题设置一个单调队列deque，保证始终能从队首拿到最大值
 * 【deque内只包含窗口中的元素，且队首始终放着窗口的最大值】
 * 每轮滑动窗口，除了要删除元素nums[i-1]，还要把deque中对应的元素也一起删除
 * 每轮滑动窗口，添加了元素nums[j+1]，需要将deque内所有小于nums[j+1]的元素都删除
 */
public class Solution59_1 {

    public int[] maxSlidingWindow(int[] nums, int k) {


        if (k == 1) {
            return nums;
        }

        int resLength = nums.length - k + 1;
        int[] res = new int[resLength];

        Deque<Integer> deque = new ArrayDeque<>();

        //未形成窗口区间
        for (int i = 0; i < k; i++) {
            //队列按从大到小放入
            //如果首位值（即最大值）不在窗口区间，删除首位
            //如果新增的值小于队列尾部值，加到队列尾部
            //如果新增值大于队列尾部值，删除队列中比新增值小的值，然后再把新增值加入到队列中
            //如果新增值大于队列中所有值，删除所有，然后把新增值放到队列首位，保证队列一直是从大到小

            //一直循环删除到队列中的值都大于当前值，或者删到队列为空
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
        }
        res[0] = deque.peekFirst();

        //形成窗口口
        for (int i = k; i < nums.length; i++) {

            //i-k是已经在区间外了，如果首位等于nums[i-k]，那么说明此时首位值已经不再区间内了，需要删除
            if (deque.peekFirst() == nums[i - k]) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);

            res[i - k + 1] = deque.peekFirst();
        }

        return res;
    }
}
