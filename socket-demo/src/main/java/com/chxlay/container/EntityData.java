package com.chxlay.container;

/**
 * @author Alay
 * @date 2020-12-11 10:03
 * @project Braineex
 */
public class EntityData<K, V> {

	private K key;
	private V value;

	public EntityData<K, V> key(K key) {
		this.key = key;
		return this;
	}

	public EntityData<K, V> value(V value) {
		this.value = value;
		return this;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}
}