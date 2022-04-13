package 代码随想录.链表;

/**
 * 两两交换链表中的节点
 * 思路：用递归或迭代
 * <p>
 * 法一：迭代
 * 思路：需要三个指针，每次处理需要三步，然后再把指针后移，继续迭代处理（注意循环终止条件）
 *
 * <p>
 * 法二：递归
 * 反思：写递归要观察本级递归的解决过程，形成抽象模型，因为递归本质就是不断重复相同的事情。而不是去思考完整的调用栈，一级又一级，无从下手。
 * 其中我们应该关心的主要有三点：
 * 1.返回值
 * 2.调用单元做了什么
 * 3.终止条件
 * 在本题中：
 * 1.返回值：交换完成的子链表
 * 2.调用单元：设需要交换的两个点为 head 和 next，head 连接后面交换完成的子链表，next 连接 head，完成交换
 * 3.终止条件：head 为空指针或者 next 为空指针，也就是当前无节点或者只有一个节点，无法进行交换
 */

public class Solution24 {

    public ListNode swapPairs1(ListNode head) {

        //1.设一个头结点 方便处理
        ListNode preHead = new ListNode();
        preHead.next = head;

        //2.设两个指针left,right用来记录要交换的两个结点位置，一个pre用来记录要交换两节点的前一个结点。初始时都指向虚拟头节点
        ListNode left = preHead;
        ListNode right = preHead;
        ListNode pre = preHead;
        //3.开始迭代，循环结束条件是 右边再没有节点 或 只有一个结点的时候 就不必再交换 （也就是说 right右边至少有两个结点的时候 才继续迭代）
        while (right != null && right.next != null && right.next.next != null) {

            //3.1 初始时从前两个结点开始处理
            left = left.next;
            right = right.next.next;

            //3.2 进行处理，需要三步交换指针
            pre.next = right;
            left.next = right.next;
            right.next = left;

            //3.3 处理完毕之后 再把三个指针后移，移到初始状态（left,right,temp都指向preHead）继续迭代
            pre = left;
            right = left;
        }
        //不能直接return head 因为head已经被换在第二个位置上了，而第一个位置可以通过preHead获取
        return preHead.next;
    }


    public ListNode swapPairs(ListNode head) {

        //终止条件
        if (head == null || head.next == null) {
            return head;
        }

        //假设链表是1-2-3-4
        //这句先存下2
        ListNode right = head.next;
        //继续递归处理3-4，当递归结束返回时，就变成4-3。此时head结点就指向4，变成1-4-3
        head.next = swapPairs(right.next);
        //再把2指向1
        right.next = head;
        return right;

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
