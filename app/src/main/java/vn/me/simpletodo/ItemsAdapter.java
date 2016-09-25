package vn.me.simpletodo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by taq on 26/9/2016.
 */

public class ItemsAdapter extends ArrayAdapter<TodoItem> {

    public ItemsAdapter(Context context, ArrayList<TodoItem> items) {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TodoItem item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.todo_item, parent, false);
        }
        TextView tvContent = (TextView) convertView.findViewById(R.id.tvContent);
        tvContent.setText(item.content);
        return convertView;
    }
}
