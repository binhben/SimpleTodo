package vn.me.simpletodo;

import android.app.DatePickerDialog;
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

/**
 * Created by binhlt on 27/09/2016.
 */

public class EditItemDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    private EditText etContent;
    private EditText etDate;
    private EditText etTime;
    private int position;

    public EditItemDialogFragment() {

    }

    public static EditItemDialogFragment newInstance(String content, int position) {
        EditItemDialogFragment fragment = new EditItemDialogFragment();
        Bundle args = new Bundle();
        args.putString(GlobalConstants.CONTENT, content);
        fragment.setArguments(args);
        fragment.position = position;
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

        String content = getArguments().getString(GlobalConstants.CONTENT, "");
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
                EditItemDialogListener listener = (EditItemDialogListener) getActivity();
                listener.onFinishEditDialog(etContent.getText().toString(), position);
                dismiss();
            }
        });

        etDate = (EditText) view.findViewById(R.id.etDate);
        ImageView ivDate = (ImageView) view.findViewById(R.id.ivDate);
        ivDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setTargetFragment(EditItemDialogFragment.this, 300);
        fragment.show(getFragmentManager(), "date_picker_fragment");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        etDate.setText(String.format("%d/%d/%d", dayOfMonth, monthOfYear, year));
    }
}
