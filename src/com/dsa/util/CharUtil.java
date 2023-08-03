package com.dsa.util;

public class CharUtil {

    private CharUtil() {
    }

    public static char[] toCharArray(int number) {
        char[] arr = new char[(int) (Math.log10(number) + 1)];

        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = (char) ('0' + (number % 10));
            number /= 10;
        }
        return arr;
    }
}
