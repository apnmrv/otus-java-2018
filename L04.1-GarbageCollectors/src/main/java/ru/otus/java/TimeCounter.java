package ru.otus.java;

public class TimeCounter {

    private static TimeCounter tc = null;

    private static long totalGcDuration = 0;
    private static long minorGcDuration = 0;
    private static long majorGcDuration = 0;

    private TimeCounter ()
    {
        return;
    }

    public static TimeCounter getInnstance()
    {
        if (tc == null)
            tc = new TimeCounter();
        return tc;
    }

    public static long getTotalGcDuration() {
        return totalGcDuration;
    }

    public static void addTotalGcDuration(long totalGcDuration) {
        TimeCounter.totalGcDuration += totalGcDuration;
    }

    public static long getMinorGcDuration() {
        return minorGcDuration;
    }

    public static void addMinorGcDuration(long minorGcDuration) {
        TimeCounter.minorGcDuration += minorGcDuration;
    }

    public static long getMajorGcDuration() {
        return majorGcDuration;
    }

    public static void addMajorGcDuration(long majorGcDuration) {
        TimeCounter.majorGcDuration += majorGcDuration;
    }
}
