package 代码随想录.链表;

/**
 * 反转链表
 * 法一：把链表遍历一遍，存在数组里面（栈），然后反序输出（好笨的办法）
 * 法二：链表反序：想想链表的特性，用指针指向下一个元素。反序，就换指针方向即可（把指针指向前一个元素）
 * 注意：1更改指针之前要存储当前节点的后一个结点  2.还有存储前一个结点
 * 反思：要考虑特殊情况，还要考虑怎么把特殊情况包含在整体里
 *
 *
 * 记得 链表的题要想想链表的指针特性！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
 *
 */
public class Solution206 {

    public ListNode reverseList(ListNode head) {

        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            //1.把当前结点的下一个结点记录下来
            ListNode next = cur.next;

            //2.将当前节点的next指针指向前一个结点
            cur.next = pre;

            //3.将pre cur指针都往后移
            pre = cur;
            cur = next;
        }
        //最后指向最后一个结点的是pre指针
        return pre;
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
