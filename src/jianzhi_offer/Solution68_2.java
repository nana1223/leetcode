package jianzhi_offer;

/**
 * 剑指 Offer 68 - II. 二叉树的最近公共祖先
 * <p>
 * 思考：遍历是从上往下遍历，但是要求的是公共祖先是先走到的位置
 * 所以，要用一种能记住之前节点的方法来做——————【递归】
 * <p>
 * 递归的过程：改root节点
 * 递归结束的条件：root == p || root == q 说明遍历到了这两节点
 * <p>
 * 思路：
 * 1、在左、右子树中分别查找是否包含p或q，如果（两种情况：左子树包含p，右子树包含q/左子树包含q，右子树包含p），
 * 那么此时的根节点就是最近公共祖先
 * 2、如果左子树包含p和q，那么到root->left中查找，最近公共祖先在左子树里面
 * 3、如果右子树包含p和q，那么到root->right中查找，最近公共祖先在右子树里面
 * 4、注意：不可能left和right的返回值同时都是nullptr
 */
public class Solution68_2 {
    /**
     * 思路：找两个节点的最近公共祖先，首先肯定是递归遍历
     * 1.递归结束条件：找到pq，即 root ==p || root == q ； 走到叶子节点，返回null
     * 2.递推工作怎么进行：若pq在当前节点root的左右两侧，那就返回root；若pq都在root的左孩子，就去左边找；若都在右边就都去右边找
     * 3.递归参数，不断更新遍历的root节点
     */

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        //1.递归结束条件
        if (root == p || root == q) {
            return root;
        }
        if (root == null) {
            return null;
        }

        //2.递推过程：左右遍历，然后判断pq分别对于root的位置
        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);

        //若pq在当前节点root的左右两侧，那就返回root；若pq都在root的左孩子，就去左边找；若都在右边就都去右边找
        if (leftNode != null && rightNode != null) {
            return root;
        }
        return leftNode == null ? rightNode : leftNode;
    }


    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {

        //1.递归结束条件：遇到叶子节点返回，或者遇到p,q节点返回
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }

        //2.递归
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        //3.递归的结果判别：看p,q是否分别在root的两侧
        //3.1 当left和right均不为空，说明pq分别在root的两侧
        if (left != null && right != null) {
            return root;
        }
        //3.2 若left为空，说明pq均在root的右侧，那么此时返回的right就是最近公共父节点；right为空是同理
        return left == null ? right : left;

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }
}
