package jianzhi_offer;

/**
 * 剑指 Offer 18. 删除链表的节点
 */
public class Solution18 {


    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        // 设一个head之前的空节点
        ListNode preHead = new ListNode();
        preHead.next = head;

        // 用来遍历的指针p
        ListNode p = head;
        ListNode preP = preHead;

        while (p != null) {
            if (p.val == val) {
                preP.next = p.next;
            } else {
                p = p.next;
                preP = preP.next;
            }
        }
        return head;
    }

}
