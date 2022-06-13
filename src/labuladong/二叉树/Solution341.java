package labuladong.二叉树;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 341. 扁平化嵌套列表迭代器
 * 思维理解：
 * 就是棵 N 叉树！！叶子节点是Integer类型，其val字段非空；其他节点都是List<NestedInteger>类型，其val字段为空，但是list字段非空，装着孩子节点。
 * 把一个NestedInteger扁平化 === 这不就等价于遍历一棵 N 叉树的所有「叶子节点」吗？
 */
public class Solution341 implements Iterator<Integer> {

    /**
     * 定义一个迭代器
     *
     * @param nestedList
     */
    private Iterator<Integer> iterator;

    public Solution341(List<NestedInteger> nestedList) {
        List<Integer> res = new ArrayList<>();

        for (NestedInteger node : nestedList) {
            //以每个结点为根节点进行遍历
            traverse(node, res);
        }

        //得到res列表的迭代器
        iterator = res.iterator();
    }

    @Override
    public Integer next() {
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    /**
     * 遍历以 root 为根的多叉树，将叶子节点的值加入 result 列表
     */
    private void traverse(NestedInteger root, List<Integer> res) {
        if (root.isInteger()) {
            //到达叶子结点
            res.add(root.getInteger());
            return;
        }

        //遍历N叉树的每一个叶子结点
        for (NestedInteger child: root.getList()) {
            traverse(child,res);
        }
    }

    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }
}