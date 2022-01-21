import java.util.Scanner;

public class NumArray303 {

    private int[] preSum;

    public NumArray303(int[] nums){
        preSum = new int[nums.length + 1];
        for( int i = 1; i< preSum.length; i++){
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int left, int right){
        return preSum[right + 1] - preSum[left];
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("hello world");
//        NumArray obj = new NumArray(nums);
//        int param_1 = obj.sumRange(left,right);
    }
}
