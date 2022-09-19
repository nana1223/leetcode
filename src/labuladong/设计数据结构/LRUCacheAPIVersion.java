package labuladong.设计数据结构;

import java.util.LinkedHashMap;

/**
 * @author zhangna
 */
public class LRUCacheAPIVersion {

    private int cap;

    //java给封装好的哈希链表
    private LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();

    public LRUCacheAPIVersion(int capacity) {
        this.cap = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        //将key变为最近使用
        makeRecent(key);
        return cache.get(key);

    }

    public void put(int key, int val) {
        if (cache.containsKey(key)) {
            //1.修改key值
            cache.put(key, val);
            //2.将key改为最近使用
            makeRecent(key);
            return;
        }

        if (cache.size() >= this.cap) {
            int oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }
        cache.put(key, val);

    }

    private void makeRecent(int key) {
        int val = cache.get(key);
        //删除key重新插入队尾
        cache.remove(key);
        cache.put(key, val);
    }
}
