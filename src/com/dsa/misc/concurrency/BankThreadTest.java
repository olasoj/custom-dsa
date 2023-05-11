package com.dsa.misc.concurrency;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class BankThreadTest {

    public static final int DELAY = 100;
    public static final int STEPS = 100;
    public static final double MAX_AMOUNT = 1000;
    private static final Logger LOGGER = Logger.getLogger(BankThreadTest.class.getSimpleName());
    private static final Random RANDOM = new Random();
    private static final int ACCOUNT_SIZE = 4;

    public static void main(String[] args) {
        var bank = new Bank(ACCOUNT_SIZE, 10000);

        Instant start = Instant.now();
        init(bank);
        Instant end = Instant.now();

        Duration between = Duration.between(start, end);
        System.out.println("Time elapsed " + between);
    }

    private static void init(Bank bank) {
        List<Runnable> runnableList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {

            int nextInt = RANDOM.nextInt(ACCOUNT_SIZE);
            int otherNextInt = RANDOM.nextInt(ACCOUNT_SIZE);

            while (otherNextInt == nextInt) {
                otherNextInt = RANDOM.nextInt(ACCOUNT_SIZE);
            }

            int finalOtherNextInt = otherNextInt;

            runnableList.add(
                    () -> transferToBankInterruptHandled(bank, nextInt, finalOtherNextInt)
            );
        }


        for (Runnable runnable : runnableList) {
//            runnable.run();
            new Thread(runnable).start();
        }
    }

    private static void transferToBankInterruptHandled(Bank bank, int i, int i2) {
        try {
            transferToBank(bank, i, i2);
        } catch (InterruptedException interruptedException) {
            Thread thread = Thread.currentThread();
            thread.interrupt();
            LOGGER.info(thread.getName() + " is exiting");
        }
    }

    private static void transferToBank(Bank bank, int i2, int i3) throws InterruptedException {
        for (int i = 0; i < STEPS; i++) {
            double amount = MAX_AMOUNT * RANDOM.nextInt(ACCOUNT_SIZE);
            bank.transfer(i2, i3, amount);
            Thread.sleep((DELAY * RANDOM.nextInt(9)));
        }
    }
}
