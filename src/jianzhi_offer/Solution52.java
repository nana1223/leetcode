package jianzhi_offer;

/**
 * 剑指 Offer 52. 两个链表的第一个公共节点
 * <p>
 * 问题：两个链表上的两个指针在什么情况下前进？
 * 1.两个链表的节点相等时直接返回
 * 2.若不相等，无法判断两指针哪个走哪个不走
 * <p>
 * 【考虑数学上有无什么等式】
 * 设第一个公共节点为node，链表A节点数量为a，链表B节点数量为b，公共尾部的节点数量为c，则有：(a-c)+b = (b-c)+a
 * 即：指针p遍历完链表A再遍历链表B，指针q遍历完链表B再遍历链表A。然后指针pq走的步数一样，两指针会相遇，同时指向公共节点
 * <p>
 * “我走过我的世界，再从你的世界走一遍”
 * “你走过你的世界，再从我的世界走一遍”
 * “最终我们将相遇，可能在途中（公共节点），可能在结尾（null）”
 */
public class Solution52 {

    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode p = headA;
        ListNode q = headB;

        while (p != q) {
            if (p == null) {
                p = headB;
            } else {
                p = p.next;
            }
            if (q == null) {
                q = headA;
            } else {
                q = q.next;
            }
        }
        return p;
    }
}
