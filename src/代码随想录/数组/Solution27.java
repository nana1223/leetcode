package 代码随想录.数组;

/**
 * 移除元素
 * 法一：双指针，分别从左右开走
 * 如果左指针指向的元素等于val，此时将右指针right 指向的元素复制到左指针的位置，然后右指针right 左移一位。
 * 如果赋值过来的元素恰好也等于val，可以继续把右指针指向的元素的值赋值过来（左指针指向的等于val 的元素的位置继续被覆盖），直到左指针指向的元素的值不等于val 为止。
 * 当左指针和右指针重合的时候，左右指针遍历完数组中所有的元素。
 * <p>
 * <p>
 * <p>
 * 法二：
 * 思路：左右指针，但是注意不是从两端开走，是都从左边开走！！！！！！！！！！！！！！！！
 * 如果右指针指向的元素不等于 val，它一定是输出数组的一个元素，我们就将右指针指向的元素复制到左指针位置，然后将左右指针同时右移；
 * 如果右指针指向的元素等于 val，它不能在输出数组里，此时左指针不动，右指针右移一位。
 * <p>
 *
 * 总结：
 * 整个过程保持不变的性质是：区间 [0,left) 中的元素都不等于val !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 当左右指针遍历完输入数组以后，left 的值就是输出数组的长度。
 *
 * 反思一下：写算法题要守住核心要素，就像本题是要始终保证[0,left) 中的元素都不等于val。
 * 好像是写数学证明题，始终都要守住当前这步是要干嘛
 */
public class Solution27 {

    public int removeElement(int[] nums, int val) {

        //注意这里right的处理，若直接赋值right = nums.length-1的话，while得写成while(left <= right)，否则会丢值；
        int left = 0, right = nums.length;
        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[right - 1];
                right--;
            } else {
                left++;
            }
        }
        return left;
    }

    public int removeElement2(int[] nums, int val) {
        int left = 0, right;
        for (right = 0; right < nums.length; right++) {
            if (nums[right] != val) {
                nums[left] = nums[right];
                left++;
            }
        }
        return left;
    }

    public static void main(String[] args) {


        Solution27 solution27 = new Solution27();
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int len = solution27.removeElement(nums, 2);

        for (int i = 0; i < len; i++) {
            System.out.println(nums[i]);
        }
    }
}
