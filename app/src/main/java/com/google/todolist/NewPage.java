package com.google.todolist;

/**
 * Created by Faiza on 1/8/2018.
 */
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.content.DialogInterface;

public class NewPage extends AppCompatActivity{
    private static final String TAG = "NewPage";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.new_page);
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
                                Log.d(TAG, "Task to add: " + task);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
