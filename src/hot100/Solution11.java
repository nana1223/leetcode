package hot100;

/**
 * 11. 盛最多水的容器
 * <p>
 * 思路：最多水 h*w 要最大，h也要大，w也要大。
 * w大：双指针从两边往里面走，然后两头的h取决于h小的那个
 *
 * 【反思】：双指针一定要搞清楚什么时候哪个指针动
 */
public class Solution11 {
    public int maxArea(int[] height) {
        int res = 0;

        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            //1.计算当前状态的盛水量
            int h = Math.min(height[left], height[right]);
            int temp = (right - left) * h;
            res = Math.max(res, temp);
            //2.h更小的那一侧往里走
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return res;
    }
}
