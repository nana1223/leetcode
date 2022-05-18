package labuladong.二叉树;

import java.util.HashMap;

/**
 * 从中序和后序构造二叉树  类似105
 */

public class Solution106 {

    HashMap<Integer, Integer> inValToIndex = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        for (int i = 0; i < inorder.length; i++) {
            inValToIndex.put(inorder[i], i);
        }

        return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);

    }

    private TreeNode build(int[] inorder, int inStart, int inEnd,
                           int[] postorder, int postStart, int postEnd) {

        //递归结束条件
        if (inStart > inEnd) {
            return null;
        }

        //1.从后序序列中找根节点
        int rootVal = postorder[postEnd];
        TreeNode rootNode = new TreeNode(rootVal);

        //2.从中序序列中找出值为rootVal的索引位置，然后以该位置划分为左右子树，
        int inIndex = inValToIndex.get(rootVal);
        int leftSize = inIndex - inStart;

        //3.再分别对左右子树执行相同操作
        rootNode.left = build(inorder, inStart, inIndex - 1,
                postorder, postStart, postStart + leftSize - 1);
        rootNode.right = build(inorder, inIndex + 1, inEnd,
                postorder, postStart + leftSize, postEnd - 1);
        return rootNode;
    }
}
