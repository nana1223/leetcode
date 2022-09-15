package labuladong.图.Dijkstra;

import java.net.Inet4Address;
import java.util.*;

/**
 * 迪杰斯特拉算法：用来求图中一个节点到其他节点的最短距离
 * https://labuladong.github.io/algo/2/22/56/
 * <p>
 * 理解：
 * 1.while 循环每执行一次，都会往外拿一个元素，但想往队列里放元素，可就有很多限制了，
 * 如果你能让到达 nextNodeID 的距离更短，那就更新 distTo[nextNodeID] 的值，让你入队，否则的话对不起，不让入队。
 * 因为两个节点之间的最短距离（路径权重）肯定是一个确定的值，不可能无限减小下去，所以队列一定会空，队列空了之后，distTo 数组中记录的就是从 start 到其他节点的最短距离。
 * <p>
 * 2.用 PriorityQueue 而不是 LinkedList 实现的普通队列。（把 PriorityQueue 改成 LinkedList，也能得到正确答案，但是效率会低很多）
 * Dijkstra 算法使用优先级队列，主要是为了效率上的优化，类似一种贪心算法的思路。
 * 使用 PriorityQueue 作为队列，让 distFromStart 的值较小的节点排在前面，这就类似我们之前讲 贪心算法 说到的贪心思路，可以很大程度上优化算法的效率。
 *
 * @author zhangna
 */
public class Dijkstra {

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
    private int getWeight(int from, int to) {
//这个 weight 方法可以根据实际情况而定，因为不同的算法题，题目给的「权重」含义可能不一样，我们存储权重的方式也不一样。
        return 0;
    }

    /**
     * 输入节点s，返回节点s的相邻节点
     */
    private List<Integer> adj(int s) {
//        int nodeNum = 0;
//        List<Integer>[] graph = new LinkedList[nodeNum];
//        return graph[s];
        return new LinkedList<>();
    }

    /**
     * 输入一幅图和一个起点 start，计算 start 到其他节点的最短距离, 返回是一个记录最短路径权重的数组。
     */
    public int[] dijkstra(int start, List<Integer>[] graph) {

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
            for (int nextNodeId : adj(currentNodeId)) {
                //看看从start经过currentNode到达nextNode的距离 会不会比 直接从start到nextNode的距离 更短
                int distToNextNode = distTo[currentNodeId] + getWeight(currentNodeId, nextNodeId);
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
