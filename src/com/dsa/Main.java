package com.dsa;

import com.dsa.custom.queue.AQueue;
import com.dsa.custom.queue.Queue;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.function.LongFunction;

public class Main {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .append(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm zzzz"))
            .parseDefaulting(ChronoField.NANO_OF_DAY, 0)
            .toFormatter()
            .withZone(ZoneId.systemDefault());

    public static void main(String[] args) {
        // write your code here
        Queue<Integer> integerLStack = new AQueue<>();

        integerLStack.dequeue();
        integerLStack.enqueue(90);
        integerLStack.enqueue(30);
        integerLStack.dequeue();
        integerLStack.enqueue(30);
        integerLStack.enqueue(90);
        integerLStack.dequeue();

        Instant instant = Instant.now();
        Clock clock = Clock.fixed(instant, ZoneId.of("America/New_York"));
        OffsetDateTime offsetDateTime = OffsetDateTime.now(clock);
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.systemDefault());
        String format = DATE_TIME_FORMATTER.format(instant);

        System.out.println(offsetDateTime); // 2019-01-03T19:10:16.806-05:00
        System.out.println(zonedDateTime);  // 2019-01-03T19:10:16.806-05:00[America/New_York]
        System.out.println(instant);
        System.out.println(format);
        System.out.println();

        OffsetDateTime offsetPlusSixMonths = offsetDateTime.plusMonths(6);
        ZonedDateTime zonedDateTimePlusSixMonths = zonedDateTime.plusMonths(6);

        System.out.println(offsetPlusSixMonths); // 2019-07-03T19:10:16.806-05:00
        System.out.println(zonedDateTimePlusSixMonths); // 2019-07-03T19:10:16.806-04:00[America/New_York]
        System.out.println(zonedDateTimePlusSixMonths.toEpochSecond() - offsetPlusSixMonths.toEpochSecond()); // -3600

        System.out.println();
        System.out.println(zonedDateTimePlusSixMonths.toLocalDateTime()); // 2019-07-03T19:10:16.806
        System.out.println(offsetPlusSixMonths.toLocalDateTime()); // 2019-07-03T19:10:16.806
    }

    public long measureSumPerf(LongFunction<Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }

}
