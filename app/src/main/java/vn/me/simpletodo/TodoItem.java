package vn.me.simpletodo;

import com.orm.SugarRecord;

/**
 * Created by taq on 26/9/2016.
 */

public class TodoItem extends SugarRecord {

    public String content;

    public TodoItem() {

    }

    public TodoItem(String content) {
        this.content = content;
    }

}
