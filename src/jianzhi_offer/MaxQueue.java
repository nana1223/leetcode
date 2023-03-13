package jianzhi_offer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 面试题59 - II. 队列的最大值
 * <p>
 * 思路：两个队列结构，
 * 一个存真正的数据，
 * 一个存当前队列的最大值（最大值始终在队头），但是入队是从队尾入的
 * 如果新的value大于deque尾端的值，那么deque一直进行pop_back操作，直到尾端的值大于等于value 或者为空
 * <p>
 * 【反思1】：类的成员变量定义时候直接new和在构造函数中new有什么区别
 * 【反思2】：Deque和ArrayDeque的各种操作需要好好学一下
 */
class MaxQueue {

    Deque<Integer> dataQueue;
    Deque<Integer> maxNumQueue;

    public MaxQueue() {
        dataQueue = new ArrayDeque<>();
        maxNumQueue = new ArrayDeque<>();
    }

    public int max_value() {
        return maxNumQueue.isEmpty() ? -1 : maxNumQueue.peek();
    }

    public void push_back(int value) {
        //1.先入数据队列
        dataQueue.offer(value);
        //2.再入最大值队列
        //从队尾向队头弹出所有比value小的值
        while (!maxNumQueue.isEmpty() && maxNumQueue.peekLast() < value) {
            maxNumQueue.pollLast();
        }
        //插入队尾
        maxNumQueue.offerLast(value);


    }

    public int pop_front() {
        if (dataQueue.isEmpty()) {
            return -1;
        }
        int temp = dataQueue.poll();
        //判断一下maxQueue用不用出队列
        if (!maxNumQueue.isEmpty() && temp == maxNumQueue.peekFirst()) {
            maxNumQueue.poll();
        }
        return temp;
    }
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */