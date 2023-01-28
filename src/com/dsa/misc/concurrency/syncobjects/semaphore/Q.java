package com.dsa.misc.concurrency.syncobjects.semaphore;

import java.util.concurrent.Semaphore;


// An implementation of a producer and consumer
// that use semaphores to control synchronization.
public class Q {
    // Start with consumer semaphore unavailable.
    static Semaphore semCon = new Semaphore(0);
    static Semaphore semProd = new Semaphore(1);
    int n;

    void get() {
        try {
            semCon.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } finally {

            System.out.println("Got: " + n);
            semProd.release();
        }
    }

    void put(int n) {
        try {
            semProd.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        this.n = n;
        System.out.println("Put: " + n);
        semCon.release();
    }
}


class Producer implements Runnable {
    Q q;

    Producer(Q q) {
        this.q = q;
        new Thread(this, "Producer").start();
    }

    public void run() {
        for (int i = 0; i < 20; i++) q.put(i);
    }
}

class Consumer implements Runnable {
    Q q;

    Consumer(Q q) {
        this.q = q;
        new Thread(this, "Consumer").start();
    }

    public void run() {
        for (int i = 0; i < 20; i++) q.get();
    }
}

class ProdCon {
    public static void main(String[] args) {
        Q q = new Q();
        new Consumer(q);
        new Producer(q);
    }
}

