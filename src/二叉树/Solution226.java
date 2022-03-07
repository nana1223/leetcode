package 二叉树;

/**
 * 翻转二叉树————————即把二叉树上的每个结点的左右子结点进行交换
 */
public class Solution226 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 递归实现也就是深度优先遍历的方式
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        //交换当前节点的左右孩子
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        //递归交换当前结点的左子树 右子树
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}



