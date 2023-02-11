package com.dsa.ops.sort.merge;

import com.dsa.ops.sort.SortType;

import java.util.Arrays;

import static com.dsa.ops.sort.SortType.MERGE;

public class LegacyMergeSort implements MergeSort {
    private static final int THRESHOLD = 0;

    static <E extends Comparable<? super E>> void mergesort(E[] array, E[] temp, int length, int r) {
        int mid = (length + r) / 2;
        if (length == r) return;
        mergesort(array, temp, length, mid);
        mergesort(array, temp, mid + 1, r); // Mergesort second half

        // Copy subarray to temp
        if (r + 1 - length >= 0) System.arraycopy(array, length, temp, length, r + 1 - length);

        // Do the merge operation back to array
        int i1 = length;
        int i2 = mid + 1;
        for (int curr = length; curr <= r; curr++) {
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

        E[] arrayCopy = Arrays.copyOf(array, array.length);
        mergesort(array, arrayCopy, array.length, 0);
    }

    @Override
    public SortType sortType() {
        return MERGE;
    }
}
