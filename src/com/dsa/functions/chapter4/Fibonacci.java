package com.dsa.functions.chapter4;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

public class Fibonacci {

    public static void main(String[] args) {

        //Fibonacci
        //f(n)=f(n–1)+f(n–2)


        Instant start = Instant.now();
        generateFibonacciSequence();
        Instant end = Instant.now();

        Duration between = Duration.between(start, end);
        System.out.println("Operation took " + between.toMillis() + "Mills");
    }


    public static IntStream stream() {
        return IntStream.generate(new FibonacciSupplier());
    }

    public static void ops() {
        for (int i = 0; i < 3; i++) { // Repeat three times
            stream()
                    //.skip((int) (Math.random() * 900)) // Begin leap
                    .limit(48) // End leap
                    .forEach(j -> System.out.println(j));

        }
    }

    private static void fail(String format) {
        System.out.println(format);
    }

    public static void generateFibonacciSequence() {

        int n = (13);
        for (int i = 1; i <= n; i++) {
            BigInteger fib = fib(i);
            System.out.println("Fibonacci result for " + i + " equals " + fib);
        }
    }

    public static long olasojFib(int n) {

        int counter = 0;
        long result = 1;
        long preResult = 0;
        long tempResult;

        while (counter < n) {

            tempResult = result;
            result = result + preResult;
            preResult = tempResult;

            counter++;
        }

        return preResult;
    }

    public static long fibLong(int n) {
        long[] f = new long[n + 1];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i <= n; i++)
            f[i] = f[i - 1] + f[i - 2];
        return f[n];
    }

    public static int fibonacci(int number) {
        if (number == 0 || number == 1) {
            return number;
        }
        return fibonacci(number - 1) + fibonacci(number - 2);
    }

    public static BigInteger fib(int x) {
        return fib_(BigInteger.ONE, BigInteger.ZERO, BigInteger.valueOf(x)).eval();
    }

    private static TailCall<BigInteger> fib_(BigInteger acc1, BigInteger acc2,
                                             BigInteger x) {
        if (x.equals(BigInteger.ZERO)) {
            return TailCall.ret(BigInteger.ZERO);
        } else if (x.equals(BigInteger.ONE)) {
            return TailCall.ret(acc1.add(acc2));
        } else {
            return TailCall.sus(() -> fib_(acc2, acc1.add(acc2), x.subtract(BigInteger.ONE)));
        }
    }

    private static class FibonacciSupplier implements IntSupplier {

        int current = 1;
        int previous = 0;

        @Override
        public int getAsInt() {
            int result = current;
            current = previous + current;
            previous = result;
            return result;
        }
    }
}


