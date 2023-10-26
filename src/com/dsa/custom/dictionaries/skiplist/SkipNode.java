package com.dsa.custom.dictionaries.skiplist;

public class SkipNode<K, E> {

    K key;

    E element;

    SkipNode<K, E>[] forward;

    public SkipNode(K k, E newValue, int newLevel) {

    }

    public K key() {
        return key;
    }

    public E element() {
        return element;
    }

    public SkipNode<K, E>[] forward() {
        return forward;
    }
}
