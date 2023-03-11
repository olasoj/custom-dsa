package com.dsa;

import com.dsa.ops.sort.Sort;
import com.dsa.ops.sort.heap.HeapSort;

import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getSimpleName());
    private static final Sort SORT = new HeapSort();

    public static void main(String[] args) {

        Integer[] array = new Integer[]{0, 1, 2, 3, 7, 67, 237, 272, 2228, 27, 1, 292, 2982, 292862, 2};
        SORT.sort(array);

    }

}