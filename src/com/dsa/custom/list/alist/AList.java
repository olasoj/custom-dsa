package com.dsa.custom.list.alist;

import com.dsa.custom.list.List;

import java.util.stream.IntStream;

/**
 * Array-based list implementation
 */
public class AList<E> implements List<E> {
    private static final int DEFAULT_SIZE = 10; // Default size
    private final int maxSize;
    private final E[] listArray;
    private int listSize;
    private int curr;

    // Maximum size of list
    // Number of list items now
    // Position current element
    // Array holding list elements

    /**
     * Create a list with the default capacity.
     */
    public AList() {
        this(DEFAULT_SIZE);
    }

    /**
     * Create a new list object.
     *
     * @param size Max number of elements list can contain.
     */
    // Generic array allocation
    @SuppressWarnings("unchecked")
    public AList(int size) {
        maxSize = size;
        listSize = curr = 0;
        listArray = (E[]) new Object[size];  // Create listArray
    }

    public void clear() {          // Reinitialize the list
        listSize = curr = 0;
    }   // Simply reinitialize values

    /**
     * Insert "it" at current position
     */
    public void insert(E it) {
        if (isListFull()) return;

        // Shift elements up
        //   to make room
        IntStream.iterate(listSize, i -> i > curr, i -> i - 1).forEach(i -> listArray[i] = listArray[i - 1]);

        listArray[curr] = it;
        listSize++;               // Increment list size
    }

    private boolean isListFull() {
        return listSize >= maxSize;
    }

    public void append(E it) { // Append "it"
        if (isListFull()) return;
        listArray[listSize++] = it;
    }

    public void moveToStart() {
        curr = 0;
    }

    // Remove and return the current element.
    public E remove() {
        if ((curr < 0) || (curr >= listSize))  // No current element
            return null;
        E it = listArray[curr];      // Copy the element
        // Shift them down
        int bound = listSize - 1;
        for (int i = curr; i < bound; i++) {
            listArray[i] = listArray[i + 1];
        }
        listSize--;                   // Decrement size
        return it;
    }

    public void moveToEnd() {
        curr = listSize;
    }

    public void prev() {
        if (curr != 0) curr--;
    }

    public void next() {
        if (curr < listSize) curr++;
    } // Next

    // Return list size
    public int length() {
        return listSize;
    }

    public int currPos() { // Return current position
        return curr;
    }

    // Set current list position to "pos"
    public void moveToPos(int pos) {
        if ((pos < 0) || (pos > listSize)) throw new IllegalArgumentException("Pos out of range");
        curr = pos;
    }

    public E getValue() {     // Return current element
        if ((curr < 0) || (curr >= listSize)) throw new IllegalStateException("No current element");
        return listArray[curr];
    }

}
