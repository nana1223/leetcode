package jianzhi_offer;

import labuladong.二叉树.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 07. 重建二叉树
 * <p>
 * 输入前序和中序结果，构建树并返回根节点
 * <p>
 * 思路：同时遍历两个数组，
 * <p>
 * 【前序遍历顺序】：[根节点 | 左子树 | 右子树] 排序
 * 【中序遍历顺序】：[左子树 | 根节点 | 右子树] 排序
 * <p>
 * 根据以上性质，可得出以下推论：
 * 1.前序遍历的首元素 为 树的根节点 node 的值。
 * 2.在中序遍历中搜索根节点 node 的索引 ，可将 中序遍历 划分为 [ 左子树 | 根节点 | 右子树 ] 。
 * 3.根据中序遍历中的左（右）子树的节点数量，可将 前序遍历 划分为 [ 根节点 | 左子树 | 右子树 ]
 * <p>
 * 通过以上三步，可确定 三个节点 ：1.树的根节点、2.左子树根节点、3.右子树根节点。
 * 然后【分治】
 * <p>
 * 【递归】：
 * 1.递归参数：根节点在前序遍历中的索引root，子树在中序遍历中的左边界left（即数组左边界），子树在中序遍历中的右边界right（数组右边界）
 * 2.递归结束条件：left > right，代表已经越过叶子节点
 * 3.递推工作
 * <p>
 * 【优化】：把中序遍历的值和索引对应的存到哈希表中
 * <p>
 * <p>
 * 【算法思路】：
 * 通过【前序遍历列表】确定【根节点 (root)】
 * 将【中序遍历列表】的节点分割成【左分支节点】和【右分支节点】
 * 递归寻找【左分支节点】中的【根节点 (left child)】和 【右分支节点】中的【根节点 (right child)】
 */
public class Solution07 {

    /**
     * 中序遍历的值-索引
     */
    Map<Integer, Integer> valueToIndex = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        for (int i = 0; i < inorder.length; i++) {
            valueToIndex.put(inorder[i], i);
        }

        return dfs(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode dfs(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {

        //1.递归结束条件
        if (preLeft > preRight) {
            return null;
        }

        //2.递推工作
        //2.1 先确定根节点
        // 前序遍历的第一个数，在中序遍历中定位根节点
        int inRootIndex = valueToIndex.get(preorder[preLeft]);
        // 建立根节点
        TreeNode root = new TreeNode(preorder[preLeft]);

        //2.2 得到左子树的节点数目
        int leftSubtreeSize = inRootIndex - inLeft;

        //2.3 递归的构建左子树，并连到根节点上
        root.left = dfs(preorder, inorder, preLeft + 1, preLeft + leftSubtreeSize, inLeft, inRootIndex - 1);

        //2.4 递归的构建右子树，并连到根节点
        root.right = dfs(preorder, inorder, preLeft + leftSubtreeSize + 1, preRight, inRootIndex + 1, inRight);
        return root;
    }
}
