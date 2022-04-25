package labuladong.二叉树;

/**
 * 翻转二叉树————————即把二叉树上的每个结点的左右子结点进行交换
 * <p>
 * <p>
 * 那么现在开始在心中默念二叉树解题总纲：
 * <p>
 * 1、这题能不能用「遍历」的思维模式解决？
 * 可以，我写一个 traverse 函数遍历每个节点，让每个节点的左右子节点颠倒过来就行了。
 * 单独抽出一个节点，需要让它做什么？让它把自己的左右子节点交换一下。
 * 需要在什么时候做？好像前中后序位置都可以。
 * <p>
 * 2、这题能不能用「分解问题」的思维模式解决？
 * 我们尝试给 invertTree 函数赋予一个定义：
 * // 定义：将以 root 为根的这棵二叉树翻转，返回翻转后的二叉树的根节点
 * TreeNode invertTree(TreeNode root);
 * 然后思考，对于某一个二叉树节点 x 执行 invertTree(x)，你能利用这个递归函数的定义做点啥？
 * 我可以用 invertTree(x.left) 先把 x 的左子树翻转，再用 invertTree(x.right) 把 x 的右子树翻转，最后把 x 的左右子树交换，
 * 这恰好完成了以 x 为根的整棵二叉树的翻转，即完成了 invertTree(x) 的定义。
 */
public class Solution226 {

    /**
     * 遍历思路
     * 翻转二叉树：在遍历的过程中，给每个结点的左右孩子进行交换
     * (注意交换的是结点，不能单纯交换结点的数值，因为子节点也要交换)
     *
     * @param root
     * @return
     */

    public TreeNode invertTree1(TreeNode root) {
        if (root == null) {
            return null;
        }
        invertTree1(root.left);
        invertTree1(root.right);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;
    }

    /**
     * 分解思路
     * 我可以用 invertTree(x.left) 先把 x 的左子树翻转，再用 invertTree(x.right) 把 x 的右子树翻转，最后把 x 的左右子树交换，
     */
    public TreeNode invertTree(TreeNode root) {

        if (root == null) {
            return null;
        }
        TreeNode leftTree = invertTree(root.left);
        TreeNode rightTree = invertTree(root.right);

        root.left = rightTree;
        root.right = leftTree;

        return root;
    }
}



