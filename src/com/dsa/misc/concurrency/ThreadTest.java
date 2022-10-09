package com.dsa.misc.concurrency;

public class ThreadTest {

    public static final int DELAY = 10;
    public static final int STEPS = 100;
    public static final double MAX_AMOUNT = 1000;

    public static void main(String[] args) {
        var bank = new Bank(4, 100000);

        Runnable task1 = () -> transferToBankInterruptHandled(bank, 0, 1);
        Runnable task2 = () -> transferToBankInterruptHandled(bank, 2, 3);

        new Thread(task1).start();
        new Thread(task2).start();
    }

    private static void transferToBankInterruptHandled(Bank bank, int i, int i2) {
        try {
            transferToBank(bank, i, i2);
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
        }
    }

    private static void transferToBank(Bank bank, int i2, int i3) throws InterruptedException {
        for (int i = 0; i < STEPS; i++) {
            double amount = MAX_AMOUNT * Math.random();
            bank.transfer(i2, i3, amount);
            Thread.sleep((int) (DELAY * Math.random()));
        }
    }
}
