package com.dsa.custom.dictionaries.ual;

import com.dsa.custom.dictionaries.Dictionary;
import com.dsa.custom.dictionaries.KVPair;
import com.dsa.custom.list.alist.AList;

/**
 * Dictionary implemented by unsorted array-based list.
 */
public class UALDictionary<K, E> implements Dictionary<K, E> {
    private static final int DEFAULT_SIZE = 10; // Default size
    private final AList<KVPair<K, E>> list;  // To store dictionary

    // Constructors
    public UALDictionary() {
        this(DEFAULT_SIZE);
    }

    public UALDictionary(int sz) {
        list = new AList<>(sz);
    }

    public void clear() {
        list.clear();
    }

    /**
     * Insert an element: append to list
     */
    public void insert(K k, E e) {
        // Reinitialize
        KVPair<K, E> temp = new KVPair<>(k, e);
        list.append(temp);
    }

    /**
     * Use sequential search to find the element to remove
     */
    public E remove(K k) {
        E temp = find(k);
        if (temp != null) list.remove();
        return temp;
    }

    /**
     * Remove the last element
     */
    public E removeAny() {
        if (size() != 0) {
            list.moveToEnd();
            list.prev();
            KVPair<K, E> e = list.remove();
            return e.value();
        } else return null;
    }

    // Find "k" using sequential search
    public E find(K k) {
        for (list.moveToStart(); list.currPos() < list.length(); list.next()) {
            KVPair<K, E> temp = list.getValue();
            if (k == temp.key())
                return temp.value();
        }
        return null; // "k" does not appear in dictionary
    }


    public int size() { // Return list size
        return list.length();
    }
}
