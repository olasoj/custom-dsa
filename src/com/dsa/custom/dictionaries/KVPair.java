package com.dsa.custom.dictionaries;

class KVPair<K, E> {
    private final K k;
    private final E e;

    // Constructors
    KVPair() {
        k = null;
        e = null;
    }

    KVPair(K kval, E eval) {
        k = kval;
        e = eval;
    }

    // Data member access functions
    public K key() {
        return k;
    }

    public E value() {
        return e;
    }
}

