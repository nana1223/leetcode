package labuladong;

import java.util.LinkedList;
import java.util.List;

/**
 * 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 */
public class Solution46 {

    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {

        //1.路径
        LinkedList<Integer> track = new LinkedList<>();
        //2.选择（存在于路径中的元素为true）
        boolean[] isChoice = new boolean[nums.length];

        backtrack(nums, track, isChoice);
        return res;
    }

    private void backtrack(int[] nums, LinkedList<Integer> track, boolean[] isChoice) {
        //1.结束条件
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }
        //2.回溯框架
        for (int i = 0; i < nums.length; i++) {
            //2.1 排除已经在路径中的选择
            if (isChoice[i]) {
                continue;
            }
            //2.2做选择
            isChoice[i] = true;
            track.add(nums[i]);
            //2.3进入下一层决策树
            backtrack(nums, track, isChoice);
            //2.4撤销选择
            //removeLast() 是LinkedList里有的方法，List里没有这个方法，所以把track定义成LinkedList类
            //有个反思：为什么集合要定义成父类子类这种形式呢？？？或者是对象为什么要这种定义？？？多态性吗？？？
            track.removeLast();
            isChoice[i] = false;
        }
    }
}
