package vn.me.simpletodo;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;

import java.util.Calendar;

/**
 * Created by taq on 29/9/2016.
 */

public class TimePickerFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int hour, minute;
        long time = getArguments().getLong(GlobalConstants.TIME, 0);
        if (time > 0) {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(time);
            hour = c.get(Calendar.HOUR_OF_DAY);
            minute = c.get(Calendar.MINUTE);
        } else {
            hour = 8;
            minute = 0;
        }

        TimePickerDialog.OnTimeSetListener listener = (TimePickerDialog.OnTimeSetListener) getTargetFragment();

        return new TimePickerDialog(getActivity(), listener, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }
}
