package vn.me.simpletodo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by taq on 25/9/2016.
 */

public class GlobalConstants {
    public static final String TAG = "SimpleTodo";

    public static final String TASK_FILE = "todo.txt";
    public static final String ITEM = "item";
    public static final String POSITION = "position";
    public static final String CONTENT = "content";
    public static final String TIME = "time";

    public static final int REQUEST_CODE = 12;

    public static String getFormattedDate(int year, int monthOfYear, int dayOfMonth, boolean useLongFormat) {
        String strDate = "";
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, 1);
        if (c.get(Calendar.DAY_OF_MONTH) == dayOfMonth) {
            strDate = "Tomorrow";
        } else {
            String pattern;
            if (useLongFormat) {
                pattern = c.get(Calendar.YEAR) == year ? "MMMM d" : "MMMM d, yyyy";
            } else {
                pattern = "MMM d";
            }
            SimpleDateFormat f = new SimpleDateFormat(pattern);
            c.set(year, monthOfYear, dayOfMonth);
            strDate = f.format(c.getTime());
        }
        return strDate;
    }

    public static String getFormattedTime(int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        SimpleDateFormat f = new SimpleDateFormat("h:mm a");
        return f.format(c.getTime());
    }

}