package jianzhi_offer;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 35. 复杂链表的复制
 * 题目解读：其实就是遍历输出当前节点的值和random指向的节点位置（注意不是random指向的节点值）
 * <p>
 * 思路一：遍历的过程中找一个数据结构（或许是队列）记下random指向的节点位置。
 * so，怎么记录节点位置？注意不同节点上的值是可能相同的【本题难点】————哈希表
 * <p>
 * 反思：思路一不太可行。因为你在一次遍历过程中random指向的节点可能还没出现在后面。所以需要两次遍历
 * <p>
 * 思路二：【两次遍历】：有两个要记录的分别是next和random。要新增一个数据结构用来保证random能找到
 * 第一次遍历：创建节点 + 构建哈希表为原节点和新节点的对应关系存下
 * 第二次遍历：利用第一次遍历构建的哈希表 构建next指针关系 + 构建random关系
 * <p>
 * 反思：遇到题目的思考过程：一次遍历不行就考虑两次。原位置不行就考虑新增位置来存放记录。就那么几种数据结构考虑用哪种
 *
 * 【哈希表】
 */
public class Solution35 {

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Map<Node, Node> oldToNewMap = new HashMap<>();
        //1.第一次遍历：遍历旧链表，创建节点 + 构建哈希表为原节点和新节点的对应关系
        Node oldPoint = head;
        while (oldPoint != null) {
            //构建新节点
            Node newPoint = new Node(oldPoint.val);
            //构建哈希表
            oldToNewMap.put(oldPoint, newPoint);
            //遍历
            oldPoint = oldPoint.next;
        }

        //2.第二次遍历：遍历旧链表，构建next指针关系 + 构建random关系（通过哈希表）
        Node secOldPoint = head;
        while (secOldPoint != null) {
            oldToNewMap.get(secOldPoint).random = oldToNewMap.get(secOldPoint.random);
            oldToNewMap.get(secOldPoint).next = oldToNewMap.get(secOldPoint.next);
            secOldPoint = secOldPoint.next;
        }
        //返回的是新链表的头
        return oldToNewMap.get(head);
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
