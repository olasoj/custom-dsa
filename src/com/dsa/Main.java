package com.dsa;

import com.dsa.ops.search.BinarySearch;
import com.dsa.ops.search.QuickSelect;
import com.dsa.ops.search.Search;
import com.dsa.ops.sort.Sort;
import com.dsa.ops.sort.heap.HeapSort;
import com.dsa.util.PerformanceUtil;

import java.util.Arrays;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getSimpleName());
    private static final Sort SORT = new HeapSort();
    private static final Search SEARCH = new BinarySearch();
    private static final Search QUICK_SEARCH = new QuickSelect();

    public static void main(String[] args) {

        Integer[] array = new Integer[]{0, 1, 2, 3, 7, 67, 237, 272, 2228, 27, 292, 2982, 292862};

        int kElement = 12;
        int quickSearch = QUICK_SEARCH.search(array, kElement);
        out.println("Smallest " + kElement + "th  element: " + quickSearch);
        SORT.sort(array);

        out.println(Arrays.toString(array));
        int search = SEARCH.search(array, 272);
        out.println(search);
        out.println(array[search]);

        PerformanceUtil.measureOperationDuration(() -> out.println("Power: " + power(5, 3)));
        PerformanceUtil.measureOperationDuration(() -> out.println("Power2: " + power2(5, 3)));
        PerformanceUtil.measureOperationDuration(() -> out.println("lcf: " + lcf(48, 16)));


        String regex = "([A-Za-z\\d\\-_]+)";
        String gmghjhj = "gm-gh_jhj";

        isAValidDecodeWays(String.valueOf(gmghjhj.charAt(0)));
        out.println("I m test this " + gmghjhj.substring(0, 2));

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(gmghjhj);
        boolean matches = gmghjhj.matches(regex);

        out.println(matches);
        out.println(matcher.matches());
    }

    static boolean isAValidDecodeWays(String s) {
        return true;
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
        int i = 1;

        int tempExp = exp;
        while (tempExp > 0) {

            int multiplier = i;
            boolean isOdd = odd(i);
            if (!isOdd) multiplier = multiplier + 1;
            multiplier = multiplier * multiplier * (isOdd ? base : 1);

            i = multiplier;
            tempExp = tempExp / 2;
        }

        return (i);
    }

    private static boolean odd(int exp) {
        return exp % 2 != 0;
    }

    static int lcf(int n, int m) {
        out.println("m=" + m + "n=" + n);
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