package labuladong.设计数据结构;

import java.util.LinkedList;
import java.util.List;

/**
 * 一.  理解Trie树前缀树
 * 1.Trie 树本质上就是一棵从二叉树衍生出来的多叉树
 * 2.Trie树节点实现：
 * class TrieNode<V> {
 * V val = null;
 * TrieNode<V>[] children = new TrieNode[256];
 * }
 * 这个val字段存储键对应的值，children数组存储指向子节点的指针。
 * 但是和之前的普通多叉树节点不同，TrieNode中children数组的索引是有意义的，代表键中的一个字符。
 * 比如说children[97]如果非空，说明这里存储了一个字符'a'，因为'a'的 ASCII 码为 97。
 * ！！！要特别注意，TrieNode节点本身只存储val字段，并没有一个字段来存储字符，字符是通过子节点在父节点的children数组中的索引确定的
 * <p>
 * trie树应用：自动补全和拼写检查
 * <p>
 * 还不是很懂？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？服了根本没看懂
 * 攻略： https://mp.weixin.qq.com/s/hGrTUmM1zusPZZ0nA9aaNw
 */


/**
 * 底层用Trie树实现键值映射，键为String类型，值为V类型
 *
 * @author zhangna
 */
public class TrieMap<V> {

    //ASCII码个数
    private static final int R = 256;

    //当前存在Map中的键值对个数
    private int size = 0;

    //节点类
    private static class TrieNode<V> {
        V val = null;
        TrieNode<V>[] children = new TrieNode[R];
    }

    //Trie树的根节点
    private TrieNode<V> root = null;

    /****************************一些工具函数********************************************/

    public int getSize() {
        return size;
    }

    //从节点node开始搜索对应的key，如果存在返回对应节点，否则返回null
    private TrieNode<V> getNode(TrieNode<V> node, String key) {
        TrieNode<V> p = node;
        //从节点node开始搜索key
        for (int i = 0; i < key.length(); i++) {
            if (p == null) {
                return null;
            }
            //向下搜索
            char c = key.charAt(i);
            p = p.children[c];
        }
        return p;
    }


    /******************** 增/改 *************************/

    // 在 Map 中添加 key
    public void put(String key, V val) {

    }

    /******************** 删 *************************/

    // 删除键 key 以及对应的值
    public void remove(String key) {

    }

    /******************** 查 *************************/

    // 搜索 key 对应的值，不存在则返回 null
    // get("the") -> 4
    // get("tha") -> null
    public V get(String key) {
        //从root开始搜索key
        TrieNode<V> x = getNode(root, key);
        if (x == null) {
            return null;
        }
        return x.val;
    }

    // 判断 key 是否存在在 Map 中
    // 注意！！就算getNode(key)的返回值x非空，也只能说字符串key是一个「前缀」；除非x.val同时非空，才能判断键key存在

    // containsKey("tea") -> false
    // containsKey("team") -> true
    public boolean containsKey(String key) {
        return get(key) != null;
    }

    // 判断是和否存在前缀为 prefix 的键
    // hasKeyWithPrefix("tha") -> true
    // hasKeyWithPrefix("apple") -> false
    public boolean hasKeyWithPrefix(String prefix) {
        //只要能找到prefix对应的节点，就是存在前缀
        return getNode(root, prefix) != null;
    }

    // 在 Map 的所有键中搜索 query 的最短前缀：只要在第一次遇到存有val的节点的时候返回就行
    // shortestPrefixOf("themxyz") -> "the"
    public String shortestPrefixOf(String query) {
        TrieNode<V> p = root;
        // 从节点node开始搜索key
        for (int i = 0; i < query.length(); i++) {
            if (p == null) {
                return "";
            }
            if (p.val != null) {
                //找到一个键是query的前缀
                return query.substring(0, i);
            }
            // 向下搜索
            char c = query.charAt(i);
            p = p.children[c];

        }
        //需要额外检查
        // Trie 树中「树枝」存储字符串，「节点」存储字符串对应的值，for 循环相当于只遍历了「树枝」，但漏掉了最后一个「节点」，即query本身就是TrieMap中的一个键的情况。
        if (p != null && p.val != null) {
            //如果query本身就是个键
            return query;
        }
        return "";
    }

