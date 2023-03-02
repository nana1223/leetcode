package jianzhi_offer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 * <p>
 * 思路：使用Java中的Deque，双端队列，遍历遇到技术从前面插入，偶数从后面插入
 * <p>
 * 思路二：不用额外的空间。用【双指针】，一个从前往后，一个从后往前
 *
 * 【反思】：双指针最重要的是要能够知道指针在什么时候移动！
 */
public class Solution21 {
    public int[] exchange1(int[] nums) {
        Deque<Integer> deque = new ArrayDeque<>();

        for (int num : nums) {
            if (num % 2 == 0) {
                deque.addLast(num);
            } else {
                deque.addFirst(num);
            }
        }
        int[] res = new int[deque.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = deque.pollFirst();
        }
        return res;
    }

    public int[] exchange(int[] nums) {
        if (nums.length == 0) {
            return new int[]{};
        }
        int n = nums.length;
        int p = 0;
        int q = n - 1;
        while (p != q) {
            // 先让p往后走，如果p遇到偶数，就看q是否是奇数，如果q不是奇数，就让q往前走，如果q也是奇数，就让两指针的数据互换
            if (nums[p] % 2 != 0) {
                p++;
            } else {
                if (nums[q] % 2 != 0) {
                    //p偶数 q奇数 互换
                    int temp = nums[p];
                    nums[p] = nums[q];
                    nums[q] = temp;
                } else {
                    q--;
                }
            }
        }
        return nums;
    }
}
