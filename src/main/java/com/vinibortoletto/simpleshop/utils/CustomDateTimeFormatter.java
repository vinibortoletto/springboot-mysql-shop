package com.vinibortoletto.simpleshop.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CustomDateTimeFormatter {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static String formatDateTime(Instant datetime) {
        return formatter.format(datetime.atZone(ZoneId.systemDefault()));
    }
}
