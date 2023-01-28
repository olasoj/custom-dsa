package com.dsa.misc.concurrency.syncobjects.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CDLDemo {

    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(5);
        System.out.println("Starting");
        new MyThread(cdl);

        try {

            System.out.println("Counting down..");
            cdl.await();
            System.out.println("Count down done");
        } catch (InterruptedException exc) {
            Thread.currentThread().interrupt();
            exc.printStackTrace();
        }
        System.out.println("Done");
    }
}

class MyThread implements Runnable {
    CountDownLatch latch;

    MyThread(CountDownLatch c) {
        latch = c;
        new Thread(this).start();
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            latch.countDown(); // decrement count
        }
    }
}