package jianzhi2_zhuanxiang;

/**
 * 剑指 Offer II 002. 二进制加法
 * <p>
 * 【思路】：从低位开始相加，每次都求出当前位和进位，注意位数较多的那个数的最高位可能仍有进位的情况。
 */
public class Solution2 {

    public String addBinary(String a, String b) {

        StringBuilder res = new StringBuilder();
        int m = a.length() - 1;
        int n = b.length() - 1;

        //进位
        int carry = 0;

        //从最低位开始，算当前位和进位。只要位数较多的那个还没算完就继续算
        while (m >= 0 || n >= 0) {

            //1.获取两个数对应位置的01值
            int ai = m >= 0 ? a.charAt(m) - '0' : 0;
            int bi = n >= 0 ? b.charAt(n) - '0' : 0;

            //2.计算当前位和进位，把当前位加入结果集
            int cur = (ai + bi + carry) % 2;
            carry = (ai + bi + carry) / 2;
            res.append(cur);

            //3.两个数都移位
            m--;
            n--;
        }
        //4.【注意处理结尾】：若最高位仍有进位时，要加上
        if (carry == 1) {
            res.append(carry);
        }

        //结果翻转
        return res.reverse().toString();
    }
}
