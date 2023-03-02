package jianzhi_offer;

/**
 * 剑指 Offer 25. 合并两个排序的链表
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 */
public class Solution25 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode newHead = new ListNode();
        ListNode cur = newHead;

        ListNode p = l1;

        ListNode q = l2;

        while (p != null && q != null) {
            if (p.val <= q.val) {
                cur.next = new ListNode(p.val);
                p = p.next;
            } else {
                cur.next = new ListNode(q.val);
                q = q.next;
            }
            cur = cur.next;
        }
        if (p != null) {
            cur.next = p;
            p = p.next;
            cur = cur.next;
        }
        if (q != null) {
            cur.next = q;
            q = q.next;
            cur = cur.next;
        }

        return newHead.next;
    }
}
