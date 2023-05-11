package com.dsa.misc.concurrency.util.striped;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public interface StripedLockWithCondition<T extends Lock> extends StripedLock<T> {

    /**
     * Retrieves the given condition at a provided key.
     *
     * @param key the key of the condition to find
     * @return the condition at the given key
     */
    Condition getCondition(Object key);

}
