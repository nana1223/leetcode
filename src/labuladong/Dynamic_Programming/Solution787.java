package labuladong.Dynamic_Programming;

import java.util.*;

/**
 * 787. K 站中转内最便宜的航班
 * <p>
 * 很明显，这题就是个加权有向图中求最短路径的问题。
 * 法一：迪杰斯特拉算法
 * 法二：动态规划
 * <p>
 * 思路：动态规划
 * （第一反应是贪心算法？找当前价格最小的。但是贪心找出来的不一定是总的价格最小的。所以不能用贪心）
 * <p>
 * 1.定义dp：dp(i, k)表示从起点 src 出发，k 步之内（一步就是一条边）到达节点i 的最小路径权重为 dp(i, k)。
 * 所以要求的就是dp(dst, K+1)：K+1步就是K个中转站
 * <p>
 * ！！！注意：定义dp：要把所有变化的状态都放在dp的参数上，这道题限制变化的有两个变量：即所在节点和中转站数量！！！！！！！！！！！！！
 * <p>
 * 2.base case：(i==src)时=0，(k==0)步数用尽时即这条路走不通。要把所有状态变量都考虑
 * <p>
 * 3.状态转移：
 * <p>
 * ！！！注意：分析状态转移时需要【分解问题】：！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
 * <p>
 * K 步之内从 src 到 dst 的最小路径权重是多少？我不知道。
 * 但我可以把问题分解：
 * s1, s2 是指向 dst 的相邻节点，我只要知道 K - 1 步之内从 src 到达 s1, s2，那我就可以在 K 步之内从 src 到达 dst。
 * 也就是如下关系式：
 * dp(dst, k) = min(
 * dp(s1, k - 1) + w1,
 * dp(s2, k - 1) + w2
 * )
 * <p>
 * 通过以上分析：发现我们需要知道有哪些节点指向某个节点以及指向该节点的权重值，即需要知道入度。所以用一个数据结构来存每个节点的入度
 * <p>
 * 4.是有重叠子问题的：如果某个节点同时指向两个其他节点，那么这两个节点就有相同的一个入度节点，就会产生重复的递归计算。
 * <p>
 * ！！！怎么消除重叠子问题？找问题的「状态」。！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
 * 谁在变化？显然就是 dp 函数的参数 i 和 k，每次递归调用，目标点 i 和步数约束 k 在变化。
 * 所以，本题的状态有两个，应该算是二维动态规划，我们可以用一个 memo 二维数组或者哈希表作为备忘录，减少重复计算。
 * 我们选用二维数组做备忘录吧，注意 K 是从 1 开始算的，所以备忘录初始大小要再加一
 * <p>
 * <p>
 * 本题思路要点总结：
 * 1.定义dp：要把所有变化的状态都放在dp的参数上，这道题限制变化的有两个变量：即所在节点和中转站数量
 * 2.分析状态转移时需要【分解问题】
 * 3.怎么消除重叠子问题？找问题的「状态」。
 * <p>
 * 本题做题细节反思：
 * 超多细节需要注意：
 * 1.K是从1开始算的所以要给K++（这个地方还有点没绕开）
 * 2.入度的数据结构加数要用putIfAbsent()如果直接用put()已经存在key的话就会执行替换操作注意！
 * 3.注意找一个节点的所有入度的List时要判空，很可能一个节点没有入度
 * 4.注意赋给memo时要看res是否还是初始化的值，若是的话说明这个节点不可达。细节！！！！！！！
 */
public class Solution787 {

    //记录入度：to-[from, price]
    HashMap<Integer, List<int[]>> indegree;

    //备忘录
    int[][] memo;

    int src, dst;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        //细节！！！！！！K 是从 1 开始算的，所以备忘录初始大小要再加一
        k++;
        this.src = src;
        this.dst = dst;

        //1.填充入度的数据结构
        indegree = new HashMap<>();
        for (int i = 0; i < flights.length; i++) {
            int from = flights[i][0];
            int to = flights[i][1];
            int price = flights[i][2];
            //注意这里用 put()还是putIfAbsent() 细节！！！！！！！！！！！！！！（put会覆盖不对！！！）
            indegree.putIfAbsent(to, new LinkedList<>());
            indegree.get(to).add(new int[]{from, price});
        }

        //2.给备忘录初始化
        memo = new int[n][k + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dp(dst, k);
    }

    private int dp(int index, int k) {
        //1.base case
        if (index == src) {
            return 0;
        }
        if (k == 0) {
            return -1;
        }

        //2.备忘录查
        if (memo[index][k] != -1) {
            return memo[index][k];
        }

        //3.状态转移
        //3.1先找index节点的所有入度
        List<int[]> fromNodeInfo = indegree.get(index);
        //这里细节！！！！！！！注意：fromNodeInfo可能为null。即没有一个节点指向index
        //处理方法：要么对fromNodeInfo判空，要么判断indegree里是否包含index的key

        //初始化为最大值，方便等会取最小值
        int res = Integer.MAX_VALUE;
        if (indegree.containsKey(index)) {
            for (int[] from : fromNodeInfo) {
                int fromNode = from[0];
                int price = from[1];
                //3.2 找从src到达相邻入度节点所需的最小路径【分解问题】
                int subProblem = dp(fromNode, k - 1);
                if (subProblem != -1) {
                    //跳过无解情况
                    res = Math.min(subProblem + price, res);
                }
            }
        }
        //注意赋给memo时要看res是否还是初始化的值，若是的话说明这个节点不可达。细节！！！！！！！
        memo[index][k] = res == Integer.MAX_VALUE ? -1 : res;

        return memo[index][k];
    }


}
