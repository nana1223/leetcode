package labuladong.图;

import java.util.LinkedList;
import java.util.List;

/**
 * 理解题干：互相讨厌的人不能放在同一组里，相当于图中的所有相邻节点都要放进两个不同的组，————也就是dislikes中的两个节点是相邻节点
 */
public class Solution886 {

    private List<Integer>[] buildGraph(int n, int[][] dislikes) {
        //图节点编号是1……n，注意细节！！！
        List<Integer>[] graph = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : dislikes) {
            int node1 = edge[0], node2 = edge[1];
            graph[node1].add(node2);
            graph[node2].add(node1);
        }
        return graph;
    }

    private boolean[] visited;
    private boolean[] color;
    private boolean isBinaryGraph = true;

    private void traverse(List<Integer>[] graph, int node) {
        if (!isBinaryGraph) {
            return;
        }
        visited[node] = true;
        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                color[neighbor] = !color[node];
                traverse(graph, neighbor);
            } else {
                if (color[neighbor] == color[node]) {
                    isBinaryGraph = false;
                    return;
                }
            }
        }
    }

    public boolean possibleBipartition(int n, int[][] dislikes) {

        //1->n 注意细节！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
        visited = new boolean[n + 1];
        color = new boolean[n + 1];

        //1.建图，用邻接表存
        List<Integer>[] graph = buildGraph(n, dislikes);

        //2.遍历图，遍历的时候判断是否是二分图
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                traverse(graph, i);
            }
        }
        return isBinaryGraph;
    }


}
