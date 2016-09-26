package vn.me.simpletodo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

    private Context mContext;
    private ArrayList<TodoItem> mItems;

    public ItemsAdapter(Context context, ArrayList<TodoItem> items) {
        super(context, 0, items);
        this.mContext = context;
        this.mItems = items;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final TodoItem item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.todo_item, parent, false);
        }
        TextView tvContent = (TextView) convertView.findViewById(R.id.tvContent);

        tvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, EditItemActivity.class);
                intent.putExtra(GlobalConstants.ITEM, item.content);
                intent.putExtra(GlobalConstants.POSITION, position);
                ((Activity) mContext).startActivityForResult(intent, GlobalConstants.REQUEST_CODE);
            }
        });

        tvContent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mItems.remove(position);
                notifyDataSetChanged();
                return true;
            }
        });

        tvContent.setText(item.content);
        return convertView;
    }
}
