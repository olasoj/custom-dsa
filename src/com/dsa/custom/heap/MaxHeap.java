package com.dsa.custom.heap;

import com.dsa.custom.queue.Queue;

public interface MaxHeap<E extends Comparable<? super E>> extends Queue<E> {
    int heapSize();

    boolean isLeaf(int pos);

    int leftChild(int pos);

    int rightChild(int pos);

    int parent(int pos);

    void insert(E val);

    E removeMax();

    E remove(int pos);
}
