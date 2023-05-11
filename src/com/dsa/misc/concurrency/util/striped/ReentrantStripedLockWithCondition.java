package com.dsa.misc.concurrency.util.striped;


import com.dsa.misc.concurrency.util.ConcurrencyUtil;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantStripedLockWithCondition implements StripedLockWithCondition<ReentrantLock> {

    private static final int DEFAULT_NUMBER_OF_LOCKS = 128;

    private final ReentrantLockWrapper[] locks;

    public ReentrantStripedLockWithCondition() {
        this(DEFAULT_NUMBER_OF_LOCKS);
    }

    public ReentrantStripedLockWithCondition(int lockCount) {
        locks = new ReentrantLockWrapper[ConcurrencyUtil.findNextHighestPowerOfTwo(lockCount)];

        for (int i = 0; i < locks.length; ++i) {
            locks[i] = new ReentrantLockWrapper(new ReentrantLock());
        }
    }

    @Override
    public ReentrantLock getLock(Object key) {
        int lockNumber = ConcurrencyUtil.selectLock(key, locks.length);
        return locks[lockNumber].reentrantLock();
    }

    @Override
    public Condition getCondition(Object key) {
        int lockNumber = ConcurrencyUtil.selectLock(key, locks.length);
        return locks[lockNumber].condition();
    }
}
