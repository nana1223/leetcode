package labuladong.二叉树;

import java.util.ArrayList;
import java.util.List;

public class Solution144 {

    List<Integer> resList = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        traversal(root);
        return resList;
    }

    private void traversal(TreeNode root) {

        if (root == null) {
            return;
        }

        resList.add(root.val);
        traversal(root.left);
        traversal(root.right);
    }
}
