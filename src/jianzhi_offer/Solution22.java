package jianzhi_offer;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 22. 链表中倒数第k个节点
 * <p>
 * 思路：空间换时间
 * <p>
 * 利用哈希表，在遍历的过程中存下。怎么存能第二次直接拿到对应的节点？
 */
public class Solution22 {

    public ListNode getKthFromEnd(ListNode head, int k) {

        // 下标位置-节点（位置从1开始
        Map<Integer, ListNode> dic = new HashMap<>();

        int index = 1;
        ListNode p = head;
        while (p != null) {
            dic.put(index++, p);
            p = p.next;
        }

        int loc = dic.size() - k + 1;
        return dic.get(loc);
    }
}
