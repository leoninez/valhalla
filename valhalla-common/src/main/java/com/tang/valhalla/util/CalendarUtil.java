package com.tang.valhalla.util;


import java.util.Calendar;

public class CalendarUtil {
    public static Calendar nextDay(int year, int month, int day) {
        Calendar calendar = ofDay(year, month, day);

        calendar.add(Calendar.DATE, 1);

        return calendar;
    }

    public static Calendar nextDay(Calendar calendar) {
        return toDayOfGap(calendar, 1);
    }

    public static Calendar previousDay(Calendar calendar) {
        Calendar ins = dup(calendar);
        ins.add(Calendar.DATE, -1);

        return ins;
    }

    public static Calendar toDayOfGap(Calendar calendar, int dayGap) {
        Calendar ins = dup(calendar);
        ins.add(Calendar.DATE, dayGap);

        return ins;
    }

    public static Calendar toMonthOfGap(Calendar calendar, int gap) {
        Calendar ins = dup(calendar);
        ins.add(Calendar.MONTH, gap);

        return ins;
    }

    public static Calendar toYearOfGap(Calendar calendar, int gap) {
        Calendar ins = dup(calendar);
        ins.add(Calendar.YEAR, gap);

        return ins;
    }


    public static Calendar toDayOfGap(Calendar calendar, int unit, int gap) {
        Calendar ins = dup(calendar);
        ins.add(unit, gap);

        return ins;
    }


    public static Calendar dup(Calendar calendar) {
        int ya = calendar.get(Calendar.YEAR);
        int ma = calendar.get(Calendar.MONTH) + 1;
        int da = calendar.get(Calendar.DATE);

        return ofDay(ya, ma, da);
    }

    public static Calendar today() {
        return Calendar.getInstance();
    }

    public static boolean isDayBefore(Calendar a, Calendar b) {
        return toDayValue(a) < toDayValue(b);
    }

    public static boolean isSameDay(Calendar a, Calendar b) {
        return toDayValue(a) == toDayValue(b);
    }

    public static boolean isDiffDay(Calendar a, Calendar b) {
        return !isSameDay(a, b);
    }

    public static Calendar ofDay(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, 0, 0, 0);
        return calendar;
    }

    public static Calendar ofTimestamp(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        return calendar;
    }

    public static int toDayValue(Calendar calendar) {
        int ya = calendar.get(Calendar.YEAR);
        int ma = calendar.get(Calendar.MONTH);
        int da = calendar.get(Calendar.DATE);

        return ya * 512 + ma * 32 + da;
    }

    public static String toDayPath(Calendar calendar) {
        return String.format("%04d/%02d/%02d",
            calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE));
    }

    public static String toDayKey(Calendar calendar) {
        return String.format("%04d-%02d-%02d",
            calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE));
    }

    public static String toDayUnderKey(Calendar calendar) {
        return String.format("%04d_%02d_%02d",
            calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE));
    }

    public static String toMonthUnderKey(Calendar calendar) {
        return String.format("%04d_%02d",
            calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1);
    }

    public static String toYearUnderKey(Calendar calendar) {
        return String.format("%04d",
            calendar.get(Calendar.YEAR));
    }

    public static int yearOf(Calendar calendar) {
        return calendar.get(Calendar.YEAR);
    }

    public static int monthOf(Calendar calendar) {
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int dayOf(Calendar calendar) {
        return calendar.get(Calendar.DATE);
    }

    public static int currentHourInDay() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    public static void main(String[] args) {
        Calendar today = CalendarUtil.ofDay(2018, 2, 28);
        System.out.println(CalendarUtil.toDayKey(dup(today)));
    }

    public static Calendar convert(Long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        return calendar;
    }
}
