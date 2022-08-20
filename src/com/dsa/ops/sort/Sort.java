package com.dsa.ops.sort;

public interface Sort {

    //TODO : Add support for direction
    <E extends Comparable<? super E>> void sort(E[] array);

    SortType sortType();
}
