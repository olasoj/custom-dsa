package com.dsa.misc.concurrency.syncobjects.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarDemo {

    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(3, new BarAction());
        System.out.println("Starting");
        new MyThread(cb, "A");
        new MyThread(cb, "B");
        new MyThread(cb, "C");
    }
}

class MyThread implements Runnable {
    CyclicBarrier cyclicBarrier;
    String name;

    MyThread(CyclicBarrier c, String n) {
        cyclicBarrier = c;
        name = n;
        new Thread(this).start();
    }

    public void run() {
        System.out.println(name);
        try {
            cyclicBarrier.await();
        } catch (BrokenBarrierException exc) {
            exc.printStackTrace();
        } catch (InterruptedException exc) {
            exc.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}

// An object of this class is called when the
// CyclicBarrier ends.
class BarAction implements Runnable {
    public void run() {
        System.out.println("Barrier Reached!");
    }
}
