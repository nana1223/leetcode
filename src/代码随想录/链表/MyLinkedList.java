package 代码随想录.链表;

/**
 * 707.设计链表
 * 反思：
 * 1.先设计结点类 ListNode
 * 2.再整一个链表类（链表类里面放个头节点，并且记录链表长度） MyLinkedList
 * 头节点放不放数据（虚拟头）
 * 到处是坑，反思一下：
 * 1.首先得设计对，（结点类，链表类），链表类得放链表长度和头结点
 * 2.get操作，各类操作时候得盯着写，然后遇到头尾特殊情况也要记得处理
 */
public class MyLinkedList {

    int size;
    ListNode head;

    public MyLinkedList() {
        size = 0;
        head = new ListNode(0);
    }

    public int get(int index) {

        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode p = head;
        for (int i = 0; i <= index; i++) {
            if (p.next != null) {
                p = p.next;
            }
        }
        return p == null ? -1 : p.val;
    }

    public void addAtHead(int val) {
        ListNode p = new ListNode(val);
        p.next = head.next;
        head.next = p;
        size++;
    }

    public void addAtTail(int val) {
        ListNode p = head;
        while (p.next != null) {
            p = p.next;
        }
        ListNode newNode = new ListNode(val);
        p.next = newNode;
        size++;
    }

    public void addAtIndex(int index, int val) {

        //index>size不会插入
        if (index > size) {
            return;
        }
        //链表为空或者index<0，在头部插入
        if (head.next == null || index < 0) {
            addAtHead(val);
            return;
        }
        ListNode p = head;
        for (int i = 0; i < index; i++) {
            if (p.next != null) {
                p = p.next;
            } else {
                return;
            }
        }
        ListNode newNode = new ListNode(val);
        newNode.next = p.next;
        p.next = newNode;
        size++;

    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }

        ListNode q = head;
        for (int i = 0; i < index; i++) {
            if (q.next != null) {
                q = q.next;
            }
        }

        //尾结点删除
        if (index == size) {
            q.next = null;
        } else {
            q.next = q.next.next;
        }

        size--;
    }

    public class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
