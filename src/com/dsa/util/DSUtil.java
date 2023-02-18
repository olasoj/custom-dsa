package com.dsa.util;

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
}
