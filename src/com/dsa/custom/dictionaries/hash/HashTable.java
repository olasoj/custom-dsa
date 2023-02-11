package com.dsa.custom.dictionaries.hash;

public class HashTable<K, E> {
    public HashTable(int maxsize) {

    }

    public void hashInsert(K k, E e) {

    }

    public E hashRemove(K k) {
        return null;
    }

    public E hashRemoveAny() {
        return null;
    }

    public E hashSearch(K k) {
        return null;
    }

    private int hash(K x) {
        return (x.hashCode() & 0x7fffffff) % 2;
    }

}
