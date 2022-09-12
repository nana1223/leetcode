package labuladong.图.最小生成树1584;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Prim算法的核心原理：切分定理
 */
public class Prim {

    //核心数据结构，存 横切边 的优先级队列
    private PriorityQueue<int[]> priorityQueue;

    //类似visited数组的作用，记录哪些节点已经成为最小生成树的一部分
    private boolean[] inMST;

    //记录最小生成树的权重和
    private int weightSum = 0;

    /**
     * 1.graph是用邻接表表示的图
     * 2.graph[s]记录节点s所有相邻的边
     * 3.三元组int[]{from, to, weight}表示一条边
     */
    private List<int[]>[] graph;

    public Prim(List<int[]>[] graph) {

        //1.先初始化各个变量
        this.graph = graph;
        this.priorityQueue = new PriorityQueue<>((a, b) -> {
            // 按照边的权重从小到大排序
            return a[2] - b[2];
        });
        int n = graph.length;
        this.inMST = new boolean[n];

        //2.开始切分 （随便从哪个点开始，不妨从节点0开始）

        //把切分出来的节点0放入最小生成树中
        inMST[0] = true;
        cut(0);
        //然后不断进行切分
        while (!priorityQueue.isEmpty()) {
            int[] edge = priorityQueue.poll();
            int toNode = edge[1];
            int weight = edge[2];
            if (inMST[toNode]) {
                //节点toNode已经处在最小生成树当中
                continue;
            }
            //将边加入最小生成树
            weightSum += weight;
            inMST[toNode] = true;

            //节点toNode加入后，再进行新一轮切分，（会产生更多横切边）
            cut(toNode);
        }
    }

    //把节点node的横切边加入优先队列
    private void cut(int node) {

        //遍历node的邻边
        for (int[] neighborEdge : graph[node]) {
            int toNode = neighborEdge[1];
            if (inMST[toNode]) {
                //相邻节点toNode已经在最小生成树中，跳过
                continue;
            }
            //加入横切边队列
            priorityQueue.offer(neighborEdge);
        }
    }

    //获取最小生成树的权重和
    public int getWeightSum() {
        return weightSum;
    }

    //判断最小生成树是否包含图中所有节点
    public boolean isAllConnected() {
        for (int i = 0; i < inMST.length; i++) {
            if (!inMST[i]) {
                return false;
            }
        }
        return true;
    }

}
