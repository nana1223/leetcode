package 差分数组;

public class Solution370 {

    //public int[] getModifiedArray(int length, int[][] updates) {
//        int[] array = new int[length];
//        for (int i = 0; i < updates.length; i++) {
//            for (int j = updates[i][0]; j <= updates[i][1]; j++) {
//                array[j] += updates[i][2];
//            }
//        }
//        return array;
//    }

    /**
     * 法一：上面的暴力解法 729ms 45.1mb
     * 法二：差分数组法 3ms 47.1mb
     */
    int[] initArray;
    int[] diffArray;

    public int[] getModifiedArray(int length, int[][] updates) {
        initArray = new int[length];
        diffArray = getDiffArray(initArray);
        for (int i = 0; i < updates.length; i++) {
            diffArray[updates[i][0]] += updates[i][2];
            //注意这里的if
            if (updates[i][1] + 1 < length) {
                diffArray[updates[i][1] + 1] -= updates[i][2];
            }
        }
        int[] resultArray = getResultArray(diffArray);
        return resultArray;
    }

    //构造差分数组
    public int[] getDiffArray(int[] nums) {
        diffArray = new int[nums.length];
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
