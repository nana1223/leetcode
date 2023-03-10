package jianzhi_offer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 39. 数组中出现次数超过一半的数字
 * <p>
 * 思路；维护一个hashmap记录每个数字出现的次数，同时遍历的时候维护最大次数。 O(n)
 */
public class Solution39 {

    public int majorityElement1(int[] nums) {
        int maxCount = 0;
        int res = 0;
        Map<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(nums[i])) {
                hashMap.put(nums[i], hashMap.get(nums[i]) + 1);
            } else {
                hashMap.put(nums[i], 1);
            }
            if (hashMap.get(nums[i]) > maxCount) {
                maxCount = hashMap.get(nums[i]);
                res = nums[i];
            }
        }

        return res;
    }

    /**
     * 思路二：排序
     * 如果把数组排好序，那么下标为 n/2 的位置就一定是出现次数超过一半的数字
     * 这个时候的时间复杂度就是排序的时间复杂度（用快排可以达到 O(nlogn)
     */
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 思路三：摩尔投票法
     * 假设有一个擂台，有一组人，每个人有编号，相同编号为一组，依次上场，
     * 没人时上去的便是擂主（x），若有人，编号相同则继续站着（人数+1），若不同，假设每个人战斗力相同，都同归于尽，则人数-1；
     * 那么到最后站着的肯定是人数占绝对优势的那一组啦~
     * 他的时间复杂度是O(n)，但是为什么他是最快的？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？
     */
    public int majorityElement(int[] nums) {
        int votes = 0;
        int x = -1;
        // 每个人轮流上去
        for (int num : nums) {
            // 如果没有擂主，那上来的就是擂主
            if (votes == 0) {
                x = num;
            }
            //如果上来的是自己人，就投票；若是别人，就减票
            if (num == x) {
                votes++;
            } else {
                votes--;
            }
        }
        return x;
    }

}
