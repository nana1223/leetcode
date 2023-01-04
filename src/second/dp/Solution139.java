package second.dp;

import java.util.List;

/**
 * 139. 单词拆分
 * 【发现最难的是定义dp】
 * 1.定义状态和选择，状态就是字符串s的字符索引位置，选择就是当前走到当前索引位置是否可以被字符串列表中的拆分表示
 * 2.定义dp：dp[i] 表示前i个字符s[0 i-1]能否被列表拼接表示
 * 3.base case：dp[0]表示空串，可以被表示 dp[0]=true
 * 所求即 dp[s.length()]
 * 4.状态转移：迭代，从0开始直到所求
 *
 */
public class Solution139 {

//    public boolean wordBreak(String s, List<String> wordDict) {
//
//    }
}
