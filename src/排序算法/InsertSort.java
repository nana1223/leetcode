package 排序算法;

public class InsertSort {

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


}
