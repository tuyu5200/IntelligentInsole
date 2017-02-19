package com.octave.intelligentinsole.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Paosin Von Scarlet on 2017/2/15.
 */

public class TimeUtils {
    private static final int SECONDS_IN_DAY = 60 * 60 * 24;
    public static final long MILLIS_IN_DAY = 1000L * SECONDS_IN_DAY;

    public static boolean isSameDayOfMillis(final long ms1, final long ms2) {
        final long interval = ms1 - ms2;
        return interval < MILLIS_IN_DAY
                && interval > -1L * MILLIS_IN_DAY
                && toDay(ms1) == toDay(ms2);
    }

    private static long toDay(long millis) {
        return (millis + TimeZone.getDefault().getOffset(millis)) / MILLIS_IN_DAY;
    }

    public static String millisToYMDHMS(long millis) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());
        format.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String out = format.format(millis);
        return out;
    }

    public static String millisToYMD(long millis) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        format.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String out = format.format(millis);
        return out;
    }

    public static String millisToHMS(long millis) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss",Locale.getDefault());
        format.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        return format.format(new Date(millis));
    }

    public static long hourToMillis(float hour) {
        return (long)(hour * 3600000);
    }

    public static long minToMillis(float min) {
        return (long)(min * 60000);
    }
}
