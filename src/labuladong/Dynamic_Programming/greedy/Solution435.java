package labuladong.Dynamic_Programming.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 435. 无重叠区间
 * <p>
 * 贪心思路：注意考虑这道题怎么才能让局部最优进而整体最优
 * 思考过程：
 * 1.这道题整体最优是什么：移除区间最少，也就是剩下区间最多
 * 2.每次怎么选择才能使剩下的区间最多：以区间的end来排序，然后选取end值和下一个区间没有交集的，依次选下去（注意以start值来考虑就不是最优，因为区间长度是不一致的）
 */
public class Solution435 {

    public int eraseOverlapIntervals(int[][] intervals) {
        //至少有一个区间不相交
        int count = 1;
        int n = intervals.length;
        //1.以每个区间的end值把所有区间重新排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        //2.挨个访问每个区间，遇到重叠的就去掉
        //排序后的第一个区间是会被留下的
        int x_end = intervals[0][1];
        int start;
        for (int[] interval : intervals) {
            start = interval[0];
            //找下一个要被留下的区间：条件是下一个留下的区间的start>=end
            if (start >= x_end) {
                count++;
                x_end = interval[1];
            }
        }
        //最后要求的是去除的区间数
        return n - count;
    }

}
