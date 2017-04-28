package com.example.android.habittracking.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.habittracking.Data.HabitContract.HabitEntry;

/**
 * Created by nalin on 27-Apr-17.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database.db";

    private static final int DATABASE_VERSION = 1;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_HABIT_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + "(" +
                HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                HabitEntry.COLUMN_PERSON_NAME + " TEXT NOT NULL," +
                HabitEntry.COLUMN_MORNING_HABIT + " TEXT NOT NULL," +
                HabitEntry.COLUMN_EVENING_HABIT + " TEXT NOT NULL," +
                HabitEntry.COLUMN_TOTAL_HABITS + " INTEGER NOT NULL DEFAULT 0);";
        db.execSQL(SQL_CREATE_HABIT_TABLE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
