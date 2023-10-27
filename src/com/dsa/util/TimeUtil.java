package com.dsa.util;


import java.time.*;
import java.util.Date;
import java.util.Objects;

public class TimeUtil {

    private TimeUtil() {
    }

//    public static Instant toInstant(DateTime dateTime) {
//
//        Assert.notNull(dateTime, "DateTime cannot be null");
//        return Instant.ofEpochMilli(dateTime.getMillis());
//    }

    public static Instant toInstant(LocalDateTime localDateTime) {
        Assert.notNull(localDateTime, "LocalDateTime cannot be null");
        return localDateTime.toInstant(OffsetDateTime.now().getOffset());
    }

    public static Instant toInstant(LocalDate localDate) {

        Assert.notNull(localDate, "LocalDate cannot be null");
        LocalDateTime atStartOfDay = localDate.atStartOfDay();
        return toInstant(atStartOfDay);
    }

    public static Instant atStartOfDayInstant() {
        LocalDateTime atStartOfDay = LocalDate.now().atStartOfDay();
        return toInstant(atStartOfDay);
    }

    public static ZonedDateTime toZonedDateTime(Instant sessionStartInstant, String zoneId) {
        ZoneId zone = ZoneId.of(zoneId, ZoneId.SHORT_IDS);
        return sessionStartInstant.atZone(zone);
    }

    public static ZonedDateTime toZonedDateTime(Instant sessionStartInstant, ZoneId zoneId) {
        return sessionStartInstant.atZone(zoneId);
    }

    public static Instant getLastDayInstantOfTheCurrentMonth() {
        LocalDate lastDayOfTheMonth = getLastDayOfTheCurrentMonth();
        return toInstant(lastDayOfTheMonth);
    }

    public static LocalDate getLastDayOfTheCurrentMonth() {
        LocalDate localDate = LocalDate.now();
        int lengthOfTheMonth = localDate.getMonth()
                .length(localDate.isLeapYear());

        return localDate.withDayOfMonth(lengthOfTheMonth);
    }

    public static LocalDate getFirstDayOfTheMonthFromTheCurrentMonth(int months) {
        return getLastDayOfTheCurrentMonth()
                .minus(Period.ofMonths(months))
                .withDayOfMonth(1);
    }

    public static Instant toInstant(Date date) {
        return toInstant(date, true);
    }

    public static Instant toInstantLenient(Date date) {
        return toInstant(date, false);
    }

    public static Instant toInstant(Date date, boolean required) {
        if (required)
            Assert.notNull(date, "Date cannot be null");

        if (date instanceof java.sql.Date dateLocal) {
            LocalDate localDate = dateLocal.toLocalDate();
            return toInstant(localDate);
        }

        return (Objects.isNull(date)) ? null : date.toInstant();
    }

    public static LocalDate toLocalDate(Date date) {
        Assert.notNull(date, "Date cannot be null");

        if (date instanceof java.sql.Date dateLocal) {
            return dateLocal.toLocalDate();
        }

        Instant instant = date.toInstant();
        ZonedDateTime zonedDateTime = toZonedDateTime(instant, ZoneId.systemDefault());
        return zonedDateTime.toLocalDate();
    }

}
