package gank.sin.me.gk.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sin on 2016/8/10.
 */

public class DateUtils {
    public static final SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

    public static String formatDate(Date date){
        return dateformat.format(date);
    }

    public static Date parseDate(String date) {
        try {
            return dateformat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
