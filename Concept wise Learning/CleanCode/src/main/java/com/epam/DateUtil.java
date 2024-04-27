package com.epam;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static void increment(final Date date, final boolean up) {
        Calendar calendar = getCalendar();
        incrementDate(date, up, calendar);
        setTime(calendar ,0 ,0 ,0 ,0);
        date.setTime(calendar.getTime().getTime());
    }

    private static void incrementDate(Date date, boolean up, Calendar calendar) {
        calendar.setTime(date);
        calendar.add(Calendar.DATE, up ? 1 : -1);
    }

    private static Calendar getCalendar() {
        return Calendar.getInstance();
    }

    private static void setTime(Calendar calendar , int hour , int min , int sec ,int milliSec) {
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, sec);
        calendar.set(Calendar.MILLISECOND, milliSec);
    }

    public static Date create(int year, int month, int day) {
        Calendar calendar = getCalendar();
        setDate(year, month, day, calendar);
        return calendar.getTime();
    }

    private static void setDate(int year, int month, int day, Calendar calendar) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
    }

    public static void main(String[] args) {
        Date date = new Date();
        increment(date, false);
        System.out.println(date);

        System.out.println(DateUtil.create(2014, 10, 10));
    }

}
