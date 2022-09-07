package labuladong.图;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 210.课程表2
 * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组
 * <p>
 * 解读：要按顺序输出所有结点，就是遍历所有节点输出（应该DFS或者BFS都可以？）。————就是进行拓扑排序————拓扑排序的结果就是所有顺序课程
 * <p>
 * 问题？如何进行拓扑排序？？？ ————将后序遍历的结果进行反转，就是拓扑排序的结果
 * 注意：建图的时候对边的定义不同决定了结果是否需要反转。
 * 我建的图中箭头方向是「被依赖」关系，比如节点 1 指向 2，含义是节点 1 被节点 2 依赖，即做完 1 才能去做 2，
 * 如果你反过来，把有向边定义为「依赖」关系，那么整幅图中边全部反转，就可以不对后序遍历结果反转。
 * 具体来说，就是把我的解法代码中 graph[from].add(to); 改成 graph[to].add(from); 就可以不反转了
 */
public class Solution210 {


    //1.建图
    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {

        //1.用邻接表存图，先初始化一下【graph[s]表示节点s所指向的节点们】
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }

        //2.把prerequisites里面的数据信息放到邻接表中
        for (int[] edge : prerequisites) {
            int from = edge[1], to = edge[0];
            graph[from].add(to);
        }
        return graph;
    }

    //onPath用来放当前的路径, visited放遍历过的节点（防止重复遍历）
    boolean[] onPath, visited;
    //记录后序遍历的结果
    List<Integer> postorder = new ArrayList<>();
    //是否有环
    boolean hasCycle = false;

    //2.遍历图
    //注意理解：递归遍历，这种记录路径的，最好弄一个全局的变量每次遍历赋值，如果要是把要输出的东西弄成函数的返回值，就可能会弄乱而且增加难度【递归函数注意观察函数返回值】
    private void traverse(List<Integer>[] graph, int s) {

        //1.先判断如果有环，就不用遍历了，然后赋空数组
        if (onPath[s]) {
            hasCycle = true;
        }

        //2.再写遍历结束的条件，当前节点遍历过了 或者 有环 就返回
        if (visited[s] || hasCycle) {
            return;
        }

        //3.开始遍历
        //前序遍历位置
        //3.1先把节点s标记为已遍历
        visited[s] = true;
        //3.2 DFS进行遍历，从graph[s]开始依次访问graph[s]所连的链上的节点
        onPath[s] = true;
        for (int t : graph[s]) {
            traverse(graph, t);
        }
        //后序遍历的位置
        postorder.add(s);
        onPath[s] = false;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        onPath = new boolean[numCourses];
        visited = new boolean[numCourses];

        //1.建图
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);

        //2.遍历图，（因为不是所有节点都相连，所以要以每个节点为起点调用一次DFS遍历算法）
        for (int i = 0; i < numCourses; i++) {
            traverse(graph, i);
        }

        //3.处理结果
        //3.1有环图无法进行拓扑排序
        if (hasCycle) {
            return new int[]{};
        }
        //3.2无环图，然后对后序遍历的结果逆序一下
        Collections.reverse(postorder);
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = postorder.get(i);
        }
        return res;
    }
}
