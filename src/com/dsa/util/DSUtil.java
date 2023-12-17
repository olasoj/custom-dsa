package com.dsa.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

public class DSUtil {
    private DSUtil() {
    }

    public static void swap(Object[] a, int replacingElementPosition, int foundElementPosition) {
        Object temp = a[replacingElementPosition];
        a[replacingElementPosition] = a[foundElementPosition];
        a[foundElementPosition] = temp;
    }

    public static <E extends Comparable<? super E>> boolean isLess(E[] a, int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }

    public static int random(int i) {
        return 0;
    }

    public static Queue<Integer> toPQ(int[] arr, Comparator<Integer> integerComparator) {
        return Arrays.stream(arr)
                .boxed()
                .collect(Collectors.toCollection(() -> new PriorityQueue<>(arr.length, integerComparator)));
    }
}
