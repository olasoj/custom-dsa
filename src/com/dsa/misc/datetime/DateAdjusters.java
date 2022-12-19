package com.dsa.misc.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class DateAdjusters {

    public static void main(String[] args) {
        LocalDate firstTuesday = LocalDate.of(2019, 2, 1)
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY));

        System.out.println("firstTuesday: " + firstTuesday);


        LocalDate today = LocalDate.now();

        TemporalAdjuster nextWorkday = w -> {
            var result = (LocalDate) w;
            do {
                result = result.plusDays(1);
            }
            while (result.getDayOfWeek().getValue() >= 6);
            return result;
        };

        LocalDate backToWork = today.with(nextWorkday);
        System.out.println("backToWork: " + backToWork);

        TemporalAdjuster nextWorkdayNoCast = TemporalAdjusters.ofDateAdjuster(w -> {
            LocalDate result = w; // No cast do
            do {
                result = result.plusDays(1);
            }
            while (result.getDayOfWeek().getValue() >= 6);
            return result;
        });

        backToWork = today.with(nextWorkdayNoCast);
        System.out.println("backToWork: " + backToWork);
    }
}
