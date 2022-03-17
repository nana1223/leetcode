package 排序算法;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 桶排序
 */
public class BucketSort {

    /**
     * 桶排序
     * 基本思路：划分多个范围相同的区间，每个子区间自排序，最后合并。
     *
     * @param arr
     */
    public static void bucketSort(int[] arr) {

        //1.计算该组数的最大值和最小值
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }

        //2.根据最大最小值 计算桶的数量
        int bucketNum = (max - min) / arr.length + 1;
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new ArrayList<>());
        }

        //3. 把每个数据放入桶中  要平均放 根据大小算具体放哪个桶
        for (int i = 0; i < arr.length; i++) {
            //计算所属的桶号
            int num = (arr[i] - min) / arr.length;
            bucketList.get(num).add(arr[i]);
        }

        //4.对每个桶内部进行排序  这里对桶内部排序时 可以选择其他排序算法
        for (int i = 0; i < bucketList.size(); i++) {
            Collections.sort(bucketList.get(i));
        }

        //5. 将桶中的元素赋值到原序列
        int index = 0;
        for (int i = 0; i < bucketList.size(); i++) {
            for (int j = 0; j < bucketList.get(i).size(); j++) {
                arr[index] = bucketList.get(i).get(j);
                index++;
            }
        }

    }
}
