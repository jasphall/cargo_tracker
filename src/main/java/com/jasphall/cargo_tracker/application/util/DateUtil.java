package com.jasphall.cargo_tracker.application.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";

    public static Date toDate(String date) {
        return toDate(date, "00:00.00.000");
    }

    public static Date toDate(String date, String time) {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(date + " " + time);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
