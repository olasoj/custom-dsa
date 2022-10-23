package com.dsa.ops.sort.merge.concurrent;

import com.dsa.ops.sort.merge.serial.SerialMergeSort;

import java.util.concurrent.CountedCompleter;

public class MergeSortTask<E extends Comparable<? super E>> extends CountedCompleter<Void> {

    private static final long serialVersionUID = -5183127767439978300L;
    private final E[] data;
    private final int start;
    private final int end;
    private int middle;

    public MergeSortTask(E[] data, int start, int end, MergeSortTask<E> parent) {
        super(parent);

        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    public void compute() {
        if (end - start >= 1024) {
            middle = (end + start) >>> 1;
            MergeSortTask<E> task1 = new MergeSortTask<>(data, start, middle, this);
            MergeSortTask<E> task2 = new MergeSortTask<>(data, middle, end, this);
            addToPendingCount(1);
            task1.fork();
            task2.fork();
        } else {
            new SerialMergeSort<E>().mergeSort(data, start, end);
            tryComplete();
        }
    }

    @Override
    public void onCompletion(CountedCompleter<?> caller) {

        if (middle == 0) {
            return;
        }
        int length = end - start + 1;
        E[] tmp = (E[]) new Object[length];

        int i, j, index;
        i = start;
        j = middle;
        index = 0;

        while ((i < middle) && (j < end)) {
            if (data[i].compareTo(data[j]) <= 0) {
                tmp[index] = data[i];
                i++;
            } else {
                tmp[index] = data[j];
                j++;
            }
            index++;
        }

        while (i < middle) {
            tmp[index] = data[i];
            i++;
            index++;
        }

        while (j < end) {
            tmp[index] = data[j];
            j++;
            index++;
        }

        for (index = 0; index < (end - start); index++) {
            data[index + start] = tmp[index];
        }

    }

}
