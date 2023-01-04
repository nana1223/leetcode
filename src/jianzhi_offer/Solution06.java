package jianzhi_offer;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 剑指 Offer 06. 从尾到头打印链表
 * 要把链表反过来输出。想到数据结构————栈。先进后出的特性。
 * 思路：用一个栈，把链表遍历过程的数据入栈。最后挨个出栈存数组。
 *
 * 反思：空间换时间的最好想法
 */
public class Solution06 {

    public int[] reversePrint(ListNode head) {

        if (head == null) {
            return new int[]{};
        }

        //1.遍历链表，并入栈数据
        Deque<Integer> myStack = new ArrayDeque<>();
        myStack.push(head.val);
        int num = 1;
        while (head.next != null) {
            myStack.push(head.next.val);
            head = head.next;
            num++;
        }
        //2.出栈并存数组
        int[] res = new int[num];
        int index = 0;
        while (!myStack.isEmpty()) {
            res[index++] = myStack.pop();
        }
        return res;
    }


}
