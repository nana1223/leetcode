package labuladong.二叉树;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 652.寻找重复的子树
 * 理解：站在一个结点处，需要知道以该结点为根的子树的结构和数据。以及以其他结点为根的子树的结构和数据
 * 解决：
 * 1.二叉树的前中后序遍历结果可以描述二叉树的结构，又因为要知道以自己为根的子树长啥样，就得先知道自己的左右子树长啥样，所以用后序遍历
 * 2.可以通过拼接字符串的形式把二叉树序列化用来比较结构数据是否相同
 * 3.为了知道别的子树的结构和数据，需要把每个结点为根的子树的序列化结果存下，又因为结果集不要重复的数据，所以可以用Map记录出现的次数
 * 反思：
 * 要干什么，步骤 一定要清晰；递归函数还要好好学
 */

public class Solution652 {

    /**
     * 存以每个结点为根节点的子树的结构和数据
     */
    Map<String, Integer> memo = new HashMap<>();

    /**
     * 存有相同的子树的结果
     */
    List<TreeNode> res = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traversal(root);
        return res;
    }

    private String traversal(TreeNode root) {
        if (root == null) {
            return "#";
        }

        String left = traversal(root.left);
        String right = traversal(root.right);
        String tempRoot = root.val + "," + left + "," + right;
        //若memo里面有该值且value=1，则加入res，且memo的value+1；
        //若memo里面有该值而value！=1，则memo的value+1；
        //若memo里面没有该值，则加入memo（即memo的value+1）；
        int times = memo.getOrDefault(tempRoot, 0);
        if (times == 1) {
            res.add(root);
        }
        memo.put(tempRoot, times + 1);
        return tempRoot;
    }

}
