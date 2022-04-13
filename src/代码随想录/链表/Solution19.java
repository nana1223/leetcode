package 代码随想录.链表;

/**
 * 删除链表的倒数第 N 个结点
 * 思路：
 * 法一：（暴力法）先遍历一遍，求出链表长度，获取n的正数的位置，然后再遍历第二遍的时候删除节点
 * 法二：一趟扫描怎么实现？
 * 双指针！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
 * 反思：双指针思路，要灵活运用在数组和链表中
 */
public class Solution19 {

    /**
     * 双指针！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //先设个虚拟头 （万一删的是第一个结点，统一处理）
        ListNode preHead = new ListNode();
        preHead.next = head;
        //搞两个快慢指针
        ListNode fast = preHead;
        ListNode slow = preHead;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        //此时 slow即指向要删除的结点de前面一个结点
        slow.next = slow.next.next;
        return preHead.next;
    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
