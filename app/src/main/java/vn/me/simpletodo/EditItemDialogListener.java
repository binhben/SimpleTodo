package vn.me.simpletodo;

/**
 * Created by taq on 27/9/2016.
 */

public interface EditItemDialogListener {
    void onFinishEditDialog(String inputText, int position);
    void onFinishEditDialog(TodoItem todoItem, int position);
}
