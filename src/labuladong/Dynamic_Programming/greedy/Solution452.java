package labuladong.Dynamic_Programming.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 452. 用最少数量的箭引爆气球
 * 思路转换：若两个区间范围有交集就可以用一支箭解决；核心还是子区间交集问题（同435）
 */
public class Solution452 {

    public int findMinArrowShots(int[][] points) {
        //注意这里，最少肯定有一个区间，所以res初始化应该是1
        int res = 1;
        int n = points.length;
        //1.按end排序区间
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                //return o1[1] - o2[1]; 注意注意！！！直接这样写当数据极大或者极小接近类型上下界时会有溢出问题，最好用对应类型的静态方法来处理
                return Integer.compare(o1[1], o2[1]);
            }
        });
        //2.找没有交集的子区间，即所需箭数
        int x_end = points[0][1];
        int start;
        for (int[] point : points) {
            start = point[0];
            if (start > x_end) {
                res++;
                x_end = point[1];
            }
        }
        return res;
    }
}
