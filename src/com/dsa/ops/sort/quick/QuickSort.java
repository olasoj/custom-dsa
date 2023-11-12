package com.dsa.ops.sort.quick;

import com.dsa.ops.sort.Sort;
import com.dsa.ops.sort.SortType;
import com.dsa.util.DSUtil;

import static com.dsa.ops.sort.SortType.QUICK;

public class QuickSort implements Sort {
    static <E extends Comparable<? super E>> void quickSort(E[] array, int i, int j) {      // Quicksort
        int pivotIndex = QuickUtil.findPivot(array, i, j); // Pick a pivot
        DSUtil.swap(array, pivotIndex, j);       // Stick pivot at end

        // k will be the first position in the right subarray
        int k = QuickUtil.partition(array, i - 1, j, array[j]);
        DSUtil.swap(array, k, j);

        if ((k - i) > 1) quickSort(array, i, k - 1); //Sort Right Partition
        if ((j - k) > 1) quickSort(array, k + 1, j); //Sort left partition
    }

    @Override
    public <E extends Comparable<? super E>> void sort(E[] array) {
        quickSort(array, 0, array.length - 1);
    }

    @Override
    public SortType sortType() {
        return QUICK;
    }
}
