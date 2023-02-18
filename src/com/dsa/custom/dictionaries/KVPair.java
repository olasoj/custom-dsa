package com.dsa.custom.dictionaries;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KVPair)) return false;

        KVPair<?, ?> kvPair = (KVPair<?, ?>) o;
        return Objects.equals(key, kvPair.key) && Objects.equals(element, kvPair.element);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, element);
    }

    @Override
    public String toString() {
        return "KVPair{" +
                "key=" + key +
                ", element=" + element +
                '}';
    }
}

