package jianzhi_offer;

/**
 * 剑指 Offer 20. 表示数值的字符串
 * <p>
 * 【思路一】：遍历s，然后按这个顺序可选或拼接（要一点点判断if）
 * 1.去掉首位空格，然后处理剩下部分
 * 2.然后判断每一个位置，是否是数字、小数点、e、+-号。是否出现在他们应该出现的位置
 * <p>
 * 【思路二：有限状态自动机】
 */
public class Solution20 {

    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        //标记是否遇到数位，小数点，e/E
        boolean isNum = false, isDot = false, isE = false;

        //1.把s去掉首位空格，转成字符数组
        char[] array = s.trim().toCharArray();

        for (int i = 0; i < array.length; i++) {

            //2.判断是否遇到数字0-9
            if (array[i] >= '0' && array[i] <= '9') {
                isNum = true;

            } else if (array[i] == '.') {
                //3.判断是否遇到小数点
                //小数点的违法规则是：不能重复出现小数点，不能在e/E后面出现小数点
                if (isDot || isE) {
                    return false;
                }
                isDot = true;

            } else if (array[i] == 'e' || array[i] == 'E') {
                //4.判断是否遇到e/E
                //e的违法规则：e前面必须有数，且e不能重复出现
                if (!isNum || isE) {
                    return false;
                }
                isE = true;
                //需要重置isNum，因为e后面必须接整数
                isNum = false;

            } else if (array[i] == '+' || array[i] == '-') {
                //5.判断是否遇到加减符号
                //加减符号应该出现在第一个或者e后面的第一个位置
                if (i != 0 && array[i - 1] != 'e' && array[i - 1] != 'E') {
                    return false;
                }

            } else {
                //其他情况均为不合法
                return false;
            }
        }
        //最后要return isNum 确保有数字
        return isNum;

    }
}
