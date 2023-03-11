package com.dsa.misc.concurrency;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;
import java.util.logging.Logger;

public class Bank {
    private static final Logger LOGGER = Logger.getLogger(Bank.class.getSimpleName());

    private final double[] accounts;
    private final Lock bankLock;
    private final Condition sufficientFunds;

    private final Lock readLock;
    private final Lock writeLock;

    /**
     * Constructs the bank.
     *
     * @param n              the number of accounts
     * @param initialBalance the initial balance for each account
     */
    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
        bankLock = new ReentrantLock();

        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readLock = readWriteLock.readLock();
        writeLock = readWriteLock.writeLock();

        sufficientFunds = writeLock.newCondition();
    }

    /**
     * Transfers money from one account to another. * @param from the account to transfer from
     *
     * @param to     the account to transfer to
     * @param amount the amount to transfer
     */
    public void transfer(int from, int to, double amount) throws InterruptedException {
        writeLock.lock();

        boolean await = true;

        try {
            LOGGER.info("Start of transaction  \n");

            //Re-examine this
            awaitSufficientBalance(from, amount, await);

            accounts[from] -= amount;
            String initiator = String.format("%s transferred ", Thread.currentThread().getName());
            LOGGER.info(initiator);

            String transaction = String.format("%10.2f from Account %d to Account %d%n", amount, from, to);
            LOGGER.info(transaction);
            accounts[to] += amount;

            String balance = String.format("Total Balance: %10.2f %n%n", getTotalBalance());
            LOGGER.info(balance);
            LOGGER.info("End of transaction  \n");
        } finally {
            sufficientFunds.signalAll();
            writeLock.unlock();
        }
    }

    private void awaitSufficientBalance(int from, double amount, boolean await) throws InterruptedException {
        while (accounts[from] < amount) {
            if (!await) throw new InterruptedException();
            LOGGER.info(Thread.currentThread().getName() + " going to sleep");
            await = sufficientFunds.await(15000, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * Gets the sum of all account balances. * @return the total balance
     */
    public double getTotalBalance() {
        writeLock.lock();

        try {
            double sum = 0;
            for (double a : accounts) sum += a;
            return sum;
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Gets the number of accounts in the bank. * @return the number of accounts
     */
    public int size() {
        readLock.lock();
        try {
            return accounts.length;
        } finally {
            readLock.unlock();
        }
    }
}
