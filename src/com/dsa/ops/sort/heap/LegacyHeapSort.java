package com.dsa.ops.sort.heap;

import com.dsa.custom.heap.MaxHeap;
import com.dsa.custom.heap.MaxHeap1;
import com.dsa.ops.sort.Sort;
import com.dsa.ops.sort.SortType;

public class LegacyHeapSort implements Sort {

    @Override
    public <E extends Comparable<? super E>> void sort(E[] array) {

        int length = array.length;
        MaxHeap<E> maxPQ = new MaxHeap1<>(array, length, length);

        for (int i = 0; i < array.length; i++)  // Now sort
            maxPQ.removeMax();
    }

    @Override
    public SortType sortType() {
        return SortType.HEAP;
    }
}
