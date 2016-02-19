package com.denis.mypocket;

import java.text.SimpleDateFormat;

public class DateTimeUtils {
    public static String convertMillis(long unixTimeStamp) {
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yy");
        return outputDateFormat.format(unixTimeStamp);
    }
}
