package labuladong.设计数据结构;

import java.util.HashMap;

/**
 * 146.LRU缓存 : 选择最近最久未使用的页面予以淘汰，一种页面置换算法
 * <p>
 * LRU 缓存算法的核心数据结构就是————哈希链表，双向链表和哈希表的结合体！！！！！！！！！！！！！！！！！！！！！！！！！！
 * 哈希表存key，然后value存双向链表的节点Node，然后双向链表的每个节点存Node(key和value)
 * <p>
 * 要让 put 和 get 方法的时间复杂度为 O(1)，我们可以总结出 cache 这个数据结构必要的条件：
 * <p>
 * 1、显然 cache 中的元素必须有时序，以区分最近使用的和久未使用的数据，当容量满了之后要删除最久未使用的那个元素腾位置。
 * 2、我们要在 cache 中快速找某个 key 是否已存在并得到对应的 val；
 * 3、每次访问 cache 中的某个 key，需要将这个元素变为最近使用的，也就是说 cache 要支持在任意位置快速插入和删除元素。
 * <p>
 * 那么，什么数据结构同时符合上述条件呢？哈希表查找快，但是数据无固定顺序；链表有顺序之分，插入删除快，但是查找慢。
 * 所以结合一下，形成一种新的数据结构：哈希链表 LinkedHashMap。
 * <p>
 * 分析上面的 3 个条件：
 * <p>
 * 1、如果我们每次默认从链表尾部添加元素，那么显然越靠尾部的元素就是最近使用的，越靠头部的元素就是最久未使用的。
 * 2、对于某一个 key，我们可以通过哈希表快速定位到链表中的节点，从而取得对应 val。
 * 3、链表显然是支持在任意位置快速插入和删除的，改改指针就行。只不过传统的链表无法按照索引快速访问某一个位置的元素，而这里借助哈希表，可以通过 key 快速映射到任意一个链表节点，然后进行插入和删除。
 * <p>
 * 疑问：为什么要是双向链表，单链表行不行？另外，既然哈希表中已经存了 key，为什么链表中还要存 key 和 val 呢，只存 val 不就行了？
 * 答：
 * 1.因为我们需要删除操作。删除一个节点不光要得到该节点本身的指针，也需要操作其前驱节点的指针，而双向链表才能支持直接查找前驱，保证操作的时间复杂度 O(1)
 * 2.当缓存容量已满，我们不仅仅要删除最后一个 Node 节点，还要把 map 中映射到该节点的 key 同时删除，而这个 key 只能由 Node 得到。
 * 如果 Node 结构中只存储 val，那么我们就无法得知 key 是什么，就无法删除 map 中的键，造成错误
 * <p>
 * 注意！！！
 * 1.注意我们实现的双链表 API 只能从尾部插入，也就是说靠尾部的数据是最近使用的，靠头部的数据是最久未使用的。
 * 2.由于我们要同时维护一个双链表 cache 和一个哈希表 map，很容易漏掉一些操作，比如说删除某个 key 时，在 cache 中删除了对应的 Node，但是却忘记在 map 中删除 key。
 * 解决这种问题的有效方法是：在这两种数据结构之上提供一层抽象 API。
 * 就是尽量让 LRU 的主方法 get 和 put 避免直接操作 map 和 cache 的细节
 * <p>
 * 很多编程语言都有内置的哈希链表或者类似 LRU 功能的库函数，但是为了理解算法的细节，先自己造轮子实现一遍 LRU 算法，然后再使用 Java 内置的 LinkedHashMap 来实现一遍。
 */
public class LRUCache {

    //双链表的节点类
    class Node {
        public int key;
        public int value;
        public Node next;
        public Node prev;

        public Node(int k, int v) {
            this.key = k;
            this.value = v;
        }
    }

    //依靠Node类构建一个双链表
    class DoubleList {
        //头尾虚节点
        private Node head, tail;

        private int size;

        public DoubleList() {
            //初始化双向链表的数据
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        //在链表尾部添加节点x，时间O(1) [修改4个指针]
        public void addLast(Node x) {
            x.prev = tail.prev;
            x.next = tail;

            tail.prev.next = x;
            tail.prev = x;

            size++;
        }

        //删除链表中的x节点（x一定存在）
        public void remove(Node x) {
            x.prev.next = x.next;
            x.next.prev = x.prev;
            size--;
        }

        //删除链表中的第一个节点,并返回该节点，时间O(1)
        public Node removeFirst() {
            if (head.next == tail) {
                return null;
            }
            Node first = head.next;
            remove(first);
            return first;
        }

        public int getSize() {
            return size;
        }
    }


    // key -> Node(key, val)
    private HashMap<Integer, Node> map;

    //Node(k1,v1) <-> Node(k2,v2)
    private DoubleList cache;

    //最大容量
    private int cap;

    /**
     * 提出来一层抽象API 同时对双向链表和哈希表操作
     */

    //将某个key提升为最近使用
    private void makeRecent(int key) {
        Node x = map.get(key);
        //1.先从链表中删除这个节点
        cache.remove(x);
        //2.重新插到链表尾部
        cache.addLast(x);
    }

    //添加最近使用的元素
    private void addRecent(int key, int val) {
        Node node = new Node(key, val);
        //1.添加到链表尾部
        cache.addLast(node);
        //2.别忘了在map中添加key的映射
        map.put(key, node);
    }

    //删除某一个key
    private void deleteKey(int key) {
        Node node = map.get(key);
        //从链表中删除
        cache.remove(node);
        //从map中删除
        map.remove(key);
    }

    //删除最久未使用的元素
    private void removeLeastRecent() {
        //1.删除链表头部的第一个元素（链表头部的第一个元素就是最久未使用的）
        Node node = cache.removeFirst();
        //2.删除map中的该元素
        map.remove(node.key);
    }

    /**
     * ---------------------------------------------------------------------------------------------------------
     * 以上：
     * 1.实现双链表的节点类
     * 2.实现双链表类（对双链表进行删除操作、删除头结点操作、链表尾部添加元素操作）
     * 3.把对哈希表和双链表同时操作抽出来抽在同一个API里直接调用
     * ---------------------------------------------------------------------------------------------------------
     * 以下：
     * 实现真正的LRU 的get和put方法
     */

    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    public int get(int key) {

        if (!map.containsKey(key)) {
            return -1;
        }
        //将该数据提升为最近使用的
        makeRecent(key);
        //返回该数据值
        return map.get(key).value;
    }

    /**
     * 捋一下put的逻辑
     * 1.若key已存在，则1删除旧key 2将新key加入最近使用
     * 2.若key不存在，则需要插入新的key
     * 3.插入新key时 若容量已满，则1先淘汰最久未使用的key2 再插入
     * 4.插入新key时，若容量未满，则可以直接插入
     */
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            //1.删除旧key
            deleteKey(key);
            //2.新插入的数据为最近使用的数据
            addRecent(key, value);
            return;
        }

        if (cap == cache.getSize()) {
            removeLeastRecent();
        }
        addRecent(key, value);

    }
}
