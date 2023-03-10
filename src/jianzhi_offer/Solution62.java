package jianzhi_offer;

import java.util.ArrayList;

/**
 * 剑指 Offer 62. 圆圈中最后剩下的数字
 * <p>
 * 思路：模拟这个运算过程，用LinkedList会超时，用ArrayList勉强不会
 * （因为ArrayList的remove()操作在后续移位的时候其实是内存连续空间的拷贝，而LinkedList要有大量非连续性的地址访问）
 */
public class Solution62 {

    public int lastRemaining1(int n, int m) {
        ArrayList<Integer> list = new ArrayList<>(n);

        //1.构造链表
        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        //2.模拟过程
        //假设当前删除位置是idx，下一个删除位置是idx+m-1（因为当前数字删掉后面的数字会往前移一位），又因为数到末尾会从头数，所以再取模一下，即(idx+m-1)(mod n)
        int idx = 0;
        while (n > 1) {
            //2.1找到删除位置
            idx = (idx + m - 1) % n;
            //2.2从链表中删除
            list.remove(idx);
            //2.3链表总数-1
            n--;
        }
        return list.get(0);
    }

    /**
     * 思路二：数学，反推过程
     * 【目前还不懂】！！！ https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/solution/javajie-jue-yue-se-fu-huan-wen-ti-gao-su-ni-wei-sh/
     *
     */
    public int lastRemaining(int n, int m) {
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = (res + m) % i;
        }
        return res;
    }

}
