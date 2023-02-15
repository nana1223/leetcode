package jianzhi_offer;

/**
 * 剑指 Offer 11. 旋转数组的最小数字
 * <p>
 * 思路：找最小值。在旋转过的数组中
 * <p>
 * 首先想到暴力，可能会超时。
 * 其次遍历数组的常见算法：二分不能用，因为不是顺序的数组
 * 然后看看题干有哪些信息能用，旋转过。那么怎么能知道旋转过几次。
 * 如果可以知道旋转过几次的话，就可以旋转回去再用二分法；
 * 但如果无法知道旋转几次的话，那本题给的条件就是废的
 * so？怎么才能知道旋转几次呢？？
 * <p>
 * 【反思】：数组遍历 不是暴力 就是二分
 * 那么就以二分出发考虑这道题：怎么在旋转数组中用二分查找？————【分不同情况用二分法】
 * 旋转后的数组 = 左排序数组 + 右排序数组 eg：3456123（左排序3456 右排序123）
 * 【最终要找的是右排序数组的第一个数（就是最小数）】
 * 每次的中点值m，
 * 若m大于右边界的值，则m的位置一定在左排序中，执行 i = m+1；
 * 若m小于右边界值，则m的位置一定在右排序中, j=m。
 * 若m等于右边界的值，则要么[i,m]内所有数相等，要么[m,j]内所有数相等（或两者都满足），此时无法判断m在左还是右。处理方法是：j=j-1
 * (证明：https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/solution/mian-shi-ti-11-xuan-zhuan-shu-zu-de-zui-xiao-shu-3/)
 * 退出循环时的i==j就是最小值
 * */
public class Solution11 {

    public int minArray(int[] numbers) {

        int n = numbers.length;

        int start = 0;
        int end = n - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (numbers[mid] > numbers[end]) {
                start = mid + 1;
            } else if (numbers[mid] < numbers[end]) {
                end = mid;
            } else if (numbers[mid] == numbers[end]) {
                end = end - 1;
            }
        }
        return numbers[start];
    }
}
