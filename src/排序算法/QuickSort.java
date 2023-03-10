package 排序算法;

public class QuickSort {

    /**
     * 快速排序
     * 核心思想：---------怎么处理能让基准数左边都比他小，右边都比他大 （由这个要注意不是左右指针比大小交换，而是左右指针要和基准数比大小然后左右指针交换）
     * 思路：
     * 1.先把数组中的一个数当作基准数（一般会把数组中最左边的数当作基准数）
     * 2.然后利用左右指针从两边进行检索。先从右边检索比基准数小的数，再从左边检索比基准数大的数。若左右指针都检索到了，就停下，然后交换左右指针所指的数。然后再继续检索
     * 3.当左右指针相遇时，就停止检索。把基准数位置的数据和指针相遇位置的数据交换。--------此时，一趟结束后，基准数左边都比他小，右边都比他大
     * 4.然后，对基准数的左右序列，再递归分别进行快排
     * 5.对于每一部分的排序过程结束条件是左指针>右指针
     */
    public static void quickSort(int[] arr, int left, int right) {

        //左指针>右指针时不合法，递归结束
        if (left > right) {
            return;
        }

        //base代表基准数，选取第一个记录为轴值
        int base = arr[left];
        int i = left, j = right;
        int temp;//交换的介质定义在循环外，节省空间

        while (i < j) {
            //先看右指针，与base比较，依次往左递减，当arr[j]<base时跳出循环

            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!  注意！！！ 快排必须右指针的操作写在前面 （若左指针在前面无法实现快排）
            //!!!!!!!!!!!!!!【注意是 >= 】
            while (i < j && arr[j] >= base) {
                j--;
            }
            while (i < j && arr[i] <= base) {
                i++;
            }
            //当前状态下，arr[j]<base，arr[i]>base，然后交换arr[j]与arr[i]的值
            temp = arr[j];
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

}
