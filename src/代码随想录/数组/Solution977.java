package 代码随想录.数组;

/**
 * 算时间复杂度补充：
 * 如果算法中包含嵌套的循环，则基本语句通常是最内层的循环体;
 * 如果算法中包含并列的循环，则将并列循环的时间复杂度相加。例如：
 * <p>
 * 　　for (i=1; i<=n; i++)
 * 　　       x++;
 * 　　for (i=1; i<=n; i++)
 * 　     　for (j=1; j<=n; j++)
 * 　　          x++;
 * 　　第一个for循环的时间复杂度为Ο(n)，第二个for循环的时间复杂度为Ο(n2)，则整个算法的时间复杂度为Ο(n+n2)=Ο(n2)。
 * <p>
 * <p>
 *
 * 有序数组的平方
 * 法一：顺序弄两遍循环，第一遍求平方，第二遍排序（但是排序过程能达到O(n)吗）
 * 法二：双指针法，找绝对值最小的地方开始，左右指针往两边走，然后让新的数组从最左边往右赋值
 * 法三：双指针法，指针从左右两端开始，因为最大值在数组的两端，就是说新的数组从最右边往左赋值
 *
 * 反思：核心是要找到新数组对于旧数组是如何排序的。法二是从中间开始往两边，找从小到大排序，法三是从两边往中间，从大到小。
 * （数组挺能用双指针，就是指针的起始位置需根据题灵活使用）
 */
public class Solution977 {

    public int[] sortedSquares2(int[] nums) {
        int absoluteMinValue = Integer.MAX_VALUE;
        int absoluteMinLoc = 0;
        for (int i = 0; i < nums.length; i++) {
            if (Math.abs(nums[i]) < absoluteMinValue) {
                absoluteMinValue = Math.abs(nums[i]);
                absoluteMinLoc = i;
            }
        }

        int[] resNum = new int[nums.length];

        resNum[0] = (int) Math.pow(nums[absoluteMinLoc], 2);
        int resLoc = 1;
        int left = absoluteMinLoc - 1, right = absoluteMinLoc + 1;
        while (left >= 0 && right < nums.length) {
            if (Math.abs(nums[left]) < Math.abs(nums[right])) {
                resNum[resLoc++] = (int) Math.pow(nums[left--], 2);
            } else {
                resNum[resLoc++] = (int) Math.pow(nums[right++], 2);
            }
        }
        while (left >= 0) {
            resNum[resLoc++] = (int) Math.pow(nums[left--], 2);
        }
        while (right < nums.length) {
            resNum[resLoc++] = (int) Math.pow(nums[right++], 2);
        }
        return resNum;
    }
}
