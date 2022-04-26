package labuladong.二叉树;

/**
 * 654 最大二叉树：输入一个数组，输出根节点（层序遍历的二叉树）
 * 分解思路，对每个子序列要找最大值
 *
 * 反思：用笔画画更有思路
 */
public class Solution654 {

    public TreeNode constructMaximumBinaryTree(int[] nums) {

        return constructTree(0, nums.length - 1, nums);
    }

    private TreeNode constructTree(int left, int right, int[] nums) {
        //递归结束条件即子序列为空
        if (right - left < 0) {
            return null;
        }
        //1.找出当前数组序列中的最大值，并且创建一个新结点，将新结点=该值
        int maxNum = Integer.MIN_VALUE;
        int maxPos = -1;
        for (int i = left; i <= right; i++) {
            if (nums[i] > maxNum) {
                maxNum = nums[i];
                maxPos = i;
            }
        }
        TreeNode rootNode = new TreeNode(maxNum);

        //2.以当前位置划分左右子序列，再分别对左右子序列递归操作
        rootNode.left = constructTree(left, maxPos - 1, nums);
        rootNode.right = constructTree(maxPos + 1, right, nums);
        return rootNode;
    }
}
