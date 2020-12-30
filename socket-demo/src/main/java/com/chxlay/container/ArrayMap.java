package com.chxlay.container;

import javax.validation.constraints.NotNull;
import java.util.HashMap;

/**
 * Array --- Map 组合数据结构
 * <p>
 * 初始化固定大小的 Map , Map数据一旦满了,后来的数据会顺序的替换老数据
 * <p>
 * 新进数据==inData==>> | data | data | data |   ==outData==>>淘汰数据
 *  ----->>>>>>  此类性能不及 QueueMap 已经废弃<<<<<<<<-------------------
 * @author Alay
 * @date 2020-12-09 11:24
 * @project Braineex
 */
@Deprecated
public final class ArrayMap<K, V> {
    private final int maxSize;
    private Data[] dataArea;
    /**
     * 存放数据数组的最头部空闲空间的索引,初始化为0,图解：src\main\resources\ArrayMap.png
     * |———4————|  maxSize =5, front = maxSize - 1 则为满,最后一个元素不做任何存储,空着
     * |———3————|
     * |———2————|
     * |———1————|
     * |———0————|
     * |  data  |
     * |———空———|  <---- front = 0, 为空,front 是一个游标
     * front 等于实际存储数据的 size
     */
    private volatile int front;


    /**
     * 不建议使用大的数据存储,数组实现的,怎删除不利
     *
     * @param maxSize 建议100以内
     */
    public ArrayMap(int maxSize) {
        // 最后一个元素不存储数据,所以多预留出一个位置
        this.maxSize = maxSize + 1;
        this.front = 0;
        this.dataArea = new Data[maxSize + 1];
    }

    public int maxSize() {
        // 最后一个元素不做存储使用
        return this.maxSize - 1;
    }

    public int size() {
        return front;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * 容器是否已经满了
     *
     * @return
     */
    public boolean isFull() {
        // 结合的最后一个元素不做存储,空着
        return this.front != 0 && this.maxSize - 1 == this.front;
    }

    public synchronized void put(@NotNull K key, V value) {
        Data<K, V> newData = new Data<>(key, value);
        int index = this.indexAt(newData);
        // 新添加的数据不存在,直接添加
        if (index == -1) {
            // 判断容器是否已经装满,则将所有元素往前移动一位,新的元素存储在最后一位
            if (this.isFull()) {
                for (int i = 0; i < front; i++) {
                    this.dataArea[i] = this.dataArea[i + 1];
                }
                // 新的元素放在最后一位
                this.dataArea[front - 1] = newData;
            } else {
                // 容器未满,数据直接存入最后一个位置
                this.dataArea[front] = newData;
                ++front;
            }
        } else {
            // 新添加的数据已经存在,删除老的数据,新的数据添加在尾部,索引大于被删除的数据往前移动一位
            for (int i = index; i < front; i++) {
                // 索引往前移动一位
                this.dataArea[i] = this.dataArea[i + 1];
            }
            this.dataArea[front - 1] = newData;
        }
    }

    /**
     * 移除容器中的数据
     *
     * @param key
     * @return
     */
    public boolean remove(K key) {
        return this.remove(new Data(key, null));
    }

    /**
     * 移除容器中的数据
     *
     * @param removeData
     */
    public boolean remove(Data removeData) {
        int index = this.indexAt(removeData);
        if (index != -1) {
            // 移除的数据存在
            if (index == front - 1) {
                // 移除的数据在尾部,直接移除即可
                this.dataArea[index] = null;
            } else {
                // 移除数据的同时还需要将 移除数据索引之后的数据进行往前移位
                for (int i = index; i < front; i++) {
                    this.dataArea[i] = this.dataArea[i + 1];
                }
            }
            // 数据最前部 -1
            --front;
            return true;
        }
        return false;
    }

    public Data<K, V> getData(K key) {
        int index = this.indexAt(key);
        if (index != -1) {
            return this.dataArea[index];
        }
        return null;
    }

    /**
     * 数据中是否已经存在这个Key
     *
     * @param key
     * @return
     */
    public boolean hasKey(K key) {
        for (int i = 0; i < front; i++) {
            Data data = dataArea[i];
            if (data.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public int indexAt(K key) {
        return this.indexAt(new Data(key, null));
    }

    /**
     * 返回数据所在的Index,不存在的数据返回-1
     *
     * @param inData
     * @return 数据的索引, 若不存在则返回 -1
     */
    public int indexAt(Data inData) {
        for (int i = 0; i < this.front; i++) {
            Data data = this.dataArea[i];
            if (data.equals(inData)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 返回全部的数据
     *
     * @return
     */
    public HashMap<K, V> showData() {
        HashMap<K, V> resultMap = new HashMap<>();
        if (!this.isEmpty()) {
            for (int i = 0; i < front; i++) {
                Data<K, V> data = this.dataArea[i];
                resultMap.put(data.getKey(), data.getValue());
            }
        }
        return resultMap;
    }

    /**
     * 具体的数据对象
     *
     * @param <K>
     * @param <V>
     */
    public final class Data<K, V> {
        private final K key;
        private final V value;

        private Data(@NotNull K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(@NotNull Object inData) {
            if (this == inData) {
                return true;
            }
            if (inData instanceof Data) {
                Data data = (Data) inData;
                return this.hash(data.key) == this.hash(this.key);
            }
            return false;
        }

        /**
         * 从HashMap中复制来得Hash算法,借用HashMap 的 hash(Object key) 方法
         *
         * @param key
         * @return
         * @link:HashMap.hash(Object key)
         */
        private int hash(Object key) {
            if (null == key) {
                return 0;
            }
            int hash = key.hashCode();
            hash = hash ^ (hash >>> 16);
            return hash;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
