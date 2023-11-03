package com.dsa.misc.concurrency.util.striped;

import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockWrapper {

    private final ReentrantLock reentrantLock;
    private final Condition condition;

    public ReentrantLockWrapper(ReentrantLock reentrantLock) {
        this.reentrantLock = reentrantLock;
        this.condition = reentrantLock.newCondition();
    }

    public ReentrantLock reentrantLock() {
        return reentrantLock;
    }

    public Condition condition() {
        return condition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof  ReentrantLockWrapper)) return false;
        ReentrantLockWrapper that = (ReentrantLockWrapper) o;
        return Objects.equals(reentrantLock, that.reentrantLock) && Objects.equals(condition, that.condition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reentrantLock, condition);
    }

    @Override
    public String toString() {
        return "ReentrantLockWrapper{" + "reentrantLock=" + reentrantLock +
                ", condition=" + condition +
                '}';
    }
}
