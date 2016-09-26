package vn.me.simpletodo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    private EditText etContent;
    private String content;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        content = getIntent().getStringExtra(GlobalConstants.ITEM);
        position = getIntent().getIntExtra(GlobalConstants.POSITION, -1);
        etContent = (EditText) findViewById(R.id.etContent);
        etContent.setText(content);
        etContent.setSelection(content.length());

        etContent.requestFocus();
    }

    public void onSaveEdit(View view) {
        String newContent = etContent.getText().toString();
        hideSoftKeyboard(etContent);
        Intent data = new Intent();
        data.putExtra(GlobalConstants.ITEM, newContent);
        data.putExtra(GlobalConstants.POSITION, position);
        setResult(RESULT_OK, data);
        finish();
    }

    public void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
