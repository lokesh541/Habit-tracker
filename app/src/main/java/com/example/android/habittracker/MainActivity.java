package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    HabitDbHelper mHelper;
    SQLiteDatabase db;
    ContentValues values = new ContentValues();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHelper = new HabitDbHelper(this);
        db = mHelper.getWritableDatabase();
        // all the method calls for insert,delete, retrieve, update methods
        insertData("running", "14");
        insertData("walking", "15");

        getData();

        deleteAllData();

        updateData("reading", "17");


    }

    //method to insert data

    public boolean insertData(String habit, String days) {


        values.put(HabitTrackerContract.HabitEntry.COL_HABIT_TITLE, habit);
        values.put(HabitTrackerContract.HabitEntry.COL_HABIT_DAYS, days);

        long rowId = db.insert(HabitTrackerContract.HabitEntry.TABLE, null, values);

        return rowId != -1;

    }


    ///method to retrieve data

    public Cursor getData() {
        Cursor res = db.rawQuery("SELECT * FROM " + HabitTrackerContract.HabitEntry.TABLE, null);
        return res;
    }

    //method to delete all data

    public Integer deleteAllData() {

        return db.delete(HabitTrackerContract.HabitEntry.TABLE, null, null);

    }


    // method to update data

    public boolean updateData(String habit, String days) {
        values.put(HabitTrackerContract.HabitEntry.COL_HABIT_DAYS,days);
        db.update(HabitTrackerContract.HabitEntry.TABLE,
                  values,
                  HabitTrackerContract.HabitEntry.COL_HABIT_TITLE + "= ?",
                  new String[] {habit});
        return true;
    }
}
