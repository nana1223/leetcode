package jianzhi_offer;

import labuladong.二叉树.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 剑指 Offer 34. 二叉树中和为某一值的路径
 * <p>
 * 思路：遍历二叉树
 * 递归 dfs
 * 1.递归参数：当前节点，以及到当前节点的时候的和值。
 * 返回值：到当前节点的时候的和值
 * 2.递归结束条件：走到叶子节点，或者还没到叶子节点和已经大于target
 * 3.递推条件：遍历完左遍历右
 * <p>
 * 问题：怎么能在递归的过程中记录和？
 * 递归过程要利用前一次递归的结果值这种情况怎么处理？
 *
 * 【反思】：递归过程有不断递推的变量，放在递归参数中；有每次递归要记录的值，放在递归外面的列表中存储
 */
public class Solution34 {

    LinkedList<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int target) {

        dfs(root, target, 0);
        return res;
    }

    private void dfs(TreeNode root, int target, int sum) {
        //1.递归结束条件：走到叶子节点，
        // 【注意】这里不能把还没到叶子节点和已经大于target当作递归结束的条件之一，因为节点可能是负数
        if (root == null) {
            return;
        }

        // 2.处理当前节点：把当前节点加入路径 + 加上当前节点的和值
        path.add(root.val);
        sum += root.val;

        // 3.递归结束的成功条件：和等于target 并且 【已经走到了叶子节点（就是再没有孩子了）】
        if (root.left == null && root.right == null && sum == target) {
            res.add(new LinkedList<>(path));

        }

        // 3.递推
        dfs(root.left, target, sum);
        dfs(root.right, target, sum);
        // 4.路径恢复
        path.removeLast();
    }
}
