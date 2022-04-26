package labuladong.二叉树;

import java.util.HashMap;

/**
 * 从前序与中序遍历序列构造二叉树
 * （两数组无重复元素）
 * <p>
 * 思路：分解思路
 * 前序序列就是：根节点+左子树+右子树
 * 中序序列就是：左子树+根节点+右子树
 * （然后对左右子树再有相同的排布）
 *
 * 反思：注意分析前序和中序的差异，核心在于根节点的位置不同，可以通过定根节点来定左右子树，然后再递归
 */

public class Solution105 {

    HashMap<Integer, Integer> inValToIndex = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        //存储inorder中 值和索引的映射
        for (int i = 0; i < inorder.length; i++) {
            inValToIndex.put(inorder[i], i);
        }

        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {

        if (preStart > preEnd) {
            return null;
        }
        //1.定根节点
        int rootVal = preorder[preStart];
        TreeNode rootNode = new TreeNode(rootVal);

        //2.在中序序列中找根节点所在位置
        int inIndex = inValToIndex.get(rootVal);

        //3.即可找出当前根节点的左右子树，再递归构造左右子树
        //左子树的节点个数
        int leftSize = inIndex - inStart;
        // 这里的参数分不清可以带入数据画画图就出来了
        rootNode.left = build(preorder, preStart + 1, preStart + leftSize, inorder, inStart, inIndex - 1);
        rootNode.right = build(preorder, preStart + leftSize + 1, preEnd, inorder, inIndex + 1, inEnd);
        return rootNode;
    }
}
