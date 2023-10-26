package com.dsa;

import com.dsa.functions.chapter4.Fibonacci;
import com.dsa.misc.concurrency.util.striped.ReentrantStripedLock;
import com.dsa.misc.concurrency.util.striped.StripedLock;
import com.dsa.ops.search.BinarySearch;
import com.dsa.ops.search.Search;
import com.dsa.ops.sort.Sort;
import com.dsa.ops.sort.quick.QuickSort;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

import static java.lang.System.out;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getSimpleName());
    private static final Sort SORT = new QuickSort();
    private static final Search SEARCH = new BinarySearch();

    private static final StripedLock<ReentrantLock> REENTRANT_STRIPED_LOCK = new ReentrantStripedLock();

    public static void main(String[] args) {

        Integer[] array = new Integer[]{0, 1, 2, 3, 7, 67, 237, 272, 2228, 27, 1, 292, 2982, 292862, 2};
        SORT.sort(array);

        int gs = 14105823;
        int lemonade = 3969332;
        int bras = 818898;

        out.println(Arrays.toString(array));
        out.println(SEARCH.search(array, 272));

        ReentrantLock lockWithOffset = REENTRANT_STRIPED_LOCK.getLock(gs);
        ReentrantLock lockWithOffset2 = REENTRANT_STRIPED_LOCK.getLock(lemonade);
        ReentrantLock lockWithOffset3 = REENTRANT_STRIPED_LOCK.getLock(bras);

        out.println(lockWithOffset);
        out.println(lockWithOffset2);
        out.println(lockWithOffset3);

        Instant now = Instant.now();
        out.println(Fibonacci.olasojFib(5));
        Instant end = Instant.now();
        Duration between = Duration.between(now, end);
        out.println("Operation took: " + between.toMillis());


        now = Instant.now();
        out.println(Fibonacci.fibLong(5));
        end = Instant.now();
        between = Duration.between(now, end);
        out.println("Operation took: " + between.toMillis());

        now = Instant.now();
        out.println(power(5, 3));
        end = Instant.now();
        between = Duration.between(now, end);
        out.println("Operation took: " + between.toMillis());
    }

    static int power(int base, int exp) {
        if (exp == 0) return 1;
        int half = power(base, exp / 2);
        half = half * half;
        if (odd(exp)) half = half * base;
        return half;
    }

    static int power2(int base, int exp) {

        int accumulator = (odd(exp)) ? base : 1;

        while (exp > 0) {
            accumulator = accumulator * exp;
            exp = exp / 2;
        }

        return (accumulator);
    }

    private static boolean odd(int exp) {
        return exp % 2 != 0;
    }

    int LCF(int n, int m) {
        if (m == 0) return n;
        return LCF(m, n % m);
    }

    int LCF2(int n, int m) {
        if (m == 0) return n;

        while (m != 0) {
            m = n % m;
        }
        return LCF(m, n % m);
    }

}