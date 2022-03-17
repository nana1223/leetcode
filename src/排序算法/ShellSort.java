package 排序算法;

public class ShellSort {

    /**
     * 希尔排序
     * 思想：先将整个待排序记录序列，按相距为d的记录作为一个子序列，在子序列内分别进行直接插入排序
     * 然后不断缩小分割间距d，直到d=1，即对全体记录进行一次直接插入排序
     *
     * @param arr
     */
    public static void shellSort(int[] arr) {
        //初始的d增量
        int d = arr.length / 2;

        //不断划分d，直到d=1
        while (d > 0) {
            //下面就是直接插入排序，对按d划分的子序列进行直接插入排序
            for (int i = 0; i < arr.length; i = i + d) {
                int tmp = arr[i];
                int j;
                for (j = i - d; j >= 0; j -= d) {
                    if (arr[j] > tmp) {
                        arr[j + d] = arr[j];
                    } else {
                        break;
                    }
                }
                arr[j + d] = tmp;
            }
            d /= 2;
        }
    }
}
