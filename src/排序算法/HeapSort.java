package 排序算法;

/**
 * @author zhangna
 */
public class HeapSort {
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
}
