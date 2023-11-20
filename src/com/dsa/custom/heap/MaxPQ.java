package com.dsa.custom.heap;

import com.dsa.util.DSUtil;

import java.util.NoSuchElementException;

import static com.dsa.util.DSUtil.isLess;

/**
 * we represent complete binary trees sequentially within an array by putting the nodes in level order, with the root at position 1, its children at positions 2 and 3, their children in positions 4, 5, 6, and 7, and so on.
 *
 * @param <E>
 */
public class MaxPQ<E extends Comparable<? super E>> implements MaxHeap<E> {

    private final E[] pq;             // heap-ordered complete binary tree
    private int n = 0;            //    in pq[1..N] with pq[0] unused

    public MaxPQ(int maxN) {
        pq = (E[]) new Comparable[maxN + 1];
    }

    @Override
    public void clear() {

    }

    @Override
    public void enqueue(E it) {

    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public E dequeue() {
        return null;
    }

    @Override
    public E frontValue() {
        return null;
    }

    @Override
    public int length() {
        return 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    @Override
    public int heapSize() {
        return pq.length;
    }

    @Override
    public boolean isLeaf(int pos) {
        return leftChild(pos) == 0 && rightChild(pos) == 0;
    }

    @Override
    public int leftChild(int pos) {
        if (pos < 1) throw new IllegalArgumentException();
        if (pos + 1 > pq.length) throw new NoSuchElementException();

        return 2 * (pos);
    }

    @Override
    public int rightChild(int pos) {
        return leftChild(pos) + 1;
    }

    @Override
    public int parent(int pos) {
        if (pos <= 1) throw new IllegalArgumentException();
        if (pos + 1 > pq.length) throw new NoSuchElementException();

        return pos / 2;
    }

    public void insert(E v) {
        pq[++n] = v;
        swim(n);
    }

    @Override
    public E removeMax() {
        return remove(1);
    }

    @Override
    public E remove(int pos) {
        if (pos < 1) throw new IllegalArgumentException();
        if (pos + 1 > pq.length) throw new NoSuchElementException();

        E max = pq[pos];
        DSUtil.swap(pq, pos, n--);
        pq[n + 1] = null;
        sink(pos);
        return max;
    }

    private void swim(int k) {
        while (k > 1 && isLess(pq, (parent(k)), k)) {
            DSUtil.swap(pq, parent(k), k);
            k = parent(k);
        }
    }

    private void sink(int k) {
        while (leftChild(k) <= n) {
            int j = leftChild(k);
            if (j < n && isLess(pq, j, j + 1)) j++;
            if (!isLess(pq, k, j)) break;
            DSUtil.swap(pq, k, j);
            k = j;
        }
    }
}
