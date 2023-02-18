package com.dsa.custom.heap;

public interface MaxHeap<E extends Comparable<? super E>> {
    int heapSize();

    boolean isLeaf(int pos);

    int leftChild(int pos);

    int rightChild(int pos);

    int parent(int pos);

    void insert(E val);

    E removeMax();

    E remove(int pos);
}
