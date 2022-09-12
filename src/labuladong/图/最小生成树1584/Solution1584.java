package labuladong.图.最小生成树1584;

import java.util.LinkedList;
import java.util.List;

public class Solution1584 {

    public int minCostConnectPoints(int[][] points) {

        //1.建图
        List<int[]>[] graph = buildGraph(points);
        //2.调Prim
        return new Prim(graph).getWeightSum();
    }

    private List<int[]>[] buildGraph(int[][] points) {
        List<int[]>[] graph = new LinkedList[points.length];
        for (int i = 0; i < points.length; i++) {
            graph[i] = new LinkedList<>();
        }
        //生成所有边以及权重
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int ix = points[i][0], iy = points[i][1];
                int jx = points[j][0], jy = points[j][1];
                int weight = Math.abs(ix - jx) + Math.abs(iy - jy);
                //用points中的索引表示坐标点
                graph[i].add(new int[]{i, j, weight});
                //建议轻易不要controlD 不然bug都是弱智的！！！
                graph[j].add(new int[]{j, i, weight});
            }
        }
        return graph;
    }
}
