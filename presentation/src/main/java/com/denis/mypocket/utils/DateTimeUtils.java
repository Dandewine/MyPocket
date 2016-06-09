package com.denis.mypocket.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Denis_Zinkovskiy at 6/9/16.
 */

public class DateTimeUtils {

    public static final int UNIX_MULTIPLIER_FACTOR = 1000;

    public static String getTransactionTime(long time) {
        StringBuilder builder = new StringBuilder();
        String template = "hh:mm a";
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(time *  UNIX_MULTIPLIER_FACTOR);

        Date getDate = calendar.getTime();

        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date startDate = calendar.getTime();

        if (getDate.compareTo(startDate) > 0)
            builder.append("Today, ");
        else if (startDate.getDay() - getDate.getDay() == 1)
            builder.append("Yesterday, ");
        else template = "dd.MM, hh:mm a";


        SimpleDateFormat sdf = new SimpleDateFormat(template);
        builder.append(sdf.format(getDate));

        return builder.toString();
    }
}
