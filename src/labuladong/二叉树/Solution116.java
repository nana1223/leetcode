package labuladong.二叉树;

public class Solution116 {

    /**
     * 遍历思路
     * 传统的 traverse 函数是遍历二叉树的所有节点，但现在我们想遍历的其实是两个相邻节点之间的「空隙」。
     * 所以我们可以在二叉树的基础上进行抽象，你把图中的每一对相邻的两个结点看做一个节点：
     * 这样，一棵二叉树被抽象成了一棵三叉树，三叉树上的每个节点就是原先二叉树的两个相邻节点。
     * <p>
     * 现在，我们只要实现一个 traverse 函数来遍历这棵三叉树，每个「三叉树节点」需要做的事就是把自己内部的两个二叉树节点穿起来：
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {

        if (root == null) {
            return null;
        }

        //遍历三叉树：root.left, root.right合起来看作三叉树的一个节点
        traverse(root.left, root.right);
        return root;
    }

    private void traverse(Node node1, Node node2) {

        if (node1 == null || node2 == null) {
            return;
        }

        node1.next = node2;
        traverse(node1.left, node1.right);
        traverse(node1.right, node2.left);
        traverse(node2.left, node2.right);
        return;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    ;

}
