package com.dsa.misc.concurrency.util.striped;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof ReentrantLockWrapper)) return false;

        ReentrantLockWrapper otherReentrantLockWrapper = (ReentrantLockWrapper) obj;
        return new EqualsBuilder()
                .append(reentrantLock, otherReentrantLockWrapper.reentrantLock)
                .append(condition, otherReentrantLockWrapper.condition)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(reentrantLock)
                .append(condition)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("reentrantLock", reentrantLock)
                .append("condition", condition)
                .toString();
    }
}
