package labuladong.图.Dijkstra;

import java.util.*;

/**
 * 标准 Dijkstra 算法是计算最短路径的，但你有想过为什么 Dijkstra 算法不允许存在负权重边么？
 * <p>
 * 因为 Dijkstra 计算最短路径的正确性依赖一个前提：路径中每增加一条边，路径的总权重就会增加。
 * <p>
 * 这个结论反过来也是 OK 的：
 * <p>
 * 如果你想计算最长路径，路径中每增加一条边，路径的总权重就会减少，要是能够满足这个条件，也可以用 Dijkstra 算法。
 * <p>
 * 你看这道题是不是符合这个条件？边和边之间是乘法关系，每条边的概率都是小于 1 的，所以肯定会越乘越小。
 */
public class Solution1514 {

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {

        //1.建图
        List<double[]>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            int node1 = edges[i][0];
            int node2 = edges[i][1];
            double weight = succProb[i];
            graph[node1].add(new double[]{node2, weight});
            graph[node2].add(new double[]{node1, weight});
            //?????????????????????????????????????????????疑问：double的数组里存int变量为什么不报错
        }

        //2.执行dijkstra
        return dijkstra(start, end, graph);

    }

    class State {
        int id;
        //从start节点到当前节点的概率
        double probFromStart;

        public State(int id, double probFromStart) {
            this.id = id;
            this.probFromStart = probFromStart;
        }
    }

    private double dijkstra(int start, int end, List<double[]>[] graph) {

        //1.先把要用的变量构造出来：1存最大概率的，2遍历过程的队列

        //1.1 存最大概率的数组
        double[] maxProb = new double[graph.length];
        Arrays.fill(maxProb, -1);
        //start到start的概率是1
        maxProb[start] = 1;

        // 1.2优先级队列，probFromStart 较大的排在前面
        Queue<State> pq = new PriorityQueue<>((a, b) -> {
            return Double.compare(b.probFromStart, a.probFromStart);
        });


        //2.开始遍历 从start开始
        pq.offer(new State(start, 1));
        while (!pq.isEmpty()) {

            //从队列中弹出一个
            State currState = pq.poll();
            int currStateID = currState.id;
            double currStatePromFromStart = currState.probFromStart;

            //若当前弹出的节点就是终点，则直接返回
            if (currStateID == end) {
                return currStatePromFromStart;
            }

            //判断弹出的这个节点的到终点的概率是否更大，若不是就不必继续走这个节点了，若概率更大，则需要继续走这个节点遍历更新把邻居节点也加入
            if (currStatePromFromStart < maxProb[currStateID]) {
                continue;
            }

            for (double[] neighbor : graph[currStateID]) {
                int nextNodeId = (int) neighbor[0];
                //看看路过currNode去nextNode到终点的概率是否会更大
                double probToNextNode = currStatePromFromStart * neighbor[1];
                if (probToNextNode > maxProb[nextNodeId]) {
                    maxProb[nextNodeId] = probToNextNode;
                    pq.offer(new State(nextNodeId, probToNextNode));
                }
            }
        }
        //若到这里，说明从start无法到end 返回0
        return 0;
    }
}
