package com.dsa.misc.concurrency;

import java.util.Arrays;
import java.util.concurrent.locks.*;

public class Bank {

    private final double[] accounts;
    private final Lock bankLock;
    private final Condition sufficientFunds;

    private final ReadWriteLock readWriteLock;
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

        readWriteLock = new ReentrantReadWriteLock();
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
        try {
            System.out.println("Start of transaction  \n");

            while (accounts[from] < amount) sufficientFunds.await(); //Re-examine this

            accounts[from] -= amount;
            System.out.printf("%s transferred ", Thread.currentThread().getName());
            System.out.printf("%10.2f from Account %d to Account %d%n", amount, from, to);
            accounts[to] += amount;

            System.out.printf("Total Balance: %10.2f %n%n", getTotalBalance());
            System.out.println("End of transaction  \n");
            sufficientFunds.signalAll();
        } finally {
            writeLock.unlock();
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
