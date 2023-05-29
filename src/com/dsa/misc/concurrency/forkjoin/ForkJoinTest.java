package com.dsa.misc.concurrency.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.function.DoublePredicate;

public class ForkJoinTest {

    public static void main(String[] args) {
        final int SIZE = 10000000;
        var numbers = new double[SIZE];

        for (int i = 0; i < SIZE; i++)
            numbers[i] = Math.random();

        var counter = new FibonacciRecursiveTask(0);
        var pool = new ForkJoinPool();
        pool.invoke(counter);
        System.out.println(counter.join());
    }
}

class Counter extends RecursiveTask<Integer> {
    public static final int THRESHOLD = 1000;
    private final double[] values;
    private final int from;
    private final int to;
    private final DoublePredicate filter;

    public Counter(double[] values, int from, int to, DoublePredicate filter) {
        this.values = values;
        this.from = from;
        this.to = to;
        this.filter = filter;
    }

    @Override
    protected Integer compute() {
        if (to - from < THRESHOLD) {
            int count = 0;
            for (int i = from; i < to; i++) {
                if (filter.test(values[i])) count++;
            }
            return count;
        } else {
            int mid = (from + to) / 2;
            var first = new FibonacciRecursiveTask(from);
            var second = new FibonacciRecursiveTask(mid);
            ForkJoinTask.invokeAll(first, second);
            return first.join() + second.join();
        }
    }
}

