package com.dsa.custom.dictionaries;

public class KVPair<K, E> {
    private final K key;
    private final E element;

    // Constructors
    public KVPair() {
        key = null;
        element = null;
    }

    public KVPair(K key, E element) {
        this.key = key;
        this.element = element;
    }

    // Data member access functions
    public K key() {
        return key;
    }

    public E value() {
        return element;
    }
}

