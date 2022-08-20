package com.dsa.ops.sort.merge;

import com.dsa.ops.sort.Sort;
import com.dsa.ops.sort.SortType;

import static com.dsa.ops.sort.SortType.MERGE;

public class MergeSort implements Sort {
    static <E extends Comparable<? super E>> void mergesort(E[] array, E[] temp, int l, int r) {
        int mid = (l + r) / 2;
        if (l == r) return;
        mergesort(array, temp, l, mid);
        mergesort(array, temp, mid + 1, r); // Mergesort second half

        // Copy subarray to temp
        if (r + 1 - l >= 0) System.arraycopy(array, l, temp, l, r + 1 - l);

        // Do the merge operation back to array
        int i1 = l;
        int i2 = mid + 1;
        for (int curr = l; curr <= r; curr++) {
            if (i1 == mid + 1)              // Left sublist exhausted
                array[curr] = temp[i2++];
            else if (i2 > r)              // Right sublist exhausted
                array[curr] = temp[i1++];
            else if (temp[i1].compareTo(temp[i2]) < 0) // Get smaller
                array[curr] = temp[i1++];
            else array[curr] = temp[i2++];
        }
    }

    @Override
    public <E extends Comparable<? super E>> void sort(E[] array) {
        //TODO: Revise this.
        mergesort(array, array, array.length, 0);

    }

    @Override
    public SortType sortType() {
        return MERGE;
    }
}
