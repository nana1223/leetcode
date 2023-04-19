package hot100;

import java.util.*;

/**
 * 49. 字母异位词分组
 * <p>
 * 思路：遍历的过程中，用哈希表存下 < key为这组字符串是字母异位的标志-对应的字符串 > hashMap(set(123), list(string))。
 * 然后把哈希表的所有value输出
 * <p>
 * 完成阻挠：
 * 1。String是否含有同样的字母如何判断？
 * 参考思路：
 * 1）排序：两个字符串排序之后是相同的
 * 2）计数：记录两个字符串中每个字母出现的次数。次数相同的是同组字符串（试试这种）
 * 2。用什么标志来记录同组字符串？———— 组成一个唯一的key
 */
public class Solution49 {

    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> hashTable = new HashMap<>();
        for (String str : strs) {
            //1.记录当前字符串每个字母出现的次数
            int[] counts = new int[26];
            for (int i = 0; i < str.length(); i++) {
                counts[str.charAt(i) - 'a']++;
            }
            //2.按字母顺序把每个字母和出现的次数拼接 组成map的key作为同组字符串的标志
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] != 0) {
                    sb.append((char) i + 'a').append(counts[i]);
                }

            }
            //3.存入map
            String key = sb.toString();
            List<String> stringList = hashTable.getOrDefault(key, new ArrayList<>());
            stringList.add(str);
            hashTable.put(key, stringList);

        }

        return new ArrayList<>(hashTable.values());
    }


}
