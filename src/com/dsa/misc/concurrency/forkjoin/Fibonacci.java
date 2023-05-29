package com.dsa.misc.concurrency.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class Fibonacci {

    //This is not an optimal solution given the number of tasks that would be generated.
    public static void main(String[] args) {
        final int SIZE = 12;

        var counter = new FibonacciRecursiveTask(SIZE);
        var pool = new ForkJoinPool();
        pool.invoke(counter);
        System.out.println(counter.join());
    }
}

class FibonacciRecursiveTask extends RecursiveTask<Integer> {
    public static final int THRESHOLD = 10;
    private final int from;

    public FibonacciRecursiveTask(int from) {
        this.from = from;
    }

    @Override
    protected Integer compute() {
        if (from <= THRESHOLD) {
            //Sequential Algorithm
            return fib(from);
        } else {
            int fib1 = from - 1;
            int fib2 = from - 2;

            var first = new FibonacciRecursiveTask(fib1);
            var second = new FibonacciRecursiveTask(fib2);

            ForkJoinTask.invokeAll(first, second);
            return first.join() + second.join();
        }
    }

    int fib(int n) {
        if (n <= 1) return n;
        return fib(n - 1) + fib(n - 2);
    }
}

