package jianzhi_offer;

import labuladong.二叉树.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 剑指 Offer 27. 二叉树的镜像
 * <p>
 * 遍历树，然后换每个节点的左右孩子
 */
public class Solution27 {
    public TreeNode mirrorTree(TreeNode root) {

        //结束条件：root为空
        if (root == null) {
            return null;
        }

        //若左右孩子都不为空，则左右互换；
        //若左孩子为空，则让左指针指向右孩子，右孩子赋空；
        //若右孩子为空，则让右指针指向左孩子，左孩子赋空；
        if (root.left != null && root.right != null) {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
        } else if (root.left == null && root.right != null) {
            root.left = root.right;
            root.right = null;
        } else if (root.left != null && root.right == null) {
            root.right = root.left;
            root.left = null;
        }

        //遍历左右孩子
        mirrorTree(root.left);
        mirrorTree(root.right);

        return root;
    }
}
