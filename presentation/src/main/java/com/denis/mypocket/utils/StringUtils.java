package com.denis.mypocket.utils;

public class StringUtils {
    private static StringBuilder builder;

    public static String concat(Object... obj){
        if(builder==null)
            builder = new StringBuilder();
        for (Object o: obj)
            builder.append(o.toString());
        return builder.toString();
    }
}
