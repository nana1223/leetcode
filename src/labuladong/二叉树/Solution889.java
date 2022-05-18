package labuladong.二叉树;

import java.util.HashMap;

/**
 * 从前序和后序构造二叉树
 */
public class Solution889 {

    // 存后序中值到索引的映射
    HashMap<Integer, Integer> postValToIndex = new HashMap<>();

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {

        for (int i = 0; i < postorder.length; i++) {
            postValToIndex.put(postorder[i], i);
        }

        return build(preorder, 0, preorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    private TreeNode build(int[] preorder, int preStart, int preEnd,
                           int[] postorder, int postStart, int postEnd) {

        //递归结束条件
        if (preStart > preEnd) {
            return null;
        }
        if (preStart == preEnd) {
            return new TreeNode(preorder[preStart]);
        }

        //1.在preorder找根节点
        int rootVal = preorder[preStart];
        TreeNode rootNode = new TreeNode(rootVal);

        //2.把preorder的第二个元素作为左子树的根节点的值
        int leftRootVal = preorder[preStart + 1];

        //3.在postorder中寻找左子树根节点的值，从而确定了左子树的索引边界，进而确定右子树的索引边界，递归构造左右子树即可
        int index = postValToIndex.get(leftRootVal);
        int leftSize = index - postStart + 1;

        rootNode.left = build(preorder, preStart + 1, preStart + leftSize,
                postorder, postStart, index);
        rootNode.right = build(preorder, preStart + leftSize + 1, preEnd,
                postorder, index + 1, postEnd - 1);

        return rootNode;

    }

}
