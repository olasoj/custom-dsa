package com.dsa.misc.concurrency.util.striped;

import java.util.concurrent.locks.Lock;

public interface StripedLock<T extends Lock> {

    int DEFAULT_NUMBER_OF_LOCKS = 128;


    /**
     * Retrieves the given lock at a provided key.
     *
     * @param key the key of the lock to find
     * @return the lock at the given key
     */
    T getLock(Object key);

}
