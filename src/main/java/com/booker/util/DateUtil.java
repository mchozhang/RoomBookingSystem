package com.booker.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtil {
    private static final String patten = "yyyy-MM-dd";

    public static String getDefaultDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(patten);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
        return dtf.format(now) + " - " + dtf.format(tomorrow);
    }

    public static String getToday() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(patten);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static String getTomorrow() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(patten);
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
        return dtf.format(tomorrow);
    }

    public static String parseDateParam(String dateStr) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(patten);
        return dtf.format(DateTimeFormatter.ofPattern(patten).parse(dateStr));
    }

    public static int dayDifference(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patten);
        LocalDate date1 = LocalDate.parse(startDate, formatter);
        LocalDate date2 = LocalDate.parse(endDate, formatter);
        long days = ChronoUnit.DAYS.between(date1, date2);
        return (int)days;
    }
}
