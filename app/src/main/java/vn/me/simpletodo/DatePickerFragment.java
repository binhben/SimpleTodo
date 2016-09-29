package vn.me.simpletodo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

/**
 * Created by taq on 28/9/2016.
 */

public class DatePickerFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        long time = getArguments().getLong(GlobalConstants.TIME, 0);
        if (time > 0) {
            c.setTimeInMillis(time);
        } else {
            c.add(Calendar.DATE, 1);
        }
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener listener = (DatePickerDialog.OnDateSetListener) getTargetFragment();

        return new DatePickerDialog(getActivity(), listener, year, month, day);
    }
}
