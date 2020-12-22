package com.chxlay.container;

import io.netty.channel.ChannelId;

import java.util.Iterator;
import java.util.Map;

/**
 * 用户通道映射关系
 *
 * @author Alay
 * @date 2020-12-08 18:21
 * @project Braineex
 */
public final class UserChannelRel<K, V> {

	private ChannelId channelId;
	private final QueueMap<K, V> queueMap;


	public UserChannelRel(ChannelId channelId, int size) {
		this.channelId = channelId;
		this.queueMap = QueueMap.allocate(size);
	}

	public ChannelId channelId() {
		return channelId;
	}

	public void addChannel(K key, V value) {
		this.queueMap.put(key, value);
	}

	public void removeChannel(K key) {
		this.queueMap.remove(key);
	}

	public V getCareChannel(K key) {
		return this.queueMap.get(key);
	}

	public boolean isEmpty() {
		return this.queueMap.isEmpty();
	}

	public void setChannelId(ChannelId channelId) {
		this.channelId = channelId;
	}

	public Iterator<Map.Entry<K, V>> iterator() {
		Iterator<Map.Entry<K, V>> iterator = this.queueMap.entrySet().iterator();
		return iterator;
	}
}