package com.google.todolist.db;

/**
 * Created by Faiza on 1/22/2018.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper (Context context){
        super(context, TaskDatabase.DB_Name, null, TaskDatabase.DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String createTable = "CREATE TABLE " + TaskDatabase.TaskEntry.Table + "(" + TaskDatabase.TaskEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + TaskDatabase.TaskEntry.Col_Title + " TEXT NOT NULL);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TaskDatabase.TaskEntry.Table);
        onCreate(db);
    }
}
