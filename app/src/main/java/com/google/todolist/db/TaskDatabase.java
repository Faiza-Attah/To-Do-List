package com.google.todolist.db;

/**
 * Created by Faiza on 1/22/2018.
 */

import android.provider.BaseColumns;

public class TaskDatabase {

    public static final String DB_Name = "com.google.todolist.db";
    public static final int DB_Version = 1;

    public class TaskEntry implements BaseColumns {

        public static final String Table = "Tasks";
        public static final String Col_Title ="Title";
    }
}
