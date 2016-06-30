package com.example.android.habittracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lokesh on 29/6/16.
 */
public class HabitDbHelper extends SQLiteOpenHelper {


    public HabitDbHelper(Context context) {

        super(context, HabitTrackerContract.DB_NAME, null, HabitTrackerContract.DB_VERSION);

    }


    // TODO: 29/6/16  add

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + HabitTrackerContract.HabitEntry.TABLE + " ( " +
                HabitTrackerContract.HabitEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                HabitTrackerContract.HabitEntry.COL_HABIT_TITLE + " TEXT NOT NULL," +
                HabitTrackerContract.HabitEntry.COL_HABIT_DAYS + " " +
                "INTEGER NOT NULL );";

        db.execSQL(createTable);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + HabitTrackerContract.HabitEntry.TABLE);
        onCreate(db);

    }
}
