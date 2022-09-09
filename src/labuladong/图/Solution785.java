package labuladong.图;

public class Solution785 {


    private boolean[] visited;
    //color记录每个节点的颜色
    private boolean[] color;
    private boolean isBinaryGraph = true;

    private void traverse(int[][] graph, int node) {

        //1.考虑什么情况下结束遍历：1当前节点已经遍历过了 2已经知道不是二分图了 （注意这里结束条件不应该包括当前节点已经遍历过，因为遍历过还要判断颜色，不必退出）
        if (!isBinaryGraph) {
            return;
        }

        //2.DFS遍历，遍历过程中同时涂色
        visited[node] = true;
        for (int neighbor : graph[node]) {

            if (!visited[neighbor]) {
                //若相邻节点没有被访问过，就给相邻节点涂上与节点node不同的颜色然后访问
                color[neighbor] = !color[node];
                traverse(graph, neighbor);

            } else {
                //若相邻节点被访问过了，则比较相邻节点和node节点的颜色，看是否相同，若相同就不是二分图
                if (color[neighbor] == color[node]) {
                    isBinaryGraph = false;
                    return;
                }
            }
        }
    }

    //用邻接矩阵存储的图结构
    public boolean isBipartite(int[][] graph) {

        //初始化各变量
        color = new boolean[graph.length];
        visited = new boolean[graph.length];

        //遍历图
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                traverse(graph, i);
            }

        }
        return isBinaryGraph;
    }
}
