package jianzhi_offer;

/**
 * 面试题67. 把字符串转换成整数
 * <p>
 * 【思路】：去掉首尾空格，转换成char数组，挨个遍历，可能遇到的有：数字0-9，+-，其他
 * 【数字拼接公式】：从左往右遍历时，当前位字符为c，当前位数字为x，数字最终结果为res。
 * 有，res = 10 * res + x，（x = ascii(c) - ascii('0') )
 */
public class Solution67 {

    public int strToInt(String str) {

        int res = 0;
        //判断越界的边界值
        int boundary = Integer.MAX_VALUE / 10;

        //遍历开始的索引位置
        int beginIdx = 0;
        //标记是正号还是负号
        int isPos = 1;

        //1.去掉首尾空格
        char[] array = str.trim().toCharArray();
        if (array.length == 0) {
            return 0;
        }

        //2.判断第一个字符是否是+-
        if (array[0] == '+' || array[0] == '-') {
            beginIdx = 1;
            //标记是正是还是负数
            isPos = array[0] == '+' ? 1 : -1;
        }

        //3.开始遍历：如果首个字符是+-，从位置1开始遍历；否则从位置0开始
        for (int i = beginIdx; i < array.length; i++) {

            //3.1遇到非数字，直接退出
            if (array[i] > '9' || array[i] < '0') {
                break;
            }

            //3.2判断是否越界
            //在数字拼接前，判断此轮拼接后res是否超过2147483647， 若超过，则加上符号位直接返回
            //则数字拼接边界为 boundary = 2147483647 / 10
            //有两种越界情况
            //1）直接 res > boundary，执行拼接后 10 * res >= 2147483650 > 2147483647 越界
            //2）res = boundary, 但是当前数字 x > 7，（x是8或者9） res = 10*res+7之后超过2147483647

            if (res > boundary || (res == boundary && array[i] > '7')) {
                return isPos == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            //3.3拼接数字
            //【注意】：用当前字符的ascii码减去字符0的ascii码，才是当前的数字值本身
            res = res * 10 + (array[i] - '0');

        }

        //结尾要乘上符号位
        return res * isPos;
    }
}
