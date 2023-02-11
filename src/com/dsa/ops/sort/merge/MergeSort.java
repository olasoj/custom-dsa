package com.dsa.ops.sort.merge;

import com.dsa.ops.sort.Sort;

import java.util.Arrays;

public interface MergeSort extends Sort {

    static <E extends Comparable<? super E>> void merge(E[] array, int lo, int mid, int hi) {  // Merge array[lo..mid] with array[mid+1..hi].
        E[] aux = Arrays.copyOf(array, array.length); //arrayCopy

        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++)  // Merge back to array[lo..hi].
            if (i > mid) array[k] = aux[j++];
            else if (j > hi) array[k] = aux[i++];
            else if (less(aux[j], aux[i])) array[k] = aux[j++];
            else array[k] = aux[i++];
    }

    private static <E extends Comparable<? super E>> boolean less(E aux, E aux1) {
        return aux.compareTo(aux1) < 0;
    }
}
