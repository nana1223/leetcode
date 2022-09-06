package labuladong.图;

import java.util.LinkedList;
import java.util.List;

/**
 * 207. 课程表
 * 思路一：
 * 判断是否可能完成所有课程的学习——即判断是否是AOV网，而拓扑排序可以使AOV网中的前驱和后继关系都能得到满足。
 * 所以可以转换为，对所有课程进行拓扑排序，若成功排成即可完成所有学习，若不成功则不行
 * <p>
 * 拓扑排序的基本思路：先选一个没有前驱的顶点，并输出；然后删去该顶点，并且删去所有以该顶点为尾的弧
 * （若所有课程都有前驱，则表明有环，那必不可能做到）
 * 若没有环，则可以做到
 * <p>
 * <p>
 * 还不是很会！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
 * <p>
 * 思路二：
 * DFS 1.首先把题目的输入转化为一副有向图，2.然后再判断图中是否有环
 */
public class Solution207 {


    //1.建图
    List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        //图中共有numCourses个节点 [用邻接表存图结构，graph[s]是一个列表，存储着节点s所指向的节点]
        List<Integer>[] graph = new LinkedList[numCourses];
        //先初始化一个邻接表 不放数据的
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }
        //把数据放入邻接表中
        for (int[] edge : prerequisites) {
            int from = edge[1], to = edge[0];
            //添加一条从from到to的有向边
            graph[from].add(to);
        }
        return graph;
    }

    //2.判断有无环：【思考过程：先考虑下怎么遍历图，只要会遍历，就可以判断图中是否存在环】
    //可以把traverse看作在图中节点上游走的指针，只需要再添加一个布尔数组onPath记录当前traverse经过的路径（类似思路：可以把递归函数看成一个在递归树上游走的指针）

    //注意理解onPath和visited区别【类比贪吃蛇游戏，visited 记录蛇经过过的格子，而 onPath 仅仅记录蛇身。onPath 用于判断是否成环，类比当贪吃蛇自己咬到自己（成环）的场景】
    //onPath记录当前traverse经过的路径【注意是当前！】
    boolean[] onPath;
    //visited1记录遍历过的节点，防止走回头路
    boolean[] visited;
    boolean isHasCycle = false;

    private void traverse(List<Integer>[] graph, int s) {
        //先判断当前要遍历的节点是否在当前的路径上，若是说明存在环
        if (onPath[s]) {
            isHasCycle = true;
        }

        //当前遍历结束条件，【什么时候结束遍历？—— 1.已经找到环了。 2.当前节点已经遍历过了】
        if (isHasCycle || visited[s]) {
            return;
        }
        //开始遍历（从s开始）
        //1.把节点s标记为已遍历
        visited[s] = true;

        //2.开始遍历节点s【深度优先DFS】
        onPath[s] = true;
        for (int t : graph[s]) {
            traverse(graph, t);
        }
        //3.节点s遍历完成
        onPath[s] = false;
    }

    /**
     * 思路：1.首先把题目的输入转化为一副有向图，2.然后再判断图中是否有环
     */

    //用邻接表存图结构，graph[s]是一个列表，存储着节点s所指向的节点
    List<Integer>[] graph;

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        //1.建图
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);

        onPath = new boolean[numCourses];
        visited = new boolean[numCourses];

        //2.遍历图，（在遍历的过程中判断有无环)，注意，因为不是所有节点都相连，所以要以每个节点为起点调用一次DFS遍历算法
        for (int i = 0; i < numCourses; i++) {
            //遍历时传入图的存储结构邻接表和遍历的起始节点
            traverse(graph, i);
        }

        //没有环，就可以完成所有课程
        return !isHasCycle;
    }
}
