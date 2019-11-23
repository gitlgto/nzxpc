package com.nzxpc.mem.core.infrastructure;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
    private final static SimpleDateFormat GMT_DATE_FORMAT = new SimpleDateFormat("dd MMM yyyy HH:mm:ss 'GMT'");

    /**
     * 转成GMT格式<br>
     * e.g: 19 Aug 2017 03:58:09 GMT
     */
    public static String toGMTString(Date date) {
        return GMT_DATE_FORMAT.format(date);
    }
}
