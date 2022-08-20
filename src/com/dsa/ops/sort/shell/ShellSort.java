package com.dsa.ops.sort.shell;

import com.dsa.ops.sort.Sort;
import com.dsa.ops.sort.SortType;
import com.dsa.util.DSUtil;

import static com.dsa.ops.sort.SortType.SHELL;

public class ShellSort implements Sort {

    // Modified version of Insertion Sort for varying increments
    static <E extends Comparable<? super E>>
    void insertionSort2(E[] array, int start, int incr) {
        for (int i = start + incr; i < array.length; i += incr)
            for (int j = i; (j >= incr) && (array[j].compareTo(array[j - incr]) < 0); j -= incr)
                DSUtil.swap(array, j, j - incr);
    }

    @Override
    public <E extends Comparable<? super E>> void sort(E[] array) {
        for (int i = array.length / 2; i > 2; i /= 2) // For each increment
            for (int j = 0; j < i; j++)             // Sort each sublist
                insertionSort2(array, j, i);
        insertionSort2(array, 0, 1);    // Could call regular inssort here
    }

    @Override
    public SortType sortType() {
        return SHELL;
    }
}
