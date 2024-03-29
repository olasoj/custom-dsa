package com.dsa.misc.concurrency.util.spin;


import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

import static com.dsa.misc.concurrency.util.spin.JDKSpecific.unsafe;

/**
 * A spin lock.  Such locks are designed to only be held for a <em>very</em> short time - for example, long enough to compare and
 * swap two fields.  The lock may degrade to yielding the thread after a certain number of spins if it is held for too long.
 * <p>
 * Spin locks do not support conditions, and they do not support timed waiting.  Normally only the uninterruptible {@code lock},
 * {@code tryLock}, and {@code unlock} methods should be used to control the lock.
 */
public class SpinLock implements ExtendedLock {
    private static final long ownerOffset;
    private static final int spinLimit;

    static {
        try {
            ownerOffset = unsafe.objectFieldOffset(SpinLock.class.getDeclaredField("owner"));
        } catch (NoSuchFieldException e) {
            throw new NoSuchFieldError(e.getMessage());
        }
        spinLimit = AccessController.doPrivileged(
                (PrivilegedAction<Integer>) () -> Integer.valueOf(
                        System.getProperty("jboss.spin-lock.limit", ProcessorInfo.availableProcessors() == 1 ? "0" : "5000")
                )
        ).intValue();
    }

    @SuppressWarnings("unused")
    private volatile Thread owner;

    private int level;

    /**
     * Construct a new instance.
     */
    public SpinLock() {
    }

    /**
     * Determine if this spin lock is held.  Useful for assertions.
     *
     * @return {@code true} if the lock is held by any thread, {@code false} otherwise
     */
    public boolean isLocked() {
        return owner != null;
    }

    /**
     * Determine if this spin lock is held by the calling thread.  Useful for assertions.
     *
     * @return {@code true} if the lock is held by the calling thread, {@code false} otherwise
     */
    public boolean isHeldByCurrentThread() {
        return owner == Thread.currentThread();
    }

    /**
     * Determine if this lock is fair.
     *
     * @return {@code true}; the lock is fair
     */
    public boolean isFair() {
        return true;
    }

    /**
     * Acquire the lock by spinning until it is held.
     */
    public void lock() {
        Thread ownerLocal;
        int spins = 0;
        for (; ; ) {
            ownerLocal = this.owner;
            if (ownerLocal == Thread.currentThread()) {
                level++;
                return;
            } else if (ownerLocal == null && unsafe.compareAndSwapObject(this, ownerOffset, null, Thread.currentThread())) {
                level = 1;
                return;
            } else if (spins >= spinLimit) {
                Thread.yield();
            } else {
                JDKSpecific.onSpinWait();
                spins++;
            }
        }
    }

    /**
     * Acquire the lock by spinning until it is held or the thread is interrupted.
     *
     * @throws InterruptedException if the thread is interrupted before the lock can be acquired
     */
    public void lockInterruptibly() throws InterruptedException {
        Thread owner;
        int spins = 0;
        for (; ; ) {
            if (Thread.interrupted()) throw new InterruptedException();
            owner = this.owner;
            if (owner == Thread.currentThread()) {
                level++;
                return;
            } else if (owner == null && unsafe.compareAndSwapObject(this, ownerOffset, null, Thread.currentThread())) {
                level = 1;
                return;
            } else if (spins >= spinLimit) {
                Thread.yield();
            } else {
                JDKSpecific.onSpinWait();
                spins++;
            }
        }
    }

    /**
     * Try to acquire the lock, returning immediately whether the lock was acquired.
     *
     * @return {@code true} if the lock was acquired, {@code false} otherwise
     */
    public boolean tryLock() {
        Thread ownerLocal = this.owner;
        if (ownerLocal == Thread.currentThread()) {
            level++;
            return true;
        } else if (ownerLocal == null && unsafe.compareAndSwapObject(this, ownerOffset, null, Thread.currentThread())) {
            level = 1;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Release the lock.
     *
     * @throws IllegalMonitorStateException if the lock is not held by the current thread
     */
    public void unlock() {
        Thread ownerLocal = this.owner;
        if (ownerLocal == Thread.currentThread()) {
            if (--level == 0) this.owner = null;
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    /**
     * Unsupported.
     *
     * @param time ignored
     * @param unit ignored
     * @return nothing
     * @throws UnsupportedOperationException always
     */
    public boolean tryLock(final long time, final TimeUnit unit) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * Unsupported.
     *
     * @return nothing
     * @throws UnsupportedOperationException always
     */
    public Condition newCondition() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}

