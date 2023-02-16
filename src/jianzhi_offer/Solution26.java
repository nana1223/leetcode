package jianzhi_offer;

import labuladong.二叉树.TreeNode;

/**
 * 剑指 Offer 26. 树的子结构
 * <p>
 * 判断B是不是A的子结构
 * <p>
 * 前序遍历A，若A遇到和B的根节点数一样的时候，就AB一起遍历判断数值和结构。若B遍历完了都一样就是true，若中途有不一样的就返回A开始的地方继续往下走找下一个相等节点
 * 即：（两步）
 * 1.前序遍历A的每个节点nodeA，函数isSubStructure(A, B)
 * 2.对于每个节点nodeA，判断以nodeA为根节点的树是否包含B, 函数 recur(A, B)
 */
public class Solution26 {

    /**
     * 死死记住isSubStructure()的定义:判断B是否为A的子结构
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {

        //结束条件：A为空 或者 B为空
        if (A == null || B == null) {
            return false;
        }

        // B为A的子结构有3种情况,满足任意一种即可:
        // 1.B的子结构起点为A的根节点,此时结果为recur(A,B)
        // 2.B的子结构起点隐藏在A的左子树中,而不是直接为A的根节点,此时结果为isSubStructure(A.left, B)
        // 3.B的子结构起点隐藏在A的右子树中,此时结果为isSubStructure(A.right, B)
        return recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);

    }

    /**
     * 判断B是否为A的子结构,其中B子结构的起点为A的根节点
     */
    private boolean recur(TreeNode A, TreeNode B) {

        //结束条件：B为空 或者 A为空 或者 AB的值不同
        // 若B走完了,说明查找完毕,B为A的子结构
        if (B == null) {
            return true;
        }
        // 若B不为空并且A为空或者A与B的值不相等,直接可以判断B不是A的子结构
        if (A == null || A.val != B.val) {
            return false;
        }
        // 当A与B当前节点值相等,若要判断B为A的子结构。还需要判断B的左子树是否为A左子树的子结构 && B的右子树是否为A右子树的子结构
        // 若两者都满足就说明B是A的子结构,并且该子结构以A根节点为起点
        return recur(A.left, B.left) && recur(A.right, B.right);
    }
}
