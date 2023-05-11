package com.dsa.misc.concurrency.test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.LongAccumulator;

public class StripLockTest {

    private static final LongAccumulator longAccumulator = new LongAccumulator(Long::sum, 0);
    private static final Random RANDOM = new Random();

    public static void main(String[] args) throws InterruptedException {

        StrippedMutexObject strippedMutexObject = new StrippedMutexObject();
        init(strippedMutexObject);

        Thread.sleep(25000);
        System.out.println("Time elapsed " + Duration.ofMillis(longAccumulator.get()));
    }

    private static void init(StrippedMutexObject strippedMutexObject) {
        List<Runnable> runnableList = runnableList(strippedMutexObject);


        for (Runnable runnable : runnableList) {
//            runnable.run();
            new Thread(runnable).start();
        }
    }

    private static List<Runnable> runnableList(StrippedMutexObject strippedMutexObject) {
        List<Runnable> runnableList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            runnableList.add(() -> {

                        for (int j = 0; j < 40; j++) {

                            try {
                                Instant start = Instant.now();
                                int nextInt = //141 + i;
                                        RANDOM.nextInt(100);
                                MutexObject lock = strippedMutexObject.getLock(nextInt);
                                boolean transfer = lock.transfer(nextInt, 90);

                                if (transfer) {
                                    System.out.println(nextInt + " successfully acquired lock");
                                } else {
                                    System.out.println(nextInt + " failed to acquire lock");
                                }

                                Thread.sleep(300);
                                lock.releaseLock(nextInt);

                                Instant end = Instant.now();

                                Duration between = Duration.between(start, end);
                                longAccumulator.accumulate(between.toMillis());
                            } catch (InterruptedException e) {
                                Thread thread = Thread.currentThread();
                                thread.interrupt();
                                System.out.println(thread.getName() + " operation Failed");
                            }
                        }


                    }
            );

        }
        return runnableList;
    }


}
