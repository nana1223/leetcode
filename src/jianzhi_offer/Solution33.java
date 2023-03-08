package jianzhi_offer;

/**
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列
 * <p>
 * 【后序遍历性质】：[左子树 | 右子树 | 根]
 * 【二叉搜索树性质】：左子树中所有节点的值 < 根节点的值；右子树中所有节点的值 > 根节点的值；其左、右子树也分别为二叉搜索树
 * 递归思路：
 * 1.递归参数：[i, j] 递归区域的左右边界
 * 2.递归结束条件：i >=j
 * 3.递推工作：
 * 1）划分左右子树区间
 * 2）判断是否为二叉搜索树，即判断大小（左子树区间内的所有节点都应小于postorder[j]，右子树区间内的所有节点都应大于postorder[j]）
 */
public class Solution33 {

    public boolean verifyPostorder(int[] postorder) {
        return dfs(postorder, 0, postorder.length - 1);
    }

    private boolean dfs(int[] postorder, int left, int right) {

        //1.递归结束条件
        if (left >= right) {
            return true;
        }

        //2.递推

        int i = left;
        while (postorder[i] < postorder[right]) {
            i++;
        }
        int rightFirstIndex = i;
        while (postorder[i] > postorder[right]) {
            i++;
        }
        // 正常情况下，如果左子树都小于根右子树都大于根，i是会走到right的位置
        return i == right && dfs(postorder, left, rightFirstIndex - 1) && dfs(postorder, rightFirstIndex, right - 1);

    }
}
