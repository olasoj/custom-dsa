package com.dsa.ops.sort.merge.concurrent;


import java.util.concurrent.ForkJoinPool;

public class ConcurrentMergeSort {

    public <E extends Comparable<? super E>> void mergeSort(E[] data, int start, int end) {

        MergeSortTask<E> task = new MergeSortTask<>(data, start, end, null);
        ForkJoinPool.commonPool().invoke(task);

    }
}
