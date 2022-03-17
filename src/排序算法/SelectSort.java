package 排序算法;

public class SelectSort {

    /**
     * 简单选择排序
     * 思路：
     * 1.数组长度n，第一趟选择排序，n个数中找到最小值，然后与第一个元素交换;
     * 2.第二趟选择排序,范围是从第2个元素到结尾，n-1个元素的范围中寻找最小值和第二个元素交换，依次类推,总共需要n-1趟即可完成排序
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {

        int temp;//交换的介质定义在循环外，节省空间

        //n个元素，只需要n-1趟即可有序，最后使得无序区只剩一个元素
        for (int i = 0; i < arr.length - 1; i++) {

            //min记录每趟的最小值的下标
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            //min每轮徐循环的初始值就是i
            if (min != i) {
                temp = arr[min];
                arr[min] = arr[i];
                arr[i] = temp;
            }
        }
    }
}
