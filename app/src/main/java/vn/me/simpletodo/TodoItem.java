package vn.me.simpletodo;

import com.orm.SugarRecord;

/**
 * Created by taq on 26/9/2016.
 */

public class TodoItem extends SugarRecord {

    public String content;
    public boolean finished;
    public long time;

    public TodoItem() {

    }

    public TodoItem(String content) {
        this(content, 0);
    }

    public TodoItem(String content, long time) {
        this.content = content;
        this.time = time;
        this.finished = false;
    }
}
