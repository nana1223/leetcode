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
     * 思路：两两比较相邻记录的关键字，若反序则交换，直到没有反序记录为止；
     * 两个优化操作：1设置flag记录是否交换数据；2进行处理的数据每次从0到arr.length - 1 - i
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {

        //n个数据用冒泡走n-1趟即可有序，所以i从0~n-2,i代表第几趟
        for (int i = 0; i < arr.length - 1; i++) {

            //flag用来判断本趟是否进行了交换
            boolean flag = false;
            //j指向要比较交换的数，若是第3趟，说明最后三个数已有序，所以j从0~ arr.length - 1 - i
            for (int j = 0; j < arr.length - 1 - i; j++) {

                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            //若本趟没有交换数据，说明已经全部有序，即可跳出循环
            if (!flag) {
                break;
            }

        }
    }

    /**
     * 快速排序
     * 核心思想：---------怎么处理能让基准数左边都比他小，右边都比他大 （由这个要注意不是左右指针比大小交换，而是左右指针要和基准数比大小然后左右指针交换）
     * 思路：
     * 1.先把数组中的一个数当作基准数（一般会把数组中最左边的数当作基准数）
     * 2.然后利用左右指针从两边进行检索。先从右边检索比基准数小的数，再从左边检索比基准数大的数。若左右指针都检索到了，就停下，然后交换左右指针所指的数。然后再继续检索
     * 3.当左右指针相遇时，就停止检索。把基准数位置的数据和指针相遇位置的数据交换。--------此时，一趟结束后，基准数左边都比他小，右边都比他大
     * 4.然后，对基准数的左右序列，再递归分别进行快排
     */
    public static void quickSort(int[] arr, int left, int right) {

        //左指针>右指针时不合法，递归结束
        if (left > right) {
            return;
        }

        //base代表基准数，选取第一个记录为轴值
        int base = arr[left];
        int i = left, j = right;
        while (i < j) {
            //先看右指针，与base比较，依次往左递减，当arr[j]<base时跳出循环
            while (i < j && arr[j] >= base) {
                j--;
            }
            while (i < j && arr[i] <= base) {
                i++;
            }
            //当前状态下，arr[j]<base，arr[i]>base，然后交换arr[j]与arr[i]的值
            int temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
        //循环结束时，左右指针相遇，交换基准数位置与相遇位置的元素
        arr[left] = arr[i];
        arr[i] = base;

        //至此，一趟结束，基准数左边都比他小，右边都比他大
        //然后对由基准数分割的左右两边，再次使用快排
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }


    public static void main(String[] args) {
        int[] arr = {49, 38, 65, 97, 23, 22, 76, 1, 5, 8, 2, 0, -1, 22};
        //insertSort(arr);
        //shellSort(arr);
        //bubbleSort(arr);
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序后:");
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

}
