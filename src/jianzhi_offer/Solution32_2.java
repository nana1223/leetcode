package jianzhi_offer;

import labuladong.二叉树.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 剑指 Offer 32 - II. 从上到下打印二叉树 II
 * <p>
 * 要每一层打印到一行。
 * <p>
 * 层序遍历，广度遍历BFS，利用队列。
 * <p>
 * 每一层要分开。怎么确定层呢？通过child来确定层。所以确定child之前得把当下的node记下以及数据存成一个list
 */
public class Solution32_2 {

    public List<List<Integer>> levelOrder(TreeNode root) {

        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();

        //队列
        Deque<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.offer(root);
        }

        while (!queue.isEmpty()) {

            //当前层打印循环。循环次数为当前层节点数，即队列queue的长度
            List<Integer> resPart = new ArrayList<>();
            //！！！【巧思】：for循环从高到低。就可以避免queue长度的变化（因为会往里加child）直接i = queue.size()赋值
            for (int i = queue.size(); i > 0; i--) {
                //1.出队
                TreeNode node = queue.poll();
                //2.打印
                resPart.add(node.val);
                //3.添加子节点
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(resPart);
        }
        return res;
    }
}
