package jianzhi_offer;

/**
 * 剑指 Offer 35. 复杂链表的复制
 * 题目解读：其实就是遍历输出当前节点的值和random指向的节点位置
 */
public class Solution35 {

    public Node copyRandomList(Node head) {

    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
