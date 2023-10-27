package com.dsa;

import com.dsa.ops.search.BinarySearch;
import com.dsa.ops.search.Search;
import com.dsa.ops.sort.Sort;
import com.dsa.ops.sort.quick.QuickSort;
import com.dsa.util.PerformanceUtil;

import java.util.Arrays;
import java.util.logging.Logger;

import static java.lang.System.out;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getSimpleName());
    private static final Sort SORT = new QuickSort();
    private static final Search SEARCH = new BinarySearch();

    public static void main(String[] args) {

        Integer[] array = new Integer[]{0, 1, 2, 3, 7, 67, 237, 272, 2228, 27, 1, 292, 2982, 292862, 2};

        SORT.sort(array);
        int search = SEARCH.search(array, 292862);

        out.println(Arrays.toString(array));
        out.println(search);
        out.println(array[search]);

        PerformanceUtil.measureOperationDuration(() -> out.println("Power: " + power(5, 3)));
        PerformanceUtil.measureOperationDuration(() -> out.println("Power2: " + power2(5, 3)));

    }

    static int power(int base, int exp) {
        if (exp == 0) return 1;
        int half = power(base, exp / 2);
        half = half * half;
        out.println("Happening..." + half);
        if (odd(exp)) {
            half = half * base;
        }
        return half;
    }

    static int power2(int base, int exp) {

        int accumulator = 1;

        while (exp > 0) {
            accumulator = accumulator * ((odd(exp)) ? base : 1) * exp * exp / 2;
            out.println("Happening..." + accumulator);
            exp = exp / 2;
        }

        return (accumulator);
    }

    private static boolean odd(int exp) {
        return exp % 2 != 0;
    }

    int lcf(int n, int m) {
        if (m == 0) return n;
        return lcf(m, n % m);
    }

    int lcf2(int n, int m) {
        if (m == 0) return n;

        while (m != 0) {
            m = n % m;
        }
        return 0;
    }

}