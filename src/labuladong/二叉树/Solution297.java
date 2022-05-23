package labuladong.二叉树;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 297.二叉树的序列化与反序列化
 * 实际是将树拉成字符串，再把字符串组成树的过程。（！！！！！！！！！！实际就是二叉树和字符串相互转换的过程）
 * ———其实是遍历二叉树的过程 （采用哪种遍历方法自己决定）
 */
public class Solution297 {


    //用于分隔
    private String SEPARATE = ",";
    //用于表示空
    private String NULL = "#";

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    /**
     * 前序遍历二叉树并存入String
     */
    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEPARATE);
            return;
        }
        sb.append(root.val).append(SEPARATE);

        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    /**
     * 给定字符串前序遍历构造二叉树，注意处理，#
     * 处理方法：把字符串都放在list里面 处理完一个删一个
     */
    public TreeNode deserialize(String data) {

        String[] dataArray = data.split(",");
        List<String> dataList = new ArrayList<>(Arrays.asList(dataArray));
        return deserialize(dataList);
    }

    private TreeNode deserialize(List<String> dataList) {
        if ("#".equals(dataList.get(0))) {
            dataList.remove(0);
            return null;
        }

        TreeNode rootNode = new TreeNode(Integer.parseInt(dataList.get(0)));
        dataList.remove(0);

        rootNode.left = deserialize(dataList);
        rootNode.right = deserialize(dataList);
        return rootNode;
    }

}
