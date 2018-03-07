package com.google.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.idescout.sql.SqlScoutServer;

/**
 * Created by Faiza on 1/11/2018.
 */

public class MainActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SqlScoutServer.create(this, getPackageName());
        Intent intent = new Intent(this, NewPage.class);
        startActivity(intent);
        finish();
    }
}
