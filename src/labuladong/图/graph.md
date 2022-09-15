# 1. 图的遍历框架
1. 先看多叉树的遍历框架
~~~java
/* 多叉树遍历框架 */
void traverse(TreeNode root) {
    if (root == null) return;
    // 前序位置
    for (TreeNode child : root.children) {
        traverse(child);
    }
    // 后序位置
}
~~~

2. 多叉树与图最大的区别：
图是可能包含环的，你从图的某一个节点开始遍历，有可能走了一圈又回到这个节点，而树不会出现这种情况，从某个节点出发必然走到叶子节点，绝不可能回到它自身。
所以，如果图包含环，图的遍历框架就要一个 visited 数组进行辅助

3. 图的DFS遍历框架
~~~java
// 记录被遍历过的节点
boolean[] visited;
// 记录从起点到当前节点的路径
boolean[] onPath;

/* 图遍历框架 */
void traverse(Graph graph, int s) {
    if (visited[s]) 
        return;
    
    /* 前序遍历代码位置 */
        
    // 经过节点 s，标记为已遍历
    visited[s] = true;
    // 做选择：标记节点 s 在路径上
    onPath[s] = true;
    for (int neighbor : graph.neighbors(s)) {
        traverse(graph, neighbor);
    }
    // 撤销选择：节点 s 离开路径
    onPath[s] = false;

    /* 后序遍历代码位置 */
}
~~~
4. BFS思路写207 210 【还没看，得看】
   https://labuladong.github.io/algo/2/22/51/
- BFS思路：https://labuladong.github.io/algo/4/31/111/
5. 图论的经典算法
   1. 二分图判定
      1. 二分图的定义：给你一幅「图」，请你用两种颜色将图中的所有顶点着色，且使得任意一条边的两个端点的颜色都不相同，你能做到吗？
      这就是图的「双色问题」，其实这个问题就等同于二分图的判定问题，如果你能够成功地将图染色，那么这幅图就是一幅二分图，反之则不是
      2. 判定二分图的算法：说白了就是遍历一遍图，一边遍历一边染色，看看能不能用两种颜色给所有节点染色，且相邻节点的颜色都不相同。（DFS或BFS）
   2. 并查集Union-find算法
   3. 最小生成树算法：kruskal和Prim
   4. 最短路径算法：1迪杰斯特拉算法Dijkstra