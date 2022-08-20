package com.dsa.ops.sort.quick;

import com.dsa.ops.sort.Sort;
import com.dsa.ops.sort.SortType;
import com.dsa.util.DSUtil;

import static com.dsa.ops.sort.SortType.QUICK;

public class QuickSort implements Sort {
    static <E extends Comparable<? super E>> void quickSort(E[] array, int i, int j) {      // Quicksort
        int pivotIndex = findPivot(array, i, j); // Pick a pivot
        DSUtil.swap(array, pivotIndex, j);       // Stick pivot at end

        // k will be the first position in the right subarray
        int k = partition(array, i - 1, j, array[j]);
        DSUtil.swap(array, k, j);

        if ((k - i) > 1) quickSort(array, i, k - 1); //Sort Right Partition
        if ((j - k) > 1) quickSort(array, k + 1, j); //Sort left partition
    }

    static <E extends Comparable<? super E>> int findPivot(E[] A, int i, int j) {
        return (i + j) / 2;
    }

    static <E extends Comparable<? super E>> int partition(E[] array, int l, int r, E pivot) {
        do {                 // Move bounds inward until they meet
            while (array[++l].compareTo(pivot) < 0) ;
            while ((r != 0) && (array[--r].compareTo(pivot) > 0)) ;
            DSUtil.swap(array, l, r);
        } while (l < r);
        DSUtil.swap(array, l, r);
        return l;      // Return first position in right partition
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
