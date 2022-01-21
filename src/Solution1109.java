public class Solution1109 {

    /**
     * 法一：暴力解法 1474ms 53.5mb
     *
     */
//    public int[] corpFlightBookings(int[][] bookings, int n) {
//        int[] answer = new int[n];
//        for (int i = 0; i < bookings.length; i++) {
//            for (int j = bookings[i][0] - 1; j < bookings[i][1]; j++) {
//                answer[j] += bookings[i][2];
//            }
//        }
//        return answer;
//    }

    /**
     * 法二：差分数组  4ms 53.3mb
     */
    int[] diffArray;

    public int[] corpFlightBookings(int[][] bookings, int n) {
        diffArray = new int[n];
        for (int i = 0; i < bookings.length; i++) {
            diffArray[bookings[i][0] - 1] += bookings[i][2];
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!注意边界 if条件
            if (bookings[i][1] < n) {
                diffArray[bookings[i][1]] -= bookings[i][2];
            }

        }
        return getResultArray(diffArray);
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
