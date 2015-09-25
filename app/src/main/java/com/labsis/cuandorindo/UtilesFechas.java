package com.labsis.cuandorindo;

import android.content.Context;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by fedea on 19/09/2015.
 */
public class UtilesFechas {
    private final DateFormat dateFormat;
    private final DateFormat timeFormat;

    private Context context;
    private static UtilesFechas instance;

    public static UtilesFechas getInstance(Context context) {
        if (instance == null) {
            instance = new UtilesFechas(context);
        }
        return instance;
    }

    public UtilesFechas(Context context) {
        this.context = context;

        dateFormat = android.text.format.DateFormat.getDateFormat(context);
        timeFormat = android.text.format.DateFormat.getTimeFormat(context);
    }

    public String dateFormat(Date date) {
        return dateFormat.format(date);
    }

    public String timeFormat(Date date) {
        return timeFormat.format(date);
    }

    public String dateTimeFormat(Date date) {
        return dateFormat(date) + " " + timeFormat(date);
    }
}
