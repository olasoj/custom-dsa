package com.dsa.ops.sort.merge;

import com.dsa.ops.sort.SortType;

import static com.dsa.ops.sort.SortType.MERGE;

public class TopDownMergeSort implements MergeSort {

    private static <E extends Comparable<? super E>> void mergeSort(E[] a, int lo, int hi) {  // Sort a[lo..hi].
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(a, lo, mid);       // Sort left half.
        mergeSort(a, mid + 1, hi);     // Sort right half.
        MergeSort.merge(a, lo, mid, hi);  // Merge results (code on page 271).
    }

    @Override
    public <E extends Comparable<? super E>> void sort(E[] array) {

        mergeSort(array, 0, array.length - 1);
    }

    @Override
    public SortType sortType() {
        return MERGE;
    }
}
