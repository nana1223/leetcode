package jianzhi2_zhuanxiang;

import java.util.*;

/**
 * 剑指 Offer II 007. 数组中和为 0 的三个数
 * 【思路】：
 * 1.暴力：三元for遍历
 * 2.先排序 然后双指针
 * 【优化】2是在1的基础上进行优化，把三层for的后两层用双指针来解决
 *
 * 【反思】：很多时候优化的思路，都是针对暴力法进行改进的。（当然，彻底换思路除外）
 */
public class Solution7 {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        //1.排序
        Arrays.sort(nums);

        //2.最外层for，双指针遍历后两层
        for (int i = 0; i < nums.length - 2; i++) {
            int target = -nums[i];

            //2.1 确保第一个数字不重复使用
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            //2.2 双指针遍历后两层
            int left = i + 1;
            int right = nums.length - 1;

            Set<Integer> set = new HashSet<>();
            while (left < right) {
                if (target > nums[left] + nums[right]) {
                    left++;
                } else if (target < nums[left] + nums[right]) {
                    right--;
                } else {
                    //2.3 确保第二个元素不被重复使用
                    if (set.add(nums[left])) {
                        List<Integer> resNumber = new ArrayList<>();
                        resNumber.add(nums[i]);
                        resNumber.add(nums[left]);
                        resNumber.add(nums[right]);
                        res.add(resNumber);
                    }
                    //2.4 后面仍可能有结果，要继续遍历
                    right--;
                }
            }
        }

        return res;
    }
}
