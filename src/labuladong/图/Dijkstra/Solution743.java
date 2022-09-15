package labuladong.图.Dijkstra;

import java.util.*;

/**
 * 743. 网络延迟时间
 */
public class Solution743 {

    public int networkDelayTime(int[][] times, int n, int k) {

        //1.先根据题目建图 用邻接表存储
        List<int[]>[] graph = buildGraph(times, n);

        //2.用dijkstra算法，计算节点k到每个节点的最短路径distTo[],该数组的最大值就是能使所有结点都收到信号的时间值
        int distTo[] = dijkstra(k, graph);

        //3.处理数据，找到distTo中最大的且不是MAX_VALUE的数，就是所求
        int res = 0;
        //注意细节：i为什么从1开始：因为节点的标号是1到n，没有0节点 【！！！注意细节！！！】
        for (int i = 1; i < distTo.length; i++) {
            if (distTo[i] == Integer.MAX_VALUE) {
                return -1;
            }
            res = Math.max(res, distTo[i]);
        }
        return res;

    }

    private List<int[]>[] buildGraph(int[][] times, int n) {

        //1.初始化邻接表，
        // 注意
        // 1节点编号是从1到n，所以开一个n+1大小的邻接表
        // 2因为是有权重的图，所以邻接表存储图结构，同时存储权重信息,图的存储结构是List<int[]>[] graph,
        // graph[s]是存节点s所指向的节点的list， int[]存的是终点和权重, 即from -> List<(to, weight)>
        List<int[]>[] graph = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }

        //2.把times的值存入
        for (int i = 0; i < times.length; i++) {
            int fromNode = times[i][0];
            int toNode = times[i][1];
            int weight = times[i][2];
            // from -> List<(to, weight)>
            graph[fromNode].add(new int[]{toNode, weight});
        }
        return graph;
    }

    /**
     * 用 State 类记录一些额外信息，也就是使用 distFromStart 变量记录从起点 start 到当前这个节点的距离。
     * 在 Dijkstra 算法中，你第一次经过某个节点时的路径权重，不见得就是最小的，
     * 所以对于同一个节点，我们可能会经过多次，而且每次的 distFromStart 可能都不一样, ————取 distFromStart 最小的那次
     */
    private class State {
        //图节点的id
        int id;
        //从start节点到当前节点的距离
        int distFromStart;

        State(int id, int distFromStart) {
            this.id = id;
            this.distFromStart = distFromStart;
        }
    }

    /**
     * 返回节点from到节点to之间的权重
     */
    private int getWeight(int from, int to, List<int[]>[] graph) {
        for (int[] toAndWeight : graph[from]) {
            if (to == toAndWeight[0]) {
                return toAndWeight[1];
            }
        }
        return -1;
    }

    /**
     * 输入节点s，返回节点s的相邻节点
     */
    private List<Integer> adj(int s, List<int[]>[] graph) {

        List<Integer> sNext = new LinkedList<>();
        for (int[] toAndWeight : graph[s]) {
            sNext.add(toAndWeight[0]);
        }
        return sNext;
    }

    /**
     * 输入一幅图和一个起点 start，计算 start 到其他节点的最短距离, 返回是一个记录最短路径权重的数组。
     */
    public int[] dijkstra(int start, List<int[]>[] graph) {

        //1.定义相关变量并初始化

        //图中节点的个数
        int nodeNum = graph.length;

        //始终记录最短路径的权重，定义 distTo[i]就是节点start到节点i
        int[] distTo = new int[nodeNum];
        //给distTo初始化，【因为要求最小值，所以给distTo初始化最大值】
        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[start] = 0;

        //优先级队列，distFromStart较小的排在前面
        Queue<State> queue = new PriorityQueue<>((a, b) -> {
            return a.distFromStart - b.distFromStart;
        });


        //2.开始BFS遍历
        //从起点start开始进行
        queue.offer(new State(start, 0));
        while (!queue.isEmpty()) {
            State currentState = queue.poll();
            int currentNodeId = currentState.id;
            int currentDistFromStart = currentState.distFromStart;

            if (currentDistFromStart > distTo[currentNodeId]) {
                //已经有一条更短的路径到达currentNodeId节点了
                continue;
            }

            //把currentNode的相邻节点装入队列
            for (int nextNodeId : adj(currentNodeId, graph)) {
                //看看从start经过currentNode到达nextNode的距离 会不会比 直接从start到nextNode的距离 更短
                int distToNextNode = distTo[currentNodeId] + getWeight(currentNodeId, nextNodeId, graph);
                if (distToNextNode < distTo[nextNodeId]) {
                    //更新最短路径权重的数组
                    distTo[nextNodeId] = distToNextNode;
                    //把相邻节点放入队列
                    queue.offer(new State(nextNodeId, distToNextNode));

                }
            }
        }

        return distTo;
    }
}
