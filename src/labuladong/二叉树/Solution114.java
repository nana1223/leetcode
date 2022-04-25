package labuladong.二叉树;

/**
 * 二叉树展开为链表
 * <p>
 * 思路一：（暴力法）先用前序遍历把记录存下，然后再用链表连起来
 * <p>
 * 思路二：二叉树思路
 * 1.能否一次遍历解决？每次遍历要做什么？节点之间有何关联？
 * 注意 flatten 函数的签名，返回类型为 void，也就是说题目希望我们在原地把二叉树拉平成链表。
 * 这样一来，没办法通过简单的二叉树遍历来解决这道题了。
 * <p>
 * 2.能否分解问题？
 * 对于一个节点 x，可以执行以下流程：
 * 1、先利用 flatten(x.left) 和 flatten(x.right) 将 x 的左右子树拉平。
 * 2、将 x 的右子树接到左子树下方，然后将整个左子树作为右子树。
 * 这样，以 x 为根的整棵二叉树就被拉平了，恰好完成了 flatten(x) 的定义。
 */
public class Solution114 {

    public void flatten(TreeNode root) {

        if (root == null) {
            return;
        }

        flatten(root.left);
        flatten(root.right);

        //将 x 的右子树接到左子树下方，然后将整个左子树作为右子树。

        //1.先记下当前的左右子树
        TreeNode leftTree = root.left;
        TreeNode rightTree = root.right;

        //2.将左子树作为右子树
        root.left = null;
        root.right = leftTree;

        //3.把原先的右子树，接在新的右子树的末端
        TreeNode temp = root;
        while (temp.right != null) {
            temp = temp.right;
        }
        temp.right = rightTree;

        return;

    }


}
