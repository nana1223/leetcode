package labuladong.图;

import java.util.ArrayList;
import java.util.List;

/**
 * 797. 所有可能的路径
 * 输入的这个 graph 其实就是「邻接表」表示的一幅图，graph[i] 存储这节点 i 的所有邻居节点。
 * 要求找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序）
 * <p>
 * 思路：以 0 为起点遍历图，同时记录遍历过的路径，当遍历到终点时将路径记录下来即可。
 * 直接套用图的遍历框架
 *
 * 反思扩展：传值和传引用的问题要再看看
 */
public class Solution797 {

    /**
     * 记录所有路径
     */
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

        //维护一次遍历经过的路径
        List<Integer> path = new ArrayList<>();
        traverse(graph, 0, path);
        return res;
    }

    /**
     * 图的遍历框架
     *
     * @param graph
     * @param s
     * @param path
     */
    private void traverse(int[][] graph, int s, List<Integer> path) {
        //把节点s添加到path中
        path.add(s);

        //遍历到了第n-1个节点
        if (s == graph.length - 1) {
            //把整条路加到res中，然后再把path中的最后一个节点remove掉，维护正确的path
            //注意这里！！！要add的是new ArrayList<>(path)，而不是直接加path。
            // 因为 Java 函数参数传的是对象引用，所以向 res 中添加 path 时需要拷贝一个新的列表，否则最终 res 中的列表都是空的。
            //原因：变量 track所指向的列表 在深度优先遍历的过程中只有一份 ，深度优先遍历完成以后，回到了根结点，成为空列表。
            //在 Java 中对象类型变量在传参的过程中，复制的是变量的地址。这些地址被添加到 res 变量，但实际上指向的是同一块内存地址，因此会看到几个空的列表对象。
            //经典的传值和传引用的问题。
            res.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
            return;
        }
        //遍历每个相邻节点
        for (int node : graph[s]) {
            traverse(graph, node, path);
        }
        //从路径中移除节点s
        path.remove(path.size() - 1);
    }
}
