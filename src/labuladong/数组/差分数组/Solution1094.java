package labuladong.数组.差分数组;

public class Solution1094 {

    /**
     *
     * 方法：labuladong.数组.差分数组   2ms 38.1mb
     */
    public boolean carPooling(int[][] trips, int capacity) {

        int[] diffArray = new int[1001];
        for (int i = 0; i < trips.length; i++) {
            //乘客在车上的区间是[trip[1], trip[2]-1]
            diffArray[trips[i][1]] += trips[i][0];
            if (trips[i][2] < diffArray.length) {
                diffArray[trips[i][2]] -= trips[i][0];
            }
        }
        int[] res = getResultArray(diffArray);
        for (int i = 0; i < res.length; i++) {
            if (res[i] > capacity) {
                return false;
            }
        }
        return true;
    }

    //构造差分数组
    public int[] getDiffArray(int[] nums) {
        int[] diffArray = new int[nums.length];
        diffArray[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            diffArray[i] = nums[i] - nums[i - 1];
        }
        return diffArray;
    }

    //差分数组还原修改后的原始数组
    public int[] getResultArray(int[] array) {
        int[] res = new int[array.length];
        res[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            res[i] = array[i] + res[i - 1];
        }
        return res;
    }
}
