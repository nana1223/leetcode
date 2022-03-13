package 排序算法;

public class Sort {

    /**
     * 直接插入排序
     * 思想：依次将待排序序列中的每一个记录插入到一个已排好序的序列中，直到全部记录都排好序
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {

        //1.起始状态 有序区有一个，无序区有n-1个：从数组中的第二个数开始看（即无序区的第一个数开始处理）
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j;
            //把无序区的第一个数按倒序依次和有序区的比大小，
            //若无序区的第一个数比当前有序区的数小，则把有序区当前的数后移一个；若无序区的第一个数比当前有序区的数arr[j]大，则退出。然后把无序区的第一个数赋给arr[j+1]
            for (j = i - 1; j >= 0; j--) {
                if (arr[j] > tmp) {
                    //把大于tmp的值往后挪一位
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = tmp;
        }
    }

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

    /**
     * 冒泡排序
     * 思路：两两比较相邻记录的关键字，若反序则交换，直到没有反序记录为止
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {

        //n个数据用冒泡走n-1趟即可有序，所以i从0~n-2
        for (int i = 0; i < arr.length - 1; i++) {

            //flag用来判断本趟是否进行了交换
            boolean flag = false;

        }
    }

    public static void main(String[] args) {
        int[] arr = {49, 38, 65, 97, 23, 22, 76, 1, 5, 8, 2, 0, -1, 22};
        //insertSort(arr);
        shellSort(arr);

        System.out.println("排序后:");
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

}
