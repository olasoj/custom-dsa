package com.dsa.misc.concurrency;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

    private final double[] accounts;
    private final Lock bankLock;
    private final Condition sufficientFunds;

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
        sufficientFunds = bankLock.newCondition();
    }

    /**
     * Transfers money from one account to another. * @param from the account to transfer from
     *
     * @param to     the account to transfer to
     * @param amount the amount to transfer
     */
    public void transfer(int from, int to, double amount) throws InterruptedException {
        bankLock.lock();
        try {
            while (accounts[from] < amount) sufficientFunds.await(); //Re-examine this

            System.out.println(Thread.currentThread() + "\n");

            accounts[from] -= amount;
            System.out.println(Thread.currentThread() + "  ------Transferred --------- \n");
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;

            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
            System.out.println(Thread.currentThread() + "\n");
            System.out.println("------End of transaction --------- \n");
            sufficientFunds.signalAll();
        } finally {
            bankLock.unlock();
        }
    }

    /**
     * Gets the sum of all account balances. * @return the total balance
     */
    public double getTotalBalance() {
        bankLock.lock();

        try {
            double sum = 0;
            for (double a : accounts) sum += a;
            return sum;
        } finally {
            bankLock.unlock();
        }
    }

    /**
     * Gets the number of accounts in the bank. * @return the number of accounts
     */
    public int size() {
        return accounts.length;
    }
}
