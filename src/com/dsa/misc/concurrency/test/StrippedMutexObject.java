package com.dsa.misc.concurrency.test;

import com.dsa.misc.concurrency.util.ConcurrencyUtil;

public class StrippedMutexObject {

    private static final int DEFAULT_NUMBER_OF_LOCKS = 128;

    private final MutexObject[] locks;

    public StrippedMutexObject() {
        this(DEFAULT_NUMBER_OF_LOCKS);
    }

    public StrippedMutexObject(int lockCount) {
        locks = new MutexObject[ConcurrencyUtil.findNextHighestPowerOfTwo(lockCount)];

        for (int i = 0; i < locks.length; ++i) {
            locks[i] = new MutexObject();
        }
    }

    public MutexObject getLock(Object key) {

        int lockNumber = ConcurrencyUtil.selectLock(key, locks.length);
        return locks[lockNumber];
    }
}
