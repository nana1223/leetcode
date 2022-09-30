package labuladong.设计数据结构;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * LFU 算法（一种淘汰算法）是淘汰访问频次最低的数据，如果访问频次最低的数据有多条，需要淘汰最旧的数据。
 * <p>
 * 【一】LFU算法要满足的要求：
 * 1、调用get(key)方法时，要返回该key对应的val。
 * 2、只要用get或者put方法访问一次某个key，该key的freq就要加一。
 * 3、如果在容量满了的时候进行插入，则需要将freq最小的key删除，如果最小的freq对应多个key，则删除其中最旧的那一个。
 * <p>
 * 【二】满足LFU算法需求的数据结构设计：
 * 1.使用一个HashMap存储key到val的映射，就可以快速计算get(key)：   HashMap<Integer, Integer> keyToVal;
 * 2.使用一个HashMap存储key到freq的映射，就可以快速操作key对应的freq ：HashMap<Integer, Integer> keyToFreq;
 * 3.LFU算法的核心数据结构
 * 3.1、首先，肯定是需要freq到key的映射，用来找到freq最小的key。
 * 3.2、可能有多个key拥有相同的freq，所以 freq对key是一对多的关系，即一个freq对应一个key的列表。
 * 3.3、希望freq对应的key的列表是存在时序的，便于快速查找并删除最旧的key。
 * 3.4、希望能够快速删除key列表中的任何一个key，因为如果频次为freq的某个key被访问，那么它的频次就会变成freq+1，就应该从freq对应的key列表中删除，加到freq+1对应的key的列表中。
 * HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
 * 3.5、将freq最小的key删除，那你就得快速得到当前所有key最小的freq是多少。想要时间复杂度 O(1) 的话，肯定不能遍历一遍去找，那就用一个变量minFreq来记录当前最小的freq吧。
 * int minFreq = 0;
 * <p>
 * 【三】
 * LinkedHashSet顾名思义，是链表和哈希集合的结合体。链表不能快速访问链表节点，但是插入元素具有时序；哈希集合中的元素无序，但是可以对元素进行快速的访问和删除。
 * 那么，它俩结合起来就兼具了哈希集合和链表的特性，既可以在 O(1) 时间内访问或删除其中的元素，又可以保持插入的时序
 * <p>
 * --------------------------------------------------------------------------------------------------------------------
 * 当代码逻辑复杂不易实现的技巧（例如本文要维护三个表KV表，KF表，FK表的映射）：
 * 1、不要企图上来就实现算法的所有细节，而应该自顶向下，逐步求精，先写清楚主函数的逻辑框架，然后再一步步实现细节。
 * <p>
 * 2、搞清楚映射关系，如果我们更新了某个key对应的freq，那么就要同步修改KF表和FK表，这样才不会出问题。
 * <p>
 * 3、画图，画图，画图，重要的话说三遍，把逻辑比较复杂的部分用流程图画出来，然后根据图来写代码，可以极大减少出错的概率。
 * --------------------------------------------------------------------------------------------------------------------
 */
public class LFUCache {

    /**
     * 用到的数据结构
     * 1.key到val的映射
     * 2.key到频次的映射
     * 3.频次对应多个key的映射
     * 4.存最小频次的频次数
     */
    private HashMap<Integer, Integer> keyToVal;
    private HashMap<Integer, Integer> keyToFreq;
    private HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
    private int minFreq;

    //记录LFU缓存的最大容量
    private int maxCap;

    public LFUCache(int capacity) {

        keyToVal = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKeys = new HashMap<>();
        this.maxCap = capacity;
        this.minFreq = 0;

    }

    /**
     * 先捋一下get的逻辑
     * 1.从KV表中拿到val值，如果有的话。没有就返回-1
     * 2.拿完之后再更新KF表key对应的freq+1，以及更新FK表把key从之前的freq列表中拿出来放到freq+1的列表，
     * 3.！！！注意还要检查拿出后freq对应的key列表是否为空
     * 4.还要看看minFreq是否要更新
     * （把234的操作抽象成一个函数increaseFreq）
     */
    public int get(int key) {
        if (keyToVal.containsKey(key)) {

            //增加key对应的freq
            increaseFreq(key);
            return keyToVal.get(key);
        }
        return -1;
    }

    private void increaseFreq(int key) {
        //更新KF表
        int freq = keyToFreq.get(key);
        keyToFreq.put(key, freq + 1);

        //更新FK表
        //将 key 从 freq 对应的列表中删除
        LinkedHashSet oldKeys = freqToKeys.get(freq);
        oldKeys.remove(key);
        //将 key 加入 freq + 1 对应的列表中
        freqToKeys.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freqToKeys.get(freq + 1).add(key);
        //如果freq对应的key列表空了，就移除这个freq
        if (freqToKeys.get(freq).isEmpty()) {
            freqToKeys.remove(freq);
            //如果这个freq恰好是minFreq，则更新minFreq
            if (freq == minFreq) {
                minFreq++;
            }
        }
    }

    /**
     * 捋put的逻辑（画图）
     * 1.先看KV表中有无对应的key，若key已存在，则1修改key对应的val 2增加对应的freq；若key不存在，需要新插入key
     * 2.插入key时，看容量是否已满，若容量已满，还需要淘汰freq最小的key
     * 3.然后插入key对应的val，然后key对应的freq为1
     */
    public void put(int key, int value) {
        if (this.maxCap <= 0) {
            return;
        }
        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, value);
            increaseFreq(key);
            return;
        }
        //容量已满
        if (keyToVal.size() >= this.maxCap) {
            //淘汰一个freq最小的key
            removeMinFreqKey();
        }
        //插入KV表
        keyToVal.put(key, value);
        //插入KF表
        keyToFreq.put(key, 1);
        //插入FK表
        freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
        freqToKeys.get(1).add(key);
        //更新minFreq（插入新key后最小的freq肯定是1）
        this.minFreq = 1;
    }

    private void removeMinFreqKey() {

        // freq 最小的 key 列表
        LinkedHashSet<Integer> minKeys = freqToKeys.get(minFreq);
        //其中最先被插入的那个key就是该淘汰的
        int deletedKey = minKeys.iterator().next();

        //更新FK表
        minKeys.remove(deletedKey);
        if (minKeys.isEmpty()) {
            freqToKeys.remove(minFreq);
            //这里没必要更新minFreq变量，因为你想想removeMinFreqKey这个函数是在什么时候调用？在put方法中插入新key时可能调用。
            // 而你回头看put的代码，插入新key时一定会把minFreq更新成 1，所以说即便这里minFreq变了，我们也不需要管它。
        }
        //更新KV表
        keyToVal.remove(deletedKey);
        //更新KF表
        keyToFreq.remove(deletedKey);

    }
}
