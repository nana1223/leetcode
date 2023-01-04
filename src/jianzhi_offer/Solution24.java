package jianzhi_offer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 剑指 Offer 24. 反转链表
 * <p>
 * 思路一：遍历的过程用一个辅助栈，依次入栈。然后再依次出栈的时候，构造链表
 * <p>
 * 思路二：迭代（在迭代的过程中，修改每个节点的next的指针指向前一个节点。所以需要存储前一个节点和后一个节点）（更快）
 * <p>
 * 思路三：递归：递归函数 dp(curNode,preNode)参数传入当前节点和上一个节点。
 * 递归结束的条件即当前节点为空。然后在递归返回的时候修改next指针。递归就不需要记录afterNode。
 */
public class Solution24 {
    public ListNode reverseList1(ListNode head) {

        if (head == null) {
            return null;
        }
        //1.遍历链表并入栈
        Deque<Integer> myStack = new ArrayDeque<>();
        myStack.push(head.val);
        while (head.next != null) {
            myStack.push(head.next.val);
            head = head.next;
        }
        //2.出栈并构造链表
        ListNode newHead = new ListNode(myStack.pop());
        ListNode preNode = newHead;
        while (!myStack.isEmpty()) {
            ListNode curNode = new ListNode(myStack.pop());
            preNode.next = curNode;
            preNode = curNode;
        }
        return newHead;

    }

    public ListNode reverseList2(ListNode head) {

        if (head == null) {
            return null;
        }
        ListNode preNode = null;
        ListNode curNode = head;
        ListNode afterNode;
        while (curNode != null) {

            //1.存下该存的节点
            afterNode = curNode.next;
            //2.修改next指针
            curNode.next = preNode;
            //3.迭代指针
            preNode = curNode;
            curNode = afterNode;
        }
        return preNode;
    }

    public ListNode reverseList(ListNode head) {
        return dp(head, null);
    }

    private ListNode dp(ListNode curNode, ListNode preNode) {
        //1.递归结束条件：curNode == null的时候，返回preNode即反转链表的头结点
        if (curNode == null) {
            return preNode;
        }
        //2.递归过程
        ListNode res = dp(curNode.next, curNode);
        curNode.next = preNode;
        return res;
    }
}
