package com.dsa.ops.sort;

public class RadixSort implements Sort {
    @Override
    public <E extends Comparable<? super E>> void sort(E[] array) {

    }

    @Override
    public SortType sortType() {
        return SortType.RADIX;
    }
}
