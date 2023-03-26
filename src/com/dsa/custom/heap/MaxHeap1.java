package com.dsa.custom.heap;

import com.dsa.util.DSUtil;

/**
 * Complete Bin Tree
 * The formulae for calculating the array indices of the various relatives of a node are as follows.  <p>
 * The total number of nodes in the tree is n.  <p>
 * The index of the node in question is r, which must fall in the range 0 to n − 1.  <p>
 * <p>
 * Parent(r) =⌊(r−1)/2⌋ if r!=0.  <p>
 * leftChild(r) = 2r+1 if 2r+1 < n.  <p>
 * rightChild(r) =2r+2 if 2r+2 < n.  <p>
 * leftSibling(r) =r−1 if r is even.  <p>
 * rightSibling(r)=r+1 if r is odd and r+1< n.
 */
public class MaxHeap1<E extends Comparable<? super E>> implements MaxHeap<E> {

    // Pointer to the heap array
    private final E[] heap;
    // Maximum size of the heap
    private final int size;
    // Number of things in heap
    private int n;

    public MaxHeap1(E[] h, int num, int max) {
        heap = h;
        n = num;
        size = max;
        buildHeap();
    }

    public MaxHeap1(int max) {
        heap = (E[]) new Comparable[max];
        n = max;
        size = max;
        buildHeap();
    }

    /**
     * Return current size of the heap
     */
    @Override
    public int heapSize() {
        return n;
    }

    /**
     * Is pos a leaf position?
     */
    @Override
    public boolean isLeaf(int pos) {
        return (pos >= n / 2) && (pos < n);
    }

    /**
     * Return position for left child of pos
     */
    @Override
    public int leftChild(int pos) {
        if (pos >= n / 2) throw new IllegalArgumentException("Position has no left child");
        return 2 * pos + 1;
    }

    /**
     * Return position for right child of pos
     */
    @Override
    public int rightChild(int pos) {
        if (pos >= (n - 1) / 2) throw new IllegalArgumentException("Position has no right child");
        return 2 * pos + 2;
    }

    /**
     * Return position for parent
     */
    @Override
    public int parent(int pos) {
        if (pos <= 0) throw new IllegalArgumentException("Position has no parent");
        return (pos - 1) / 2;
    }

    /**
     * Heapify contents of Heap
     */
    public void buildHeap() {
        for (int i = n / 2 - 1; i >= 0; i--) siftDown(i);
    }

    /**
     * Insert into heap
     */
    @Override
    public void insert(E val) {
        if (n >= size) throw new IllegalArgumentException("Heap is full");

        int curr = n++;
        heap[curr] = val;                 // Start at end of heap
        // Now sift up until curr’s parent’s key > curr’s key
        while ((curr != 0) &&
                (heap[curr].compareTo(heap[parent(curr)]) > 0)) {
            DSUtil.swap(heap, curr, parent(curr));
            curr = parent(curr);
        }
    }


    /**
     * Put element in its correct place
     */
    private void siftDown(int pos) {
        if ((pos < 0) || (pos >= n)) throw new IllegalArgumentException("Illegal heap position");

        while (!isLeaf(pos)) {
            int j = leftChild(pos);
            if ((j < (n - 1)) && (heap[j].compareTo(heap[j + 1]) < 0))
                j++; // j is now index of child with greater value
            if (heap[pos].compareTo(heap[j]) >= 0)
                return;
            DSUtil.swap(heap, pos, j);
            pos = j;  // Move down
        }
    }

    @Override
    public E removeMax() {     // Remove maximum value
        if (n <= 0) throw new IllegalArgumentException("Removing from empty heap");

        DSUtil.swap(heap, 0, --n); // Swap maximum with last value
        if (n != 0)      // Not on last element
            siftDown(0);   // Put new heap root val in correct place
        return heap[n];
    }

    /**
     * Remove element at specified position
     */
    @Override
    public E remove(int pos) {
        if ((pos < 0) || (pos >= n)) throw new IllegalArgumentException("Illegal heap position");

        DSUtil.swap(heap, pos, --n); // Swap with last value
        while (heap[pos].compareTo(heap[parent(pos)]) > 0) {
            DSUtil.swap(heap, pos, parent(pos));
            pos = parent(pos);
        }
        if (n != 0) siftDown(pos);
// Push down
        return heap[n];
    }
}

