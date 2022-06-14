package labuladong.二叉树;

/**
 * 236. 二叉树的最近公共祖先
 */
public class Solution236 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) {
            return null;
        }

        //若p、q为根节点，则公共祖先为根节点
        if (root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        //p、q都没找到，那就没有
        if (root == null && right == null) {
            return null;
        }

        //左子树没有p也没有q，就返回右子树的结果
        if (left == null) {
            return right;
        }

        //右子树没有p也没有q就返回左子树的结果
        if (right == null) {
            return left;
        }

        //左右子树都找到p和q了，那就说明p和q分别在左右两个子树上，所以此时的最近公共祖先就是root
        return root;
    }


}
