package labuladong.Dynamic_Programming.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1024. 视频拼接
 * 给定一个目标区间和若干小区间，如何通过裁剪和组合小区间拼凑出目标区间？最少需要几个小区间？
 * <p>
 * 思考过程：
 * 1.首先，他是一个区间问题
 * 2.区间问题，就要先排序，（按区间的起点或者终点排序），因为排序之后更容易找打相邻区间之间的关系
 * 3.对于这道题，因为要凑目标区间[0, time]，还要所需区间最小
 * <p>
 * 思路：先把所有区间按起点升序排序，如果起点相同的话按终点降序排序
 * 理解：
 * 1.要用若干短视频凑出完成视频 [0, T]，至少得有一个短视频的起点是 0。如果没有一个短视频是从 0 开始的，那么区间 [0, T] 肯定是凑不出来的。
 * 2.如果有几个短视频的起点都相同，那么一定应该选择那个最长（终点最大）的视频。
 * 这一条就是贪心的策略，因为题目让我们计算最少需要的短视频个数，如果起点相同，那肯定是越长越好
 */
public class Solution1024 {

    public static int videoStitching(int[][] clips, int time) {
        //注意这道题不能像452，435那样的直接初始说至少有一个res初始值设为1；
        //因为首先这道题的区间是可以切割的；其次这道题有[0,0]这样的就没必要选。所以有所变种 初始值为0
        int res = 0;
        int n = clips.length;
        //特殊点每次要记得考虑
        if (time == 0) {
            return 0;
        }

        //1.排序：按起点升序排序，起点相同的降序排序
        Arrays.sort(clips, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return Integer.compare(b[1], a[1]);
                }
                return Integer.compare(a[0], b[0]);
            }
        });
        //2.选择区间的过程：
        //2.1特殊情况处理：第一个区间的起始点不是从0开始的，根本构不成目标区间
        if (clips[0][0] != 0) {
            return -1;
        }
        //2.2定义所需变量
        int lastEnd = 0;
        int curEnd = 0;
        //2.3一步一步算
        int i = 0;
        while (i < n && clips[i][0] <= lastEnd) {
            //在当前区间和上一个区间的end有重叠的区间里 选一个最大的区间
            while (i < n && clips[i][0] <= lastEnd) {
                curEnd = Math.max(curEnd, clips[i][1]);
                i++;
            }
            //更新结果值和中间变量
            res++;
            lastEnd = curEnd;
            //当前区间已经可以拼出目标区间了
            if (lastEnd >= time) {
                return res;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] a = {{0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}};//[[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]]
        videoStitching(a, 10);
    }
}
