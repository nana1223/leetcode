package jianzhi_offer;

import labuladong.二叉树.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 剑指 Offer 32 - III. 从上到下打印二叉树 III
 * <p>
 * 按之字型打印 从左到右从右到左再从左到右等等
 * <p>
 * 用队列暂存 实现层序遍历
 * 每层再循环 实现一层按一行输出
 * 怎么实现之字？奇数行从左到右  偶数行从右到左：加一个变量tag用来记录奇偶
 *
 * 反思：题过于复杂的时候就注意【拆分】：本题就是拆成三个需求：层序遍历、一层一行输出、还要之字
 */
public class Solution32_3 {

    public List<List<Integer>> levelOrder(TreeNode root) {

        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();

        //记录奇偶层
        int tag = 1;
        //队列
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        //循环队列
        while (!queue.isEmpty()) {

            Deque<Integer> temp = new ArrayDeque<>();
            //单层循环
            for (int i = queue.size(); i > 0; i--) {

                //1.出队
                TreeNode node = queue.poll();
                //2.打印
                if (tag % 2 != 0) {
                    temp.addLast(node.val);
                } else {
                    temp.addFirst(node.val);
                }
                //3.下一层入队
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            List<Integer> resPart = temp.stream().collect(Collectors.toList());
            res.add(resPart);
            tag++;
        }
        return res;
    }
}
