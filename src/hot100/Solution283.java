package hot100;

/**
 * 283. 移动零
 * <p>
 * 题意：0放后面，还要保持非0元素的相对顺序！！【注意：要保持非0元素的相对顺序，所以左右指针初始化不能在数组两端】
 * <p>
 * 思路：左右指针。
 * 左右指针都从左边开始，左指针从0开始，右指针从1开始。
 * 然后左指针如果遇到0，先判断右指针是否也是0，如果不是二者交换；如果是先让右指针右移，直到遇到不是0的二者交换；
 * 交换完，两者都前进
 * 结束边界处理：右边届到最右边
 * <p>
 * 思路2 ✅：使用双指针，左指针指向当前已经处理好的序列的尾部，右指针指向待处理序列的头部。
 * 右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换，同时左指针右移。
 * 注意到以下性质：
 * 1。左指针左边均为非零数；
 * 2。右指针左边直到左指针处均为零。
 * <p>
 * 初始化：两指针都在最左边
 *
 * 【反思】：思路1和思路2的差别其实在于先动左指针还是先动右指针，但是对应的所耗费时间以及代码复杂度有较大差别。
 * 既然要始终保证右指针在右边，不妨先动右指针？尤其是在二者起点一样的时候
 */
public class Solution283 {

    public static void moveZeroes(int[] nums) {

        if (nums.length < 2) {
            return;
        }

        int left = 0;
        int right = 1;
        while (right < nums.length) {
            //1.更新左指针，当左指针遇到第一个为0的数停下
            if (left < nums.length && nums[left] != 0) {
                left++;
                continue;
            }
            if (left >= nums.length - 1) {
                return;
            }
            //2.更新右指针，当右指针>左指针且遇到第一个不为0的数停下
            right = left + 1;
            while (right < nums.length - 1 && nums[right] == 0) {
                right++;
            }
            //3.左指针为0，右指针不为0，交换
            nums[left] = nums[left] ^ nums[right];
            nums[right] = nums[left] ^ nums[right];
            nums[left] = nums[left] ^ nums[right];
            //4.换完之后二者都前进
            left++;
            right++;

        }
    }

    public static void moveZeroes2(int[] nums) {

        int left, right;
        left = right = 0;
        while (right < nums.length) {
            //右指针指的数不为0，要交换然后左指针右移
            if (nums[right] != 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            //右指针右移
            right++;
        }

    }


    public static void main(String[] args) {
        int[] nums = new int[]{ 1, 0};
        moveZeroes2(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