    // 在 Map 的所有键中搜索 query 的最长前缀
    // longestPrefixOf("themxyz") -> "them"
    public String longestPrefixOf(String query) {
        TrieNode<V> p = root;
        //记录前缀的最大长度
        int max_len = 0;

        //从节点node开始搜索key
        for (int i = 0; i < query.length(); i++) {
            if (p == null) {
                break;
            }
            if (p.val != null) {
                //找到一个键是query的前缀，更新前缀的最大长度
                max_len = i;
            }
            //向下搜索
            char c = query.charAt(i);
            p = p.children[c];
        }
        if (p != null && p.val != null) {
            return query;
        }
        return query.substring(0, max_len);
    }

    // 搜索所有前缀为 prefix 的所有键
    // keysWithPrefix("th") -> ["that", "the", "them"]
    public List<String> keysWithPrefix(String prefix) {
        List<String> res = new LinkedList<>();
        //找到匹配prefix在前缀树中的那个节点
        TrieNode<V> x = getNode(root, prefix);
        if (x == null) {
            return res;
        }

        //DFS遍历以x为根的这颗Trie树
        traverse(x, new StringBuilder(prefix), res);
        return res;
    }

    private void traverse(TrieNode<V> root, StringBuilder path, List<String> res) {
        if (root == null) {
            //到达底部叶子节点
            return;
        }

        if (root.val != null) {
            //找到一个key，添加到结果列表中
            res.add(path.toString());
        }

        //回溯算法遍历框架
        for (char c = 0; c < R; c++) {
            //做选择
            path.append(c);
            traverse(root.children[c], path, res);
            //撤销选择
            path.deleteCharAt(path.length() - 1);
        }
    }


    // 通配符 . 匹配任意字符，搜索所有匹配的键
    // keysWithPattern("t.a.") -> ["team", "that"]
    public List<String> keysWithPattern(String pattern) {
        List<String> res = new LinkedList<>();
        traverse(root, new StringBuilder(), pattern, 0, res);
        return res;
    }

    private void traverse(TrieNode<V> node, StringBuilder path, String pattern, int i, List<String> res) {
        if (node == null) {
            //树枝不存在，即匹配失败
            return;
        }
        if (i == pattern.length()) {
            //pattern匹配完成
            if (node.val != null) {
                //如果这个节点存着val，则找到一个匹配的键
                res.add(path.toString());
            }
            return;
        }
        char c = pattern.charAt(i);
        if (c == '.') {
            //pattern[i]是通配符，可以变化成任意字符
            //多叉树（回溯算法）遍历框架
            for (char j = 0; j < R; j++) {
                path.append(j);
                traverse(node.children[j], path, pattern, i + 1, res);
                path.deleteCharAt(path.length() - 1);
            }
        } else {
            //pattern[i]是普通字符c
            path.append(c);
            traverse(node.children[c], path, pattern, i + 1, res);
            path.deleteCharAt(path.length() - 1);
        }
    }


    // 通配符 . 匹配任意字符，判断是否存在匹配的键
    // hasKeyWithPattern(".ip") -> true
    // hasKeyWithPattern(".i") -> false
    public boolean hasKeyWithPattern(String pattern) {
        //从root节点开始匹配pattern[0..]
        return hasKeyWithPattern(root, pattern, 0);
    }

    //函数定义：从node节点开始匹配pattern[i..]，返回是否匹配成功
    private boolean hasKeyWithPattern(TrieNode<V> node, String pattern, int i) {
        if (node == null) {
            //树枝不存在，匹配失败
            return false;
        }
        if (i == pattern.length()) {
            //模式串走到头了，看看匹配到的是否是一个键
            return node.val != null;
        }
        char c = pattern.charAt(i);
        //没有遇到通配符
        if (c != '.') {
            // 从node.children[c]节点开始匹配pattern[i+1..]
            return hasKeyWithPattern(node.children[c], pattern, i + 1);
        }
        //遇到通配符
        for (int j = 0; j < R; j++) {
            //pattern[i]可以变成任意字符，尝试所有可能，只要遇到一个匹配成功就返回
            if (hasKeyWithPattern(node.children[j], pattern, i + 1)) {
                return true;
            }
        }
        return false;
    }

}
