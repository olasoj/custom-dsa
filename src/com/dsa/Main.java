package com.dsa;

import com.dsa.misc.datetime.ComputeRunningTImeResult;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Main {

    private static final String SIMPLE_DATE_TIME_FORMAT_N = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final DateTimeFormatter OVERDRAFT_DATE_TIME_DATA_STORE_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .append(DateTimeFormatter.ofPattern(Main.SIMPLE_DATE_TIME_FORMAT_N))
            .parseDefaulting(ChronoField.NANO_OF_DAY, 0)
            .toFormatter()
            .withZone(ZoneId.systemDefault());

    public static void main(String[] args) {
        // write your code here
        Instant startTime = Instant.now();
        Instant lastFeeProcessedTimestampInstant =
//                startTime;
                startTime.plus(Duration.ofDays(2).plus(Duration.ofHours(17)));


        Duration between = Duration.between(startTime, lastFeeProcessedTimestampInstant);
        long backOffDuration = between.dividedBy(Duration.ofHours(24)) + 1;
        System.out.println(backOffDuration);

        Instant nextFeeProcessedTimestampInstant = startTime.plus(Duration.ofDays(backOffDuration));

        System.out.println(startTime);
        System.out.println(OVERDRAFT_DATE_TIME_DATA_STORE_FORMATTER.format(startTime));
        System.out.println(OVERDRAFT_DATE_TIME_DATA_STORE_FORMATTER.format(nextFeeProcessedTimestampInstant));

        System.out.println(OVERDRAFT_DATE_TIME_DATA_STORE_FORMATTER.format(startTime.plus(Duration.ofDays(1))));
        System.out.println(OVERDRAFT_DATE_TIME_DATA_STORE_FORMATTER.format(nextFeeProcessedTimestampInstant.plus(Duration.ofDays(1).minus(Duration.ofMillis(1)))));


        Clock clock = Clock.systemUTC();
        Instant now = Instant.now();
        System.out.println(now.toEpochMilli() == clock.millis());
        System.out.println(now.toEpochMilli() + " Instant millis <-> Clock millis " + clock.millis());

        Duration duration = Duration.ofSeconds(172800);
        System.out.println(duration.toSeconds());

//        Instant.parse()

        Main main = new Main();

        Instant instant = Instant.now();
        Instant sessionStartInstant = instant.minus(Duration.ofDays(2));
        ComputeRunningTImeResult computeRunningTImeResult = main.computeRunningTIme(sessionStartInstant, instant, ZoneId.systemDefault().getId());
        System.out.println(computeRunningTImeResult);

        Duration graceDayDuration = computeRunningTImeResult.graceDayDuration();
        System.out.println(graceDayDuration);
        Duration between1 = Duration.between(sessionStartInstant, instant);
        System.out.println(between1);

        System.out.println(between1.minus(graceDayDuration));

    }

    private static ZonedDateTime toZonedDateTime(Instant sessionStartInstant, String zoneId) {
        ZoneId zone = ZoneId.of(zoneId, ZoneId.SHORT_IDS);
        return sessionStartInstant.atZone(zone);
    }

    private ComputeRunningTImeResult computeRunningTIme(Instant sessionStartInstant, Instant expectedSessionEndInstant, String zoneId) {
        Instant tempSessionStartInstant = sessionStartInstant;

        Duration expectedDuration = Duration.between(sessionStartInstant, expectedSessionEndInstant);
        Duration runningDuration = Duration.ofSeconds(0);
        Duration graceDuration = Duration.ofSeconds(0);

        //Iteration may occur up to 100 times, so a sequential algorithm is adequate.
        while (!expectedDuration.isNegative() && !expectedDuration.isZero()) {

            Instant atStartOfTheDay = getAtStartOfTheDay(sessionStartInstant, zoneId);
            boolean isStartOfTheDayAGraceDay = isStartOfTheDayAGraceDay(atStartOfTheDay);

            //Duration left for the day
            Duration durationLeftForTheDay = getDurationLeftForTheDay(sessionStartInstant, atStartOfTheDay);

            if (!isStartOfTheDayAGraceDay) {//If it not a grace day deplete the expected duration
                Duration tempExpectedDuration = expectedDuration;
                expectedDuration = expectedDuration.minus(durationLeftForTheDay);

                if (expectedDuration.isZero() || expectedDuration.isNegative())
                    durationLeftForTheDay = tempExpectedDuration;
            }

            if (isStartOfTheDayAGraceDay) { // If it is grace day add the duration
                graceDuration = graceDuration.plus(durationLeftForTheDay);
            }

            runningDuration = runningDuration.plus(durationLeftForTheDay);
            sessionStartInstant = sessionStartInstant.plus(durationLeftForTheDay);
        }

        return ComputeRunningTImeResult.builder()
                .startInstant(tempSessionStartInstant)
                .endInstant(sessionStartInstant)
                .duration(runningDuration)
                .graceDuration(graceDuration)
                .build();
    }

    private boolean isStartOfTheDayAGraceDay(Instant atStartOfTheDay) {
        LocalDateTime atStartOfDay = LocalDate.now().atStartOfDay();
        ZonedDateTime zonedDateTime = toZonedDateTime(atStartOfTheDay, ZoneId.systemDefault().getId());

        return atStartOfDay.atZone(ZoneId.systemDefault())
                .equals(zonedDateTime);
    }

    private Instant getAtStartOfTheDay(Instant instant, String zoneId) {
        ZonedDateTime sessionStartZonedDateTime = toZonedDateTime(instant, zoneId);
        ZonedDateTime sessionStartZonedDateTimeAtStartOfTheDay = sessionStartZonedDateTime.toLocalDate().atStartOfDay(sessionStartZonedDateTime.getZone());
        return sessionStartZonedDateTimeAtStartOfTheDay.toInstant();
    }

    private Duration getDurationLeftForTheDay(Instant sessionStartInstant, Instant atStartOfTheDay) {
        Duration durationLostBetweenStartOfDayAndStartTime = Duration.between(atStartOfTheDay, sessionStartInstant);
        return Duration.ofDays(1).minus(durationLostBetweenStartOfDayAndStartTime);
    }

}
//Start Time: 2023-01-13T010:37:47.564407Z
//Next pay at 2023-01-13 10:37:47.564 of usage between  2023-01-13 10:37:47.564  and 2023-01-14 10:37:47.563
//Next pay at 22023-01-14 10:37:47.564 of usage between  2023-01-14 10:37:47.564  and 2023-01-15 10:37:47.563