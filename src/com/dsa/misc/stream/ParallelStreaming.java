package com.dsa.misc.stream;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelStreaming {


    private static final String FORMAT = "%s processing %d%n";

    public static int doubleIt(int n) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignore) {
            Thread.currentThread().interrupt();
        }
        return n * 2;
    }

    public static void main(String[] args) {

        Instant start = Instant.now();
        int total = IntStream.of(3, 1, 4, 1, 5, 9)

                .peek(operand -> System.out.printf(FORMAT,
                        Thread.currentThread().getName(), operand))
                .parallel()
                .map(operand -> {
//                    System.out.printf(FORMAT,
//                            Thread.currentThread().getName(), operand);
                    return doubleIt(operand);
                })
                .sum();
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println("Total of doubles = " + total);
        System.out.println("time = " + duration.toMillis() + " ms");

        // write your code here
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9);
        List<Integer> nums = numbers.parallelStream()
                .map(n -> {
                    System.out.printf(FORMAT,
                            Thread.currentThread().getName(), n);
                    return doubleIt(n);
                })
                .peek(n -> System.out.printf(FORMAT,
                        Thread.currentThread().getName(), n))
//                .sequential()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(nums);
    }
}
