package jianzhi_offer;

/**
 * 剑指 Offer 36. 二叉搜索树与双向链表
 * <p>
 * dfs：在遇到每个节点时，修改该接节点的两指针。然后再递推
 * 1.递归结束条件：root == null
 * 2.递归参数就是root
 * 3.递归过程：在遇到每个节点时，修改该接节点的两指针。然后再递推
 * <p>
 * 【q】：要在递推过程链接到上一次递归的参数和下一次递归的参数：————【中序遍历】————每次递归要记录的东西放在全局！！！
 */
public class Solution36 {

    //要在每次递归的过程中更新pre节点，所以需要把pre放在全局的位置【注意】
    Node pre;
    Node head;

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }

        dfs(root);
        // 【处理循环链表】最后递归结束的时候 head指向头，pre指向尾
        pre.right = head;
        head.left = pre;

        return head;
    }

    private void dfs(Node cur) {

        //1.递归结束条件
        if (cur == null) {
            return;
        }
        //2.递推
        dfs(cur.left);
        if (pre != null) {
            pre.right = cur;
        } else {
            //pre为空时，说明正在访问头节点
            head = cur;
        }
        cur.left = pre;
        //修改pre节点
        pre = cur;
        dfs(cur.right);
    }

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    ;
}
