package com.dsa.util;

public class DSUtil {
    public static void swap(Object[] a, int replacingElementPosition, int foundElementPosition) {
        Object temp = a[replacingElementPosition];
        a[replacingElementPosition] = a[foundElementPosition];
        a[foundElementPosition] = temp;
    }
}
