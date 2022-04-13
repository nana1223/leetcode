package 代码随想录.链表;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 反思：审题要清，是看指针是否相交，不是只看数值是否相等。（根据示例的 输入 找输出）
 *
 * 注意是重合，指针指向同一个节点而不是只要数值相同即可，直接从开头比是不正确的因为长度不同可能对着不一样，正确思路首先尾部对齐再比较
 */
public class Solution_LianBiaoXiangJiao {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode fingerA = headA;
        ListNode fingerB = headB;

        //1.先分别计算出两个链表的长度并计算长度差 diff
        int countA = 0, countB = 0;
        while (fingerA != null) {
            fingerA = fingerA.next;
            countA++;
        }
        while (fingerB != null) {
            fingerB = fingerB.next;
            countB++;
        }
        int diff = countA > countB ? countA - countB : countB - countA;

        //2.让较长的链表向前移动 diff 步，这样两个链表就实现了尾部对齐（即操作两个链表的指针在同一起点上）

        //让headA指向较长的链表，方便后续操作
        if (countB > countA) {
            ListNode temp = headA;
            headA = headB;
            headB = temp;
        }

        while (diff > 0) {
            headA = headA.next;
            diff--;
        }

        //3.最后遍历两个链表判断是否有交点
        while (headA != null) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
