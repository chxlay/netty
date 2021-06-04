package com.chxlay.container;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 此Map将不会扩容,后来者挤出先来者(图解：/src/main/resources/ArrayMap.png)
 *
 * @author Alay
 * @date 2020-12-11 16:05
 * @project Braineex
 * @see /src/main/resources/ArrayMap.png
 */
public class QueueMap<K, V> extends LinkedHashMap<K, V> {
    private static final long serialVersionUID = 362498820763181265L;
    /**
     * Map 允许容纳数据容量的大小
     */
    private final int CAPACITY;

    /**
     * Map实际的 Size 大小
     */
    private static int maxSize;
    /**
     * HashMap的扩容加载因子
     */
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * 为了不让Hap新型扩容,初始化时将Map容量与加载因子进行计算+1
     *
     * @param capacity
     */
    private QueueMap(int capacity) {
        this.CAPACITY = capacity;
    }

    /**
     * Map将不会扩容
     *
     * @param capacity
     * @return
     */
    public static QueueMap allocate(int capacity) {
        maxSize = 1 + (int) (capacity / DEFAULT_LOAD_FACTOR);
        return new QueueMap(maxSize);
    }

    @Override
    public V put(K key, V value) {
        boolean exist = this.containsKey(key);
        if (exist) {
            // 存入的Key已经存在
            this.remove(key);
        } else {
            if (this.size() == this.CAPACITY) {
                // 数据满了(限定大小的),删掉头一个元素
                this.removeHead();
            }
        }
        return super.put(key, value);
    }

    /**
     * 获取第一个元素
     *
     * @return
     */
    public Map.Entry<K, V> getHead() {
        return this.entrySet().iterator().next();
    }

    /**
     * 删除头元素,并且返回被删除的头元素
     *
     * @return
     */
    public Map.Entry<K, V> removeHead() {
        Map.Entry<K, V> result = null;
        Iterator<Map.Entry<K, V>> iterator = this.entrySet().iterator();
        if (iterator.hasNext()) {
            result = iterator.next();
            iterator.remove();
        }
        return result;
    }

}