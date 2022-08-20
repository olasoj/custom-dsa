package com.dsa.custom.tree.binary.heap;

public interface MaxHeapInterface<T extends Comparable<? super T>> {

    /**
     * Adds a new entry to this heap.
     *
     * @param newEntry An object to be added.
     */
    void add(T newEntry);

    /**
     * Removes and returns the largest item in this heap.
     *
     * @return Either the largest object in the heap or,
     * if the heap is empty before the operation, null.
     */
    T removeMax();

    /**
     * Retrieves the largest item in this heap.
     *
     * @return Either the largest object in the heap or,
     * if the heap is empty, null.
     */
    T getMax();

    /**
     * Detects whether this heap is empty.
     *
     * @return True if the heap is empty, or false otherwise.
     */
    boolean isEmpty();

    /**
     * Gets the size of this heap.
     *
     * @return The number of entries currently in the heap.
     */
    int getSize();

    /**
     * Removes all entries from this heap.
     */
    void clear();
}
