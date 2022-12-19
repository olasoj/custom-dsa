package com.dsa.custom.dictionaries;

/**
 * Dictionary implemented using hashing.
 */
public class HashDictionary<K extends Comparable<? super K>, E> implements Dictionary<K, E> {
    private static final int DEFAULT_SIZE = 10;
    private final int maxsize;      // Maximum size of dictionary
    private HashTable<K, E> hashTable; // The hash table
    private int count;        // # of records now in table

    public HashDictionary() {
        this(DEFAULT_SIZE);
    }

    public HashDictionary(int size) {
        hashTable = new HashTable<>(size);
        count = 0;
        maxsize = size;
    }

    public void clear() {   /** Reinitialize */
        hashTable = new HashTable<>(maxsize);
        count = 0;
    }

    /**
     * Insert an element
     */
    public void insert(K k, E e) {
        assert count < maxsize : "Hash table is full";
        hashTable.hashInsert(k, e);
        count++;
    }

    /**
     * Remove an element
     */
    public E remove(K k) {
        E temp = hashTable.hashRemove(k);
        if (temp != null) count--;
        return temp;
    }

    /**
     * Remove some element.
     */
    public E removeAny() {
        if (count != 0) {
            count--;
            return hashTable.hashRemoveAny();
        } else return null;
    }

    /**
     * Find a record with key value "k"
     */
    public E find(K k) {
        return hashTable.hashSearch(k);
    }

    /**
     * Return number of values in the hash table
     */
    public int size() {
        return count;
    }
}
