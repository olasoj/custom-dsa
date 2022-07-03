package com.dsa.custom.dictionaries;

import com.dsa.custom.list.AList;

/**
 * Dictionary implemented by unsorted array-based list.
 */
class UALDictionary<K, E> implements Dictionary<K, E> {
    private static final int defaultSize = 10; // Default size
    private final AList<KVPair<K, E>> list;  // To store dictionary

    // Constructors
    UALDictionary() {
        this(defaultSize);
    }

    UALDictionary(int sz) {
        list = new AList<KVPair<K, E>>(sz);
    }

    public void clear() {
        list.clear();
    }

    /**
     * Insert an element: append to list
     */
    public void insert(K k, E e) {
// Reinitialize
        KVPair<K, E> temp = new KVPair<K, E>(k, e);
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
        for (list.moveToStart(); list.currPos() < list.length();
             list.next()) {
            KVPair<K, E> temp = list.getValue();
            if (k == temp.key())
                return temp.value();
        }
        return null; // "k" does not appear in dictionary
    }


    public int size() // Return list size
    {
        return list.length();
    }
}
