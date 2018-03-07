package com.google.todolist;

/**
 * Created by Faiza on 1/8/2018.
 */
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.content.DialogInterface;
import android.widget.ListView;
import android.widget.TextView;

import com.google.todolist.db.DatabaseHelper;
import com.google.todolist.db.TaskDatabase;

import java.util.ArrayList;

public class NewPage extends AppCompatActivity{

    private static final String TAG = "NewPage";
    private DatabaseHelper mHelper;
    private ListView taskView;
    private ArrayAdapter<String> mAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.new_page);

        mHelper = new DatabaseHelper(this);
        taskView = (ListView) findViewById(R.id.todo_list);

        updateUI();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.add_task:
                final EditText editTask = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add a new task")
                        //.setMessage("What do you want to do next?")
                        .setView(editTask)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which){
                                String task = String.valueOf(editTask.getText());
                                SQLiteDatabase db = mHelper.getWritableDatabase();
                                ContentValues values = new ContentValues();
                                values.put(TaskDatabase.TaskEntry.Col_Title, task);
                                db.insertWithOnConflict(TaskDatabase.TaskEntry.Table, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                                db.close();
                                updateUI();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void FinishTask(View view){
        View parent = (View) view.getParent();
        TextView taskText = (TextView) parent.findViewById(R.id.item_text);
        String task = String.valueOf(taskText.getText());
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(TaskDatabase.TaskEntry.Table, TaskDatabase.TaskEntry.Col_Title + " = ?", new String[]{task});
        db.close();
        updateUI();
    }

    private void updateUI() {
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(TaskDatabase.TaskEntry.Table, new String[]{TaskDatabase.TaskEntry._ID, TaskDatabase.TaskEntry.Col_Title}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(TaskDatabase.TaskEntry.Col_Title);
            Log.d(TAG, "TASK: " + cursor.getString(idx));
        }
        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.item_text, taskList);
            taskView.setAdapter(mAdapter);
        }else {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }

        cursor.close();
        db.close();
    }
}
