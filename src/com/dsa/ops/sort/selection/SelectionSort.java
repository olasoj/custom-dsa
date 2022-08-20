package com.dsa.ops.sort.selection;

import com.dsa.ops.sort.Sort;
import com.dsa.ops.sort.SortType;
import com.dsa.util.DSUtil;

import static com.dsa.ops.sort.SortType.SELECTION;

public class SelectionSort implements Sort {

    @Override
    public <E extends Comparable<? super E>> void sort(E[] array) {
        for (int i = 0; i < array.length - 1; i++) { // Select i’th record
            int lowIndex = i;                // Remember its index

            for (int j = array.length - 1; j > i; j--) // Find the least value
                if (array[j].compareTo(array[lowIndex]) < 0)
                    lowIndex = j;                // Put it in place

            DSUtil.swap(array, i, lowIndex);
        }
    }

    @Override
    public SortType sortType() {
        return SELECTION;
    }
}
