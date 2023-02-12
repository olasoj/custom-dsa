package com.dsa.custom.dictionaries.btree;

/**
 * Interface for B+ Tree nodes
 */
public interface BPNode<K, E> {
    boolean isLeaf();

    int numrecs();

    K[] keys();
}

