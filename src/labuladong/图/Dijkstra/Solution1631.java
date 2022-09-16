package labuladong.图.Dijkstra;

import java.util.*;

/**
 * 1631. 还没看懂？？？？？？？？？？？？？？？？？？？？？？？？？？
 * 把二维数组中每个 (x, y) 坐标看做一个节点，它的上下左右坐标就是相邻节点，它对应的值和相邻坐标对应的值之差的绝对值就是题目说的「体力消耗」，你就可以理解为边的权重。
 * 这样一想，是不是就在让你以左上角坐标为起点，以右下角坐标为终点，计算起点到终点的最短路径？Dijkstra 算法是不是可以做到？
 * 只不过，这道题中评判一条路径是长还是短的标准不再是路径经过的权重总和，而是路径经过的权重最大值。
 */
public class Solution1631 {

    public int minimumEffortPath(int[][] heights) {

        //1.定义使用的变量：包括 1存最小体力消耗的effortMin 2执行Dijkstra算法过程中用到的队列

        int m = heights.length, n = heights[0].length;
        //定义：从 (0, 0) 到 (i, j) 的最小体力消耗是 effortToMin[i][j]
        int[][] effortToMin = new int[m][n];
        //给effortToMin初始化最大值（除了起点）
        for (int i = 0; i < m; i++) {
            Arrays.fill(effortToMin[i], Integer.MAX_VALUE);
        }
        effortToMin[0][0] = 0;

        //优先级队列， effortFromStart较小的排在前面
        Queue<State> pq = new PriorityQueue<>((a, b) -> {
            return a.effortFromStart - b.effortFromStart;
        });

        //2.执行Dijkstra算法

        //从起点(0,0)开始
        pq.offer(new State(0, 0, 0));
        while (!pq.isEmpty()) {
            State curState = pq.poll();
            int curX = curState.x;
            int curY = curState.y;
            int curEffortFromState = curState.effortFromStart;

            //到达终点提前结束
            if (curX == m - 1 && curY == n - 1) {
                return curEffortFromState;
            }

            //体力消耗没有更小 也没有必要继续
            if (curEffortFromState > effortToMin[curX][curY]) {
                continue;
            }

            //将 (curX, curY) 的相邻坐标装入队列 （BFS的思路）
            for (int[] neighbor : adj(heights, curX, curY)) {
                int nextX = neighbor[0];
                int nextY = neighbor[1];
                // 计算从 (curX, curY) 达到 (nextX, nextY) 的消耗   ????????????????????????????还没看懂这地方为什么求max
                int effortToNextNode = Math.max(
                        effortToMin[curX][curY],
                        Math.abs(heights[curX][curY] - heights[nextX][nextY]));

                //更新effortToMin表，同时加队列
                if (effortToMin[nextX][nextY] > effortToNextNode) {
                    effortToMin[nextX][nextY] = effortToNextNode;
                    pq.offer(new State(nextX, nextY, effortToNextNode));
                }
            }
        }
        // 正常情况不会达到这个 return
        return -1;
    }

    /**
     * 输入节点s，返回节点s的相邻节点
     * 对这道题来说，节点s就是点(x, y)，相邻节点就是上下左右节点
     */
    private List<int[]> adj(int[][] matrix, int x, int y) {

        List<int[]> neighbors = new ArrayList<>();

        int m = matrix.length;
        int n = matrix[0].length;
        //方向数组，上下左右的偏移坐标量
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        for (int[] dir : dirs) {

            int neighborX = x + dir[0];
            int neighborY = y + dir[1];
            //判断有无越界
            if (neighborX >= m || neighborX < 0 || neighborY >= n || neighborY < 0) {
                continue;
            }
            neighbors.add(new int[]{neighborX, neighborY});
        }
        return neighbors;

    }

    class State {
        int x;
        int y;
        // 从起点 (0, 0) 到当前位置的最小体力消耗（距离）
        int effortFromStart;

        State(int x, int y, int effortFromStart) {
            this.x = x;
            this.y = y;
            this.effortFromStart = effortFromStart;
        }
    }


}
