package com.dsa.ops.sort.merge;

import com.dsa.ops.sort.SortType;

import static com.dsa.ops.sort.SortType.MERGE;

public class BottomUpMergeSort implements MergeSort {

    private static <E extends Comparable<? super E>> void mergeSort(E[] a) {  // Sort a[lo..hi].
        int n = a.length;

        for (int sz = 1; sz < n; sz = sz + sz)
            for (int lo = 0; lo < n - sz; lo += sz + sz) // lo: subarray index
                MergeSort.merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
    }

    @Override
    public <E extends Comparable<? super E>> void sort(E[] array) {

        mergeSort(array);
    }

    @Override
    public SortType sortType() {
        return MERGE;
    }
}
