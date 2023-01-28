package com.dsa.misc.concurrency.syncobjects.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        new UseString(exchanger);
        new MakeString(exchanger);
    }
}


// A Thread that constructs a string.
class MakeString implements Runnable {
    Exchanger<String> ex;
    String str;

    MakeString(Exchanger<String> c) {
        ex = c;
        str = "";
        new Thread(this).start();
    }

    public void run() {
        char ch = 'A';
        for (int i = 0; i < 3; i++) {
            // Fill Buffer
            for (int j = 0; j < 5; j++)
                str += ch++;
            try {
                // Exchange a full buffer for an empty one.
                str = ex.exchange(str);
            } catch (InterruptedException exc) {
                exc.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}

// A Thread that uses a string.
class UseString implements Runnable {
    Exchanger<String> ex;
    String str;

    UseString(Exchanger<String> c) {
        ex = c;
        new Thread(this).start();
    }

    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                // Exchange an empty buffer for a full one.
                str = ex.exchange("");
                System.out.println("Got: " + str);
            } catch (InterruptedException exc) {
                exc.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}

