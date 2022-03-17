package 排序算法;

import java.util.ArrayList;

/**
 * 基数排序
 */
public class RadixSort {
    /**
     * 基数排序
     * 思路：
     * 1.找出数组中的最大数，计算最大数是几位数，（最大数是几位数即循环几趟）
     * 2. 然后循环，依次比较个位数，十位数……
     * 每次循环的操作：个位数比较时：先按个位数分别进桶，然后再按序都出桶。然后再比较十位数，重复进桶出桶的操作。
     *
     * @param arr
     */
    public static void radixSort(int[] arr) {

        //1.找出数组中的最大数，计算最大数是几位数
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        //计算最大数的绝妙方法：转成String求长度
        int maxLength = (max + "").length();
        // 10个桶 放数字0-9 然后每个桶存一个链表
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            bucketList.add(new ArrayList<>());
        }

        //2.循环 第一轮个位数较易得到余数，第二轮就得先除以十再去取余，之后百位除以一百。可以看出，还有一个变量n随循环次数变化，为了取余
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {

            //位数相同的数据 进一样的桶
            split(bucketList, arr, n);
            //按序 出桶 再合并出一个数组排好
            merge(bucketList, arr);
        }
    }

    //将所有的元素 按位数 分散到各个桶中
    private static void split(ArrayList<ArrayList<Integer>> bucketList, int[] arr, int n) {

        for (int j = 0; j < arr.length; j++) {
            //计算余数，所得即位数上的数字是几 就进哪个桶
            int yuShu = arr[j] / n % 10;
            System.out.println(yuShu);
            bucketList.get(yuShu).add(arr[j]);
        }
    }

    //再把所有桶中的数据 按序拿出来排好
    private static void merge(ArrayList<ArrayList<Integer>> bucketList, int[] arr) {
        //index表示合并数组时的下标
        int index = 0;
        for (int i = 0; i < bucketList.size(); i++) {
            if (bucketList.get(i) != null) {
                for (int j = 0; j < bucketList.get(i).size(); j++) {
                    arr[index++] = bucketList.get(i).get(j);
                }
            }
            //合并完成之后需要将每个桶都清空,否则元素会越来越多
            bucketList.get(i).clear();
        }
    }

}
