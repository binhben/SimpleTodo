package vn.me.simpletodo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by binhlt on 27/09/2016.
 */

public class EditItemDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private EditText etContent;
    private EditText etDate;
    private EditText etTime;
    private Calendar storedDate;


    public EditItemDialogFragment() {
        storedDate = Calendar.getInstance();
    }

    public static EditItemDialogFragment newInstance(String content, int position) {
        EditItemDialogFragment fragment = new EditItemDialogFragment();
        Bundle args = new Bundle();
        args.putString(GlobalConstants.CONTENT, content);
        args.putInt(GlobalConstants.POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    public static EditItemDialogFragment newInstance(TodoItem todoItem, int position) {
        EditItemDialogFragment fragment = new EditItemDialogFragment();
        Bundle args = new Bundle();
        args.putInt(GlobalConstants.POSITION, position);
        args.putString(GlobalConstants.CONTENT, todoItem.content);
        args.putLong(GlobalConstants.TIME, todoItem.time);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_edit_item, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDialog().setTitle("Edit item");

        String content = getArguments().getString(GlobalConstants.CONTENT, "");
        final int position = getArguments().getInt(GlobalConstants.POSITION, -1);
        final long time = getArguments().getLong(GlobalConstants.TIME, 0);
        storedDate.setTimeInMillis(time);

        etContent = (EditText) view.findViewById(R.id.etContent);
        etContent.setText(content);
        etContent.requestFocus();

        Button btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Button btnOk = (Button) view.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etContent.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Content must not be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                long newTime = storedDate.getTimeInMillis();
                if (etDate.getText().toString().isEmpty() || etTime.getText().toString().isEmpty()) {
                    newTime = 0;
                }
                EditItemDialogListener listener = (EditItemDialogListener) getActivity();
                listener.onFinishEditDialog(new TodoItem(etContent.getText().toString(), newTime), position);
                dismiss();
            }
        });

        etDate = (EditText) view.findViewById(R.id.etDate);
        ImageView ivDate = (ImageView) view.findViewById(R.id.ivDate);
        ivDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPickerDialog(new DatePickerFragment(), "date_picker_fragment", time);
            }
        });

        etTime = (EditText) view.findViewById(R.id.etTime);
        ImageView ivTime = (ImageView) view.findViewById(R.id.ivTime);
        ivTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPickerDialog(new TimePickerFragment(), "time_picker_fragment", time);
            }
        });

        if (time > 0) {
            showDate(storedDate.get(Calendar.YEAR), storedDate.get(Calendar.MONTH), storedDate.get(Calendar.DAY_OF_MONTH));
            showTime(storedDate.get(Calendar.HOUR_OF_DAY), storedDate.get(Calendar.MINUTE));
        }
    }

    private void showPickerDialog(DialogFragment fragment, String tag, long time) {
        if (fragment == null) {
            return;
        }
        Bundle args = new Bundle();
        args.putLong(GlobalConstants.TIME, time);
        fragment.setArguments(args);
        fragment.setTargetFragment(EditItemDialogFragment.this, 300);
        fragment.show(getFragmentManager(), tag);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        storedDate.set(year, monthOfYear, dayOfMonth);
        showDate(year, monthOfYear, dayOfMonth);
    }

    private void showDate(int year, int monthOfYear, int dayOfMonth) {
        String strDate = "";
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, 1);
        if (c.get(Calendar.DAY_OF_MONTH) == dayOfMonth) {
            strDate = "Tomorrow";
        } else {
            String pattern = c.get(Calendar.YEAR) == year ? "MMMM d" : "MMMM d, yyyy";
            SimpleDateFormat f = new SimpleDateFormat(pattern);
            c.set(year, monthOfYear, dayOfMonth);
            strDate = f.format(c.getTime());
        }
        etDate.setText(strDate);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        storedDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
        storedDate.set(Calendar.MINUTE, minute);
        showTime(hourOfDay, minute);
    }

    private void showTime(int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        SimpleDateFormat f = new SimpleDateFormat("h:mm a");
        etTime.setText(f.format(c.getTime()));
    }
}
