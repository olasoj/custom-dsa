package com.dsa;

import com.dsa.custom.dictionaries.KVPair;
import com.dsa.custom.queue.AQueue;
import com.dsa.custom.queue.Queue;
import org.apache.commons.lang3.time.DurationFormatUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.SortedMap;
import java.util.TreeMap;

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

        KVPair<Instant, Duration> instantDurationKVPair = computeExpectedRunningTIme(instant.minus(Duration.ofDays(2)), instant.plus(Duration.ofHours(4)));
        KVPair<Instant, Duration> instantDurationKVPair2 = computeExpectedRunningTIme2(instant.minus(Duration.ofDays(2)), instant.plus(Duration.ofHours(4)));

        Instant endTime = instantDurationKVPair.key();
        Duration between = Duration.between(Instant.now(), endTime);

        System.out.println(endTime);
        Duration duration = instantDurationKVPair.value();
        System.out.println(between.toHours());
        System.out.println(between.toMinutesPart());
        System.out.println(between.toSecondsPart());

        String formatDuration = DurationFormatUtils.formatDuration(duration.toMillis(), "dd HH:mm:ss:SS");
        String formatDuration2 = DurationFormatUtils.formatDurationWords(between.toMillis(), true, false);
        System.out.println(formatDuration);
        System.out.println(formatDuration2);

//        AmountFormats.wordBased(d, Locale.getDefault())

    }

    private static KVPair<Instant, Duration> computeExpectedRunningTIme(Instant sessionStartDateTime, Instant expectedSessionEnd) {
        Duration expectedDuration = Duration.between(sessionStartDateTime, expectedSessionEnd);
        Duration runningDuration = Duration.ofSeconds(0);

        SortedMap<Long, Instant> scheduledGraceDates = getScheduledGraceDates(sessionStartDateTime.plus(Duration.ofDays(2)));

        while (!expectedDuration.isNegative() && !expectedDuration.isZero()) {
            Instant atStartOfTheDay = getAtStartOfTheDay(sessionStartDateTime);

            Duration durationLostBetweenStartOfDayAndStartTime = Duration.between(atStartOfTheDay, sessionStartDateTime);
            Duration defaultDurationDiffer = Duration.ofDays(1).minus(durationLostBetweenStartOfDayAndStartTime);

            if (!scheduledGraceDates.containsKey(sessionStartDateTime.toEpochMilli())) {
                Duration tempExpectedDuration = expectedDuration;
                expectedDuration = expectedDuration.minus(defaultDurationDiffer);

                if (expectedDuration.isZero() || expectedDuration.isNegative())
                    defaultDurationDiffer = tempExpectedDuration;
            }

            runningDuration = runningDuration.plus(defaultDurationDiffer);
            sessionStartDateTime = sessionStartDateTime.plus(defaultDurationDiffer);
        }

        return new KVPair<>(sessionStartDateTime, runningDuration);
    }

    private static KVPair<Instant, Duration> computeExpectedRunningTIme2(Instant sessionStartDateTime, Instant expectedSessionEnd) {
        Duration runningDuration = Duration.ofSeconds(0);
        Duration expectedDuration = Duration.between(sessionStartDateTime, expectedSessionEnd);

        SortedMap<Long, Instant> scheduledGraceDates = getScheduledGraceDates(sessionStartDateTime.plus(Duration.ofDays(2)));

        if (!expectedDuration.isNegative() && !expectedDuration.isZero()) {
            Instant atStartOfTheDay = getAtStartOfTheDay(sessionStartDateTime);

            Duration durationLostBetweenStartOfDayAndStartTime = Duration.between(atStartOfTheDay, sessionStartDateTime);
            Duration defaultDurationDiffer = Duration.ofDays(1).minus(durationLostBetweenStartOfDayAndStartTime);

            if (!scheduledGraceDates.containsKey(sessionStartDateTime.toEpochMilli())) {
                Duration tempExpectedDuration = expectedDuration;
                expectedDuration = expectedDuration.minus(defaultDurationDiffer);

                if (expectedDuration.isZero() || expectedDuration.isNegative())
                    defaultDurationDiffer = tempExpectedDuration;
            }

            runningDuration = runningDuration.plus(defaultDurationDiffer);
            sessionStartDateTime = sessionStartDateTime.plus(defaultDurationDiffer);

        }
        computeExpectedRunningTIme2(sessionStartDateTime, expectedSessionEnd);

        return new KVPair<>(sessionStartDateTime, runningDuration);
    }

    private static SortedMap<Long, Instant> getScheduledGraceDates(Instant smapleInstant) {
        SortedMap<Long, Instant> scheduledGraceDates = new TreeMap<>();

        Instant sample = smapleInstant.minus(Duration.ofDays(2));
        scheduledGraceDates.put(sample.toEpochMilli(), sample);

        Instant sample2 = smapleInstant.minus(Duration.ofDays(1));
        scheduledGraceDates.put(sample2.toEpochMilli(), sample2);

        scheduledGraceDates.put(smapleInstant.toEpochMilli(), smapleInstant);

        Instant sample3 = smapleInstant.plus(Duration.ofDays(1));
        scheduledGraceDates.put(sample3.toEpochMilli(), sample3);

        Instant sample4 = smapleInstant.plus(Duration.ofDays(3));
        scheduledGraceDates.put(sample4.toEpochMilli(), sample4);
        return scheduledGraceDates;
    }

    private static Instant getAtStartOfTheDay(Instant instant) {
        ZonedDateTime sessionStartZonedDateTime = instant.atZone(ZoneId.systemDefault());
        ZonedDateTime sessionStartZonedDateTimeAtStartOfTheDay = sessionStartZonedDateTime.toLocalDate().atStartOfDay(sessionStartZonedDateTime.getZone());
        return sessionStartZonedDateTimeAtStartOfTheDay.toInstant();
    }


}
