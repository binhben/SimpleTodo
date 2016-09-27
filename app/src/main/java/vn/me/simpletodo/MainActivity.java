package vn.me.simpletodo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements EditItemDialogListener {

    private ArrayList<TodoItem> items;
    private ItemsAdapter itemsAdapter;
    private ListView lvItems;
    private EditText etNewItem;
    private int editingPos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readItemsFromDB();
        lvItems = (ListView) findViewById(R.id.lvItems);
        etNewItem = (EditText) findViewById(R.id.etNewItem);
        itemsAdapter = new ItemsAdapter(this, items);
        lvItems.setAdapter(itemsAdapter);
        lvItems.requestFocus();
    }

    public void onAddItem(View view) {
        String itemText = etNewItem.getText().toString();
        if (!itemText.isEmpty()) {
            TodoItem item = new TodoItem(itemText);
            item.save();
            itemsAdapter.add(item);
            etNewItem.setText("");
            hideSoftKeyboard(etNewItem);
        } else {
            Toast.makeText(this, "New item is empty", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GlobalConstants.REQUEST_CODE && resultCode == RESULT_OK) {
            int position = data.getIntExtra(GlobalConstants.POSITION, -1);
            if (position != -1) {
                String newContent = data.getStringExtra(GlobalConstants.ITEM);
                TodoItem item = items.get(position);
                item.content = newContent;
                item.save();
                itemsAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Edit successfully", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void readItemsFromDB() {
        items = new ArrayList<>();
        items.addAll(TodoItem.listAll(TodoItem.class));
    }

    @Override
    public void onFinishEditDialog(String newContent, int position) {
        TodoItem item = items.get(position);
        if (!newContent.equals("") && !item.content.equals(newContent)) {
            item.content = newContent;
            item.save();
            itemsAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Edit successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
