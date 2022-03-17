package 排序算法;

import java.util.ArrayList;
import java.util.Collections;

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

    /**
     * 堆排序
     *
     * @param arr
     */
    public static void heapSort(int[] arr) {

        //1.根据原始数组创建大根堆
        buildHeap(arr);

        //2.创建出来大根堆后，需要将堆顶和堆底的元素进行交换，使最大元素位于堆底（即将最大数换到了数组末端）。
        //此时除堆底外，不满足大根堆的元素正是堆顶的元素，所以再对堆顶的元素进行堆调整。
        // 然后不断重复（堆顶和堆底交换，调整新堆顶）两个动作，利用堆不断将最大数换到数组末端进行排序
        for (int i = arr.length - 1; i > 0; i--) {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, i, 0);
        }
    }

    //创建初试的大根堆
    private static void buildHeap(int[] arr) {
        //最后一个节点
        int lastNode = arr.length - 1;
        //最后一个非叶子节点，对节点进行堆调整操作就从最后一个非叶子节点开始，依次往前处理每一个节点
        int parent = (lastNode - 1) / 2;
        for (int i = parent; i >= 0; i--) {
            adjustHeap(arr, arr.length, i);
        }
    }

    //对堆中的节点i进行堆调整操作,使节点i及其两个孩子之间的树符合堆的条件（构建大根堆），即节点i>两个孩子
    private static void adjustHeap(int[] tree, int n, int i) {
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;
        int max = i;
        //注意判断左孩子右孩子是否越界
        if (leftChild < n && tree[leftChild] > tree[max]) {
            max = leftChild;
        }
        if (rightChild < n && tree[rightChild] > tree[max]) {
            max = rightChild;
        }
        //max != i说明此时的父节点（节点i）不是最大值，需要将节点i与其较大的孩子交换位置
        if (max != i) {
            int temp = tree[max];
            tree[max] = tree[i];
            tree[i] = temp;

            //假设左子树与父节点进行了交换，则以左子树为根节点的二叉树可能也会不满足二叉堆，因此还必须对其进行调整
            adjustHeap(tree, n, max);
        }
    }

    /**
     * 归并排序
     * 思路：分治法思想（一分二，二分四，四合二，二合一）
     *
     * @param arr
     */
    public static void mergeSort(int[] arr, int left, int right) {
        //先分治再归并。递归结束条件即分治到只有一个值的时候，开始归并
        if (left == right) {
            return;
        } else {
            int middle = (left + right) / 2;
            // 对中间值左边进行排序----不断拆分
            mergeSort(arr, left, middle);
            // 对中间值右边进行排序----不断拆分
            mergeSort(arr, middle + 1, right);
            // 将左右两个排好序的数组进行合并
            merge(arr, left, middle, right);
        }
    }

    /**
     * 一次归并过程，左右两个排好序的数组进行合并，这两个数组在原arr中下标分别为arr[left,middle]和arr[middle+1,right]
     */
    private static void merge(int[] arr, int left, int middle, int right) {

        //新建一个临时数组，用来存放排好序的数
        int[] temp = new int[arr.length];

        //i指向左边数组的第一个下标，j指向右边数组的第一个下标, k指向临时数组的最左边下标
        int i = left, j = middle + 1, k = left;
        while (i <= middle && j <= right) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        //能走到这里有两种情况,或者i>middle了,或者j>right了,也就是左边或右边至少有一个数组已经全部放到新数组temp里了，再把剩余的直接放进去即可
        while (i <= middle) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        //再将排好序的temp数组赋给arr
        while (left <= right) {
            arr[left] = temp[left++];
        }
    }

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

    public static void main(String[] args) {
        int[] arr = {49, 38, 65, 97, 23, 22, 76, 1, 5, 8, 2, 12, 22};
        //insertSort(arr);
        //shellSort(arr);
        //bubbleSort(arr);
        //quickSort(arr, 0, arr.length - 1);
        //selectSort(arr);
        //heapSort(arr);
        //mergeSort(arr, 0, arr.length - 1);
        //bucketSort(arr);
        radixSort(arr);
        System.out.println("排序后:");
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

}
