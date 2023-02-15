package jianzhi_offer;

import labuladong.二叉树.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 剑指 Offer 32 - I. 从上到下打印二叉树
 * <p>
 * 层序遍历二叉树
 * <p>
 * 层序遍历，又是二叉树的广度优先搜索 BFS。 BFS通常借助【队列】实现（先入先出的特性）
 * <p>
 * 需要一个队列queue，
 * 初始化：把根节点放入queue中。
 * 对于队列中的每个节点node的操作：
 * 1.node出队并输出
 * 2.把node的左右孩子再依次入队（不为空的前提下）
 * 循环【直到队列空】
 * <p>
 * 小细节：节点为null的用入队列吗？————不用
 */
public class Solution32_1 {

    public int[] levelOrder(TreeNode root) {

        //特殊情况
        if (root == null) {
            return new int[]{};
        }

        List<Integer> resList = new ArrayList<>();

        //定义队列
        Deque<TreeNode> queue = new ArrayDeque<>();

        //初始化队列
        queue.addFirst(root);

        //循环直到队列空
        while (!queue.isEmpty()) {
            TreeNode node = queue.pollFirst();
            resList.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }

        //把结果处理成int[]
        int[] res = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }
        return res;
    }
}
