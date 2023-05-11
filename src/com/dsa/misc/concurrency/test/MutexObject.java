package com.dsa.misc.concurrency.test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class MutexObject {

    private static final Logger LOGGER = Logger.getLogger(MutexObject.class.getSimpleName());

    private static final ConcurrentMap<Integer, Double> concurrentMap = new ConcurrentHashMap<>();
    private final ReentrantLock lock;
    private final Condition sufficientFunds;

    public MutexObject() {


        this.lock = new ReentrantLock();
        this.sufficientFunds = lock.newCondition();
    }

    public boolean transfer(int from, double amount) {

        lock.lock();
        boolean await = true;

        try {

            String name = Thread.currentThread().getName();
            LOGGER.info(name + " Started of transaction with the id   \n" + from);

            while (concurrentMap.containsKey(from)) {
                if (!await) {
                    return false;
                }

                LOGGER.info(name + " going to sleep");
                await = sufficientFunds.await(1500, TimeUnit.MILLISECONDS);
            }

            concurrentMap.putIfAbsent(from, amount);
            LOGGER.info(name + " ended the transaction with the id  \n" + from);
            return true;
        } catch (InterruptedException e) {
            Thread thread = Thread.currentThread();
            thread.interrupt();
            System.out.println(thread.getName() + " operation Failed");
            return false;
        } finally {
            sufficientFunds.signalAll();
            lock.unlock();
        }
    }

    public void releaseLock(int from) {

        try {
            concurrentMap.remove(from);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                sufficientFunds.signalAll();
            }
        }
    }


}
