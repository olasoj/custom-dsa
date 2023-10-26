package com.dsa.custom.dictionaries.skiplist;

import com.dsa.custom.dictionaries.Dictionary;
import com.dsa.util.DSUtil;

public class SkipList<K extends Comparable<? super K>, E> implements Dictionary<K, E> {

    private SkipNode<K, E> head;
    private int level;
    private int size;


    @Override
    public void clear() {

    }

    /**
     * Insert a record into the skip list
     */
    @Override
    public void insert(K k, E newValue) {
        int newLevel = randomLevel();  // New node’s level
        if (newLevel > level)          // If new node is deeper
            AdjustHead(newLevel);        //   adjust the header
        // Track end of level
        SkipNode<K, E>[] update = (SkipNode<K, E>[]) new SkipNode[level + 1];
        SkipNode<K, E> x = head;        // Start at header node
        for (int i = level; i >= 0; i--) { // Find insert position
            while ((x.forward[i] != null) &&
                    (k.compareTo(x.forward[i].key()) > 0))
                x = x.forward[i];
            update[i] = x;               // Track end at level i
        }

        x = new SkipNode<>(k, newValue, newLevel);
        for (int i = 0; i <= newLevel; i++) {      // Splice into list
            x.forward[i] = update[i].forward[i]; // Who x points to
            update[i].forward[i] = x;            // Who y points to
        }
        size++;                       // Increment dictionary size
    }

    private void AdjustHead(int newLevel) {

    }

    @Override
    public E remove(K k) {
        return null;
    }

    @Override
    public E removeAny() {
        return null;
    }

    public E find(K searchKey) { // Skiplist Search

        SkipNode<K, E> x = head;            // Dummy header node

        for (int i = level; i >= 0; i--)       // For each level...
            while ((x.forward[i] != null) && // go forward
                    (searchKey.compareTo(x.forward[i].key()) > 0))
                x = x.forward[i];              // Go one last step

        x = x.forward[0];  // Move to actual record, if it exists
        if ((x != null) && (searchKey.compareTo(x.key()) == 0))
            return x.element();              // Got it
        else return null;                  // Its not there
    }

    @Override
    public int size() {
        return 0;
    }

    /**
     * Pick a level using exponential distribution
     */
    int randomLevel() {
        int lev;
        for (lev = 0; DSUtil.random(2) == 0; lev++) ; // Do nothing
        return lev;
    }
}
