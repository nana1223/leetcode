package jianzhi2_zhuanxiang;

/**
 * 剑指 Offer II 001. 整数除法
 * <p>
 * 题意理解：要求的是除法的商（即除法的整数部分）
 * 思路：不让用乘除号。那就是用【加减符号】来解决除法
 * 例：15/2：用加减法算：15减2再减2一直减到剩下1，共减了7次2。商为7。
 * <p>
 * 问题：若被除数无敌大，除数很小的时候，就容易算很多很多次。
 * <p>
 * 【优化】：不要每次只减除数。
 * 上例子比较直观，15/2，15不仅大于2，而且大于2的2倍，2的4倍，但小于2的8倍也就是16，于是让15减去8，因为被减去的是8也就是那个2的四倍，所以这一部分的商就是4。
 * 接下来对剩余的7和除数2进行比较，7大于2，大于2的2倍，但小于2的4倍，因此减去4也就是2的2倍得3，这一部分的商则是2。
 * 再接下来对剩余的3和除数2比较，发现3大于2，小于2的2倍，因此减去2的一倍得1，这一部分的商是1。
 * 最后剩的1比除数2小，所以1是余数，再把每一部分的商合并就可以求出商是多少了。
 * <p>
 * 【细节】
 * 1.int型的范围是-2 ^31~-2 ^31-1，如果用-2 ^31除以-1就会导致溢出，也就是越界，所以这种情况我们算特殊情况。
 * int 型整数的除法只有一种情况会导致溢出，即 (2^31-1) / (-1) ，这是因为最大的正数为 2^31 - 1，2^31 超出了正数的范围
 * 2.无论什么类型，任意正数转换为负数都不会溢出，所以我们可以先将正数转化为负数，再用优化算法计算，最后根据需要调整正负号。
 * <p>
 * 【反思】：为了降低时间复杂度，就要想着怎么能减少计算的次数
 */
public class Solution1 {

    public int divide(int a, int b) {

        //1.特殊溢出情况
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }

        //记录最终结果的正负号：sign=1是负数
        int sign = 2;

        //2.把正数都转换成负数进行计算
        if (a > 0) {
            sign--;
            a = -a;
        }
        if (b > 0) {
            sign--;
            b = -b;
        }

        //3.循环减法求商
        int res = divideCore(a, b);
        return sign == 1 ? -res : res;
    }

    /**
     * 使用【倍数循环】计算【两个负数的商】
     */
    private int divideCore(int a, int b) {
        int result = 0;

        while (a <= b) {
            //1.定义除数
            int value = b;
            //2.定义倍数
            int quotient = 1;
            //3.判断加多少倍
            //如果 被除数 <= 除数^倍数，循环结束
            //为了防止int型dividend溢出，所以value值最多是-2的31次方的一半，所以得大于0xc0000000
            while (value >= 0xc0000000 && a <= value + value) {
                //3.1倍数加倍，除数加倍
                value += value;
                quotient += quotient;
            }
            //3.2商 加上 倍数值
            result += quotient;
            //3.3被除数减去除数。继续循环
            a -= value;
        }
        return result;
    }
}
