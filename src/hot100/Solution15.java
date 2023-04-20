package hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 15. 三数之和
 * <p>
 * 思考过程：最最复杂的就是三层for。O(n3)
 * 然后针对里面两层，可以用双指针优化，先排序。O(n2)
 * <p>
 * 以上这种实现会重复。✅去重思路：要么就开始加入的时候就去重；要么就最后对结果去重。
 * 1）结果去重法：非常耗时，试过了（技术去重）
 * 2）开头去重法✅，在三个数分别往下一个走的时候都要判断是否相等（业务去重）
 * <p>
 * 优化思路2：遍历过程中对于 nums[i]>0的就可以直接返回了
 */
public class Solution15 {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        //1.排序数组
        Arrays.sort(nums);

        //2.一层for + 双指针
        for (int i = 0; i < nums.length; i++) {
            //优化：nums[i] > 0
            if (nums[i] > 0) {
                return res;
            }
            //去重操作
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] < target) {
                    left++;
                } else if (nums[left] + nums[right] > target) {
                    right--;
                } else {
                    res.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
                    //首先，无论如何都要进行加减操作
                    left++;
                    right--;
                    //去重操作
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                }
            }
        }
        return res;
    }
}
