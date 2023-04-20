package hot100;

/**
 * 42. 接雨水
 * <p>
 * 思路一：按行求。即先求高度为 1 的水，再求高度为 2 的水，再求高度为 3 的水。
 * <p>
 * 思路二：按列求。求每一列的水，我们只需要关注当前列，以及左边最高的墙，右边最高的墙就够了。    O(n2)
 * 找出左右最高的两堵墙中较矮的一个墙x，比较x和当前列的高度。若x>当前列，当前列存水=x高度-当前列高度；若x<=当前列，当前列不存水
 * <p>
 * 思路三：动态规划✅。针对思路二优化，在找左右最高墙的时候不要遍历找，而是存两个dp数组 leftMax[i], rightMax[i], 每次更新。   O(n)
 * leftMax[i]表示第i列左边的最大值。每次求leftMax[i] = Math.max(leftMax[i-1], height[i-1])
 */
public class Solution42 {

    public int trap2(int[] height) {
        int res = 0;
        //1. 遍历每一列（最两端的列不用考虑，因为一定不会有存水）
        for (int i = 1; i < height.length - 1; i++) {
            //2. 找出当前列的左右最高列，以及二者中较矮的一个
            int leftMax = 0;
            for (int j = i - 1; j >= 0; j--) {
                leftMax = Math.max(leftMax, height[j]);
            }
            int rightMax = 0;
            for (int j = i + 1; j < height.length; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }
            int low = Math.min(leftMax, rightMax);

            //3. 比较较矮的一个和当前列的高度，计算存水量
            if (low > height[i]) {
                res += low - height[i];
            }
        }
        return res;

    }

    public int trap(int[] height) {
        int res = 0;
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];

        //1. 计算每一列的左右最高列
        for (int i = 1; i < height.length - 1; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i - 1]);
        }
        for (int i = height.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i + 1]);
        }

        //2. 遍历每一列（最两端的列不用考虑，因为一定不会有存水）
        for (int i = 1; i < height.length - 1; i++) {
            //3. 找出当前列的左右最高列，以及二者中较矮的一个
            int low = Math.min(leftMax[i], rightMax[i]);

            //4. 比较较矮的一个和当前列的高度，计算存水量
            if (low > height[i]) {
                res += low - height[i];
            }
        }
        return res;

    }
}
