package com.botvin.util;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Setter
@Getter
public class Time {

    private static Date date = new Date();
    private static SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm:ss a");

    public static String getCurrentDate() {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm:ss a");
        return timeFormatter.format(System.currentTimeMillis());
    }

}