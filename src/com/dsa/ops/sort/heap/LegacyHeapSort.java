package com.dsa.ops.sort.heap;

import com.dsa.custom.heap.MaxHeap;
import com.dsa.custom.heap.MaxPQ;
import com.dsa.ops.sort.Sort;
import com.dsa.ops.sort.SortType;

public class LegacyHeapSort implements Sort {
    @Override
    public <E extends Comparable<? super E>> void sort(E[] array) {

        int length = array.length;
        MaxHeap<E> maxPQ = new MaxPQ<>(length);

        for (E arr : array)
            maxPQ.insert(arr);

        while (length != 0) {
            E e = maxPQ.removeMax();
            array[length - 1] = e;
            length--;
        }

    }

    @Override
    public SortType sortType() {
        return SortType.HEAP;
    }
}
