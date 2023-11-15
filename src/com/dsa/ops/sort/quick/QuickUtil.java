package com.dsa.ops.sort.quick;

import com.dsa.util.DSUtil;

public class QuickUtil {

    private QuickUtil() {
    }

    public static <E extends Comparable<? super E>> int findPivot(E[] A, int i, int j) {
        return (i + j) / 2;
    }

    public static <E extends Comparable<? super E>> int partition(E[] array, int l, int r, E pivot) {
        do {
            // Move bounds inward until they meet
            while (array[++l].compareTo(pivot) < 0) ;
            while ((r != 0) && (array[--r].compareTo(pivot) > 0)) ;
            DSUtil.swap(array, l, r);
        } while (l < r);
        DSUtil.swap(array, l, r);
        return l;      // Return first position in right partition
    }

    public static <E extends Comparable<? super E>> int partitionMax(E[] array, int l, int r, E pivot) {
        do {
            // Move bounds inward until they meet
            while (array[++l].compareTo(pivot) > 0) ;
            while ((r != 0) && (array[--r].compareTo(pivot) < 0)) ;
            DSUtil.swap(array, l, r);
        } while (l < r);
        DSUtil.swap(array, l, r);
        return l;      // Return first position in right partition
    }
}
