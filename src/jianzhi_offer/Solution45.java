package jianzhi_offer;

/**
 * 面试题45. 把数组排成最小的数
 * <p>
 * 思路：小数字往高位数拼接。数字相同的数位少的在前
 * <p>
 * 思路：【桶排序】把数位相同的放在一个桶里，然后按桶输出。（暂时不知道这个思路能不能实现）
 * <p>
 * 思路：本题本质就是排序问题。但是相比传统的排序来说，在两个数字互换位置的判断逻辑变了。不是简单的比大小，【而是要把两个数拼接后的大小来调整位置】。
 * 比如：[3, 30, 34, 1, 9]
 * 遍历过程：
 * 3和30的位置要互换一下，因为 330 > 303 : [30, 3, 34, 1, 9]
 * 3和34的位置不用换，因为 334 < 343 : [30, 3, 34, 1, 9]
 * ……
 * 这样一轮遍历完，还得再来一轮。所以就是简单的冒泡！
 * 但是冒泡性能是排序里比较差的。所以套用任何的排序方法都可。用【快排】
 * <p>
 * 反思：本题的核心在于【排序判断规则】
 * 若拼接起来的 x+y > y+x，则 x 大于 y
 * 若拼接起来的 x+y < y+x，则 x 小于 y
 */
public class Solution45 {
    public String minNumber(int[] nums) {
        //1.先把数据都存成String方便处理
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }

        //2.快排
        quickSort(strs, 0, nums.length - 1);
        //3.处理结果，拼接起来
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s);
        }
        return sb.toString();
    }

    private void quickSort(String[] strs, int left, int right) {

        //1.递归结束条件：左指针大于右指针
        if (left > right) {
            return;
        }

        //2.排序操作
        String base = strs[left];
        int i = left;
        int j = right;

        while (i < j) {
            // 先看右指针
            while (i < j && (base + strs[j]).compareTo(strs[j] + base) <= 0) {
                j--;
            }
            strs[i] = strs[j];
            // 再看左指针
            while (i < j && (strs[i] + base).compareTo(base + strs[i]) <= 0) {
                i++;
            }
            strs[j] = strs[i];
        }
        // 左右指针相遇在i，重新设置基准数
        strs[i] = base;

        //3.递归
        quickSort(strs, left, i - 1);
        quickSort(strs, i + 1, right);

    }

}
