package com.denis.mypocket;

import java.text.SimpleDateFormat;

public class DateTimeUtils {
    public static String getFormattedShiftListItemDateTime(long unixTimeStamp) {
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        return outputDateFormat.format(unixTimeStamp);
    }
}
