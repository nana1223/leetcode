package 排序算法;

public class BubbleSort {
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
}
