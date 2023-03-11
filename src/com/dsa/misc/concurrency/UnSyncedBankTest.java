package com.dsa.misc.concurrency;

import java.util.Random;

public class UnSyncedBankTest {

    public static final int N_ACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;
    public static final double MAX_AMOUNT = 1000;
    public static final int DELAY = 1000000;
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        //Disable the lock in the bank class to see this work
        var bank = new Bank(N_ACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < N_ACCOUNTS; i++) {
            int fromAccount = i;
            Runnable r = () -> {
                try {
                    while (true) {

                        int toAccount = (bank.size() * RANDOM.nextInt(4));
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((DELAY * RANDOM.nextInt(9)));
                    }
                } catch (InterruptedException interruptedException) {
                    Thread.currentThread().interrupt();
                    interruptedException.printStackTrace();
                }
            };
            var t = new Thread(r);
            t.start();
        }
    }
}
