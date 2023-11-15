package com.dsa.util;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Supplier;

import static java.lang.System.out;

public class PerformanceUtil {

    private PerformanceUtil() {

    }

    public static void measureOperationDuration(Runnable runnable) {
        Instant now = start();
        runnable.run();
        end(now);
    }

    public static <T> T measureOperationDuration(Supplier<T> supplier) {
        Instant now = start();
        T t = supplier.get();
        end(now);
        return t;
    }

    private static Instant start() {
        return Instant.now();
    }

    private static void end(Instant now) {
        Instant end = Instant.now();
        Duration between = Duration.between(now, end);
        out.println("Operation took: " + between.toMillis() + " millis");
    }
}
