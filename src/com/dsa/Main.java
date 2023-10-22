package com.dsa;

import com.dsa.misc.concurrency.util.striped.ReentrantStripedLock;
import com.dsa.misc.concurrency.util.striped.StripedLock;
import com.dsa.ops.sort.Sort;
import com.dsa.ops.sort.heap.HeapSort;

import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

import static java.lang.System.out;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getSimpleName());
    private static final Sort SORT = new HeapSort();

    private static final StripedLock<ReentrantLock> REENTRANT_STRIPED_LOCK = new ReentrantStripedLock();

    public static void main(String[] args) {

        Integer[] array = new Integer[]{0, 1, 2, 3, 7, 67, 237, 272, 2228, 27, 1, 292, 2982, 292862, 2};
        SORT.sort(array);

        int gs = 14105823;
        int lemonade = 3969332;
        int bras = 818898;

        ReentrantLock lockWithOffset = REENTRANT_STRIPED_LOCK.getLock(gs);
        ReentrantLock lockWithOffset2 = REENTRANT_STRIPED_LOCK.getLock(lemonade);
        ReentrantLock lockWithOffset3 = REENTRANT_STRIPED_LOCK.getLock(bras);

        out.println(lockWithOffset);
        out.println(lockWithOffset2);
        out.println(lockWithOffset3);

    }

}