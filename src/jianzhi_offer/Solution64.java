package jianzhi_offer;

/**
 * 剑指 Offer 64. 求1+2+…+n
 * <p>
 * 递归求
 * <p>
 * 【反思】：本题不让用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。考虑能用什么？？？
 * 列举出来发现只有加减法、赋值、位运算符以及逻辑运算符。
 * 而递归会利用条件判断语句来决定递归的出口，但由于题目的限制我们不能使用条件判断语句，
 * 那么我们是否能使用别的办法来确定递归出口呢？答案就是————【逻辑运算符的短路性质】。
 * <p>
 * 【逻辑运算符的短路性质】：A && B : 若A为false，就不会执行B； A||B，若A为True，就不会执行B
 * 把递归结束条件看作A，递归主体看作B
 */
public class Solution64 {
    /**
     * 【注意】：本题不让用if！，那么怎么能搞定递归结束条件
     */
    public int sumNums(int n) {
        boolean flag = n > 1 && (n += sumNums(n - 1)) > 0;
        return n;
    }
}
