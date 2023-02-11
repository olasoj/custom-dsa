package com.dsa;

import com.dsa.misc.datetime.ComputeRunningTImeResult;
import com.dsa.ops.sort.Sort;
import com.dsa.ops.sort.merge.BottomUpMergeSort;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final String SIMPLE_DATE_TIME_FORMAT_N = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final Sort SORT = new BottomUpMergeSort();

    public static final DateTimeFormatter OVERDRAFT_DATE_TIME_DATA_STORE_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .append(DateTimeFormatter.ofPattern(Main.SIMPLE_DATE_TIME_FORMAT_N))
            .parseDefaulting(ChronoField.NANO_OF_DAY, 0)
            .toFormatter()
            .withZone(ZoneId.systemDefault());

    public static void main(String[] args) {

        Integer[] array = new Integer[]{0, 1, 2, 3, 7, 67, 237, 272, 2228, 27, 1, 292, 2982, 292862, 2};
        SORT.sort(array);
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

        String str = "? Olasoji Ige Olubusayo ?";
        String strR = "Olasoji Ige Olubusayo";
        String str2 = "Olubusayo Ige Olasoji";

        String[] split = str
                .replace("?", "")
                .trim()
                .split(" ");

        System.out.println("The number char found when splitting: " + split.length);
        String[] strRSplit = strR
//                .replaceAll("\\?", "")
                .split(" ");

        int count = 0;

        List<String> strSplitList = Arrays.asList(split);

        for (String strRR : strRSplit)
            if (strSplitList.contains(strRR)) count++;

        System.out.println("Name Count Match " + count);

        for (String st : split)
            System.out.println(st);

//        System.out.println(str.equals(strR));

        String toString = new StringBuilder(str)
                .append(" ")
                .toString();

        System.out.println(toString.trim().equalsIgnoreCase(str));

//There are three names returned by BVN
        //There are two names returned by Paga
        //We want check if the user appended his/her middle name with his first name or last name
        //Meaning the user would have more two names assign to that name

//        We want to try strict match
        // Try and see the number of names exist tallies with the number of BVN names
        //If the number are equal then all names must match else if 2 then either first name must match
//         If that does not work try our normal algo flow


//        BVN is standard the last name must match.
//        If the last name matches the first name must also match


        String digits = "0123456789";
        String num = "210";

//        digits.
        StringBuilder stringBuilder = new StringBuilder(digits);

        char[] chars = digits.toCharArray();
        char[] chars2 = num.toCharArray();

        int counter = 0;
        int pointer = 0;

        for (int i = 0; i < chars2.length; i++) {

            for (int j = 0; j < chars.length; j++) {
                counter++;
                if (chars2[i] == chars[j]) {
                    break;
                }
            }
        }

        System.out.println("Counter value" + (counter - (chars2.length - 1)));

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