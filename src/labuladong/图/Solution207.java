package labuladong.图;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 207. 课程表
 * 判断是否可能完成所有课程的学习——即判断是否是AOV网，而拓扑排序可以使AOV网中的前驱和后继关系都能得到满足。
 * 所以可以转换为，对所有课程进行拓扑排序，若成功排成即可完成所有学习，若不成功则不行
 * <p>
 * 拓扑排序的基本思路：先选一个没有前驱的顶点，并输出；然后删去该顶点，并且删去所有以该顶点为尾的弧
 * （若所有课程都有前驱，则表明有环，那必不可能做到）
 * 若没有环，则可以做到
 *
 *
 * 还不是很会！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
 */
public class Solution207 {

    // 记录遍历过的节点，防止走回头路
    private boolean[] visited;

    // 记录一次递归堆栈中的节点
    private boolean[] onPath;

    // 记录图中是否有环
    private boolean hasCycle = false;

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        //1.建图，把prerequisites数组转换成图的存储方式（邻接表）
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);

        //2.判断图中有没有环（有环就不能完成所有课程，没环就可以完成），也就是看是否能进行拓扑排序
        //思考如何遍历这幅图，只要会遍历，就可以判断图中是否存在环了。
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            //注意图中并不是所有节点都相连，所以要用一个 for 循环将所有节点都作为起点调用一次 DFS 搜索算法。
            traverse(graph, i);
        }
        return !hasCycle;

    }

    private void traverse(List<Integer>[] graph, int s) {
        //图的遍历框架就是那样，然后要在遍历的过程中判断这幅图是否存在环，只需要再添加一个布尔数组 onPath 记录当前 traverse 经过的路径

        if (onPath[s]) {
            //出现环
            hasCycle = true;
        }

        if (visited[s] || hasCycle) {
            return;
        }

        // 前序代码位置

        visited[s] = true;
        onPath[s] = true;
        for (int node : graph[s]) {
            traverse(graph, node);
        }

        // 后序代码位置

        onPath[s] = false;

    }

    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {

        //图中共有numCourses个节点
        //1.先建出一个邻接表结构
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }

        //2.往邻接表中添加图的边的信息
        for (int[] edge : prerequisites) {
            //添加一条从from指向to的有向边，即修完课程 from 才能修课程 to
            int from = edge[1], to = edge[0];
            graph[from].add(to);
        }
        return graph;

    }

}
