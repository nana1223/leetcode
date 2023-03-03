package jianzhi_offer;

/**
 * 剑指 Offer 12. 矩阵中的路径
 * <p>
 * 理解：要在矩阵中找到一条能走过word的路
 * 思路：先找word的第一个字母，然后开始【dfs遍历】如果不匹配或者走过就回退，称为【剪枝】
 * DFS解析：
 * 1.递归参数：当前元素在矩阵 board 中的行列索引 i 和 j ，当前目标字符在 word 中的索引 index
 * 2.终止条件：1.返回true的：word找到； 2.返回false的：行列索引越界，或者当前没有匹配字符，或者开始走走过的路
 * 3.递推工作：
 * 1）搜索下一单元格： 朝当前元素的 上、下、左、右 四个方向开启下层递归，用 或|| 来连接 （代表只需找到一条可行路径就直接返回，不再做后续 DFS ），并记录结果至 res
 * 2）把每次走过的路标记一下，防止重复访问。直接把board[i][j] 修改为 空字符 ''
 * 3）本次dfs之后，再把board恢复原样
 */
public class Solution12 {
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 1.在board中找word的第一个字符
                if (findWord(board, words, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 递归参数：当前元素在矩阵 board 中的行列索引 i 和 j ，当前目标字符在 word 中的索引 index
     */
    private boolean findWord(char[][] board, char[] words, int i, int j, int index) {
        int m = board.length;
        int n = board[0].length;

        //递归结束条件：word找到，或者越界，或者当前没有匹配字符，或者开始走走过的路
        // 【注意】！！！要把false的判断放在true判断的前面来判断
        if (i >= m || i < 0 || j >= n || j < 0 || board[i][j] != words[index]) {
            return false;
        }
        if (index == words.length - 1) {
            return true;
        }

        // 访问过的要标记为空字符串【“ ”是空格 '\0'是空字符串，不一样的！】
        board[i][j] = '\0';
        // 上下左右寻找下一个字符，但是要注意是否越界
        boolean res = findWord(board, words, i - 1, j, index + 1) || findWord(board, words, i + 1, j, index + 1)
                || findWord(board, words, i, j - 1, index + 1) || findWord(board, words, i, j + 1, index + 1);

        // 还原找过的元素，因为之后可能还会访问到（不同路径）
        board[i][j] = words[index];
        return res;
    }

}
