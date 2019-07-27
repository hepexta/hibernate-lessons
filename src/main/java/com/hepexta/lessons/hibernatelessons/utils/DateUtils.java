package com.hepexta.lessons.hibernatelessons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils extends org.apache.commons.lang.time.DateUtils {
    public static Date parseDate(String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

        Date result = null;
        try {
            result = formatter.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
