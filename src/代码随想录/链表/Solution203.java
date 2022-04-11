package 代码随想录.链表;

/**
 * 移除链表元素
 * 法一：迭代
 * 法二：递归（链表的定义有递归的性质）
 */
public class Solution203 {
    public ListNode removeElements1(ListNode head, int val) {

        while (head != null && head.val == val) {
            head = head.next;
        }

        //判head == null需得放在对头单独处理的代码下面，不然处理完head有可能会空
        if (head == null) {
            return head;
        }

        ListNode p = head;
        while (p.next != null) {
            if (p.next.val == val) {
                p.next = p.next.next;
            } else {
                //若不加else，当有两个连续的相等的值的时候就会跳过 eg[1,2,2,1] 2
                p = p.next;
            }

        }
        return head;
    }

    //递归实现
    public ListNode removeElements2(ListNode head, int val) {

        //递归终止条件
        if (head == null) {
            return head;
        }
        head.next = removeElements2(head.next, val);
        return head.val == val ? head.next : head;
    }


    public class ListNode {
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
