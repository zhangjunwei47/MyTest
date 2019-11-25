package com.example.kaola.myapplication.util;

import java.util.Formatter;
import java.util.Locale;

/**
 * @author zhangchao on 2019-09-05.
 */


public class DataFormatUtil {
    private void test() {
        StringBuilder buffer = new StringBuilder();
        Formatter formatter = new Formatter(buffer, Locale.getDefault());
        getStringForTime(buffer, formatter, 1);
    }

    public static String getStringForTime(StringBuilder builder, Formatter formatter, long timeMs) {
        if (timeMs == -1) {
            timeMs = 0;
        }
        long totalSeconds = (timeMs + 500) / 1000;
        long seconds = totalSeconds % 60;
        long minutes = (totalSeconds / 60) % 60;
        long hours = totalSeconds / 3600;
        builder.setLength(0);
        return hours > 0 ? formatter.format("%d:%02d:%02d", hours, minutes, seconds).toString()
                : formatter.format("%02d:%02d", minutes, seconds).toString();
    }
}
