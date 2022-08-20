package com.dsa.ops.sort.bubble;

import com.dsa.ops.sort.Sort;
import com.dsa.ops.sort.SortType;
import com.dsa.util.DSUtil;

import static com.dsa.ops.sort.SortType.BUBBLE;

public class BubbleSort implements Sort {

    public <E extends Comparable<? super E>> void sort(E[] A) {
        for (int i = 0; i < A.length - 1; i++) // Bubble up i’th record
            for (int j = A.length - 1; j > i; j--)
                if ((A[j].compareTo(A[j - 1]) < 0))
                    DSUtil.swap(A, j, j - 1);
    }

    @Override
    public SortType sortType() {
        return BUBBLE;
    }
}
