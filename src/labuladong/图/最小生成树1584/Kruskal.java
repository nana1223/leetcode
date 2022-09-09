package labuladong.图.最小生成树1584;

import labuladong.图.UnionFind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Kruskal算法：
 * 1.先把所有的边列出来，并且把边按权重从小到大遍历
 * 2.然后遍历边的集合，对于每个边，先判断加入这条边会不会产生环，若不产生环就加入，若产生环就跳过
 * 3.在2的过程中，判断会不会产生环：就是判断这条边连着的两个节点是否已经在同一个连通分量上，若已经在了再加边就会有环。加入边=把边的两个节点所在的两个连通分量变成一个连通分量
 * ————这个过程中充分用到并查集算法
 */
public class Kruskal {

    public int minCostConnectPoints(int[][] points) {

        //1.首先生成所有的边以及边的权重，然后把边按从小到大排序
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int ix = points[i][0], iy = points[i][1];
                int jx = points[j][0], jy = points[j][1];
                //用坐标点在points中的索引表示坐标点
                edges.add(new int[]{i, j, (Math.abs(ix - jx) + Math.abs(iy - jy))});
            }
        }
        //把边排序（从小到大排序）
        Collections.sort(edges, (a, b) -> {
            return a[2] - b[2];
        });

        //2.然后对这些边执行Kruskal算法
        int res = 0;
        UnionFind unionFind = new UnionFind(points.length);
        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];
            int weight = edge[2];
            //判断node1和node2是否处于一个连通份量上，若是，则在两点之间加边的话就会产生环，所以不加边
            if (unionFind.connected(node1, node2)) {
                continue;
            }
            //若node1和node2在两个连通分量上，则不会产生环，属于最小生成树
            res += weight;
            unionFind.union(node1, node2);
        }
        return res;
    }
}
