package com.dsa.misc.concurrency.util.striped;

import com.dsa.misc.concurrency.util.ConcurrencyUtil;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantStripedLock implements StripedLock<ReentrantLock> {

    private final ReentrantLock[] locks;


    public ReentrantStripedLock() {
        this(DEFAULT_NUMBER_OF_LOCKS);
    }

    public ReentrantStripedLock(int lockCount) {
        locks = new ReentrantLock[ConcurrencyUtil.findNextHighestPowerOfTwo(lockCount)];
        for (int i = 0; i < locks.length; ++i) {
            locks[i] = new ReentrantLock();
        }
    }

    /**
     * Retrieves the given lock allocated to the key.
     *
     * @param key the key of the lock to find
     * @return the lock at the given offset
     */
    @Override
    public ReentrantLock getLock(Object key) {

        int lockNumber = ConcurrencyUtil.selectLock(key, locks.length);
        return locks[lockNumber];
    }

}
