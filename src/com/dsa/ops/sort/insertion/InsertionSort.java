package com.dsa.ops.sort.insertion;

import com.dsa.ops.sort.Sort;
import com.dsa.ops.sort.SortType;
import com.dsa.util.DSUtil;

import static com.dsa.ops.sort.SortType.INSERTION;

public class InsertionSort implements Sort {
    @Override
    public <E extends Comparable<? super E>> void sort(E[] array) {
        for (int i = 1; i < array.length; i++) // Insert i’th record
            for (int h = i; (h > 0) && (array[h].compareTo(array[h - 1]) < 0); h--)
                DSUtil.swap(array, h, h - 1);
    }

    @Override
    public SortType sortType() {
        return INSERTION;
    }
}
