package com.example.android.habittracking;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.habittracking.Data.HabitContract.HabitEntry;
import com.example.android.habittracking.Data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new HabitDbHelper(this);
        insert();
        display();
    }

    public void insert() {

        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_PERSON_NAME, "Surbhi");
        values.put(HabitEntry.COLUMN_MORNING_HABIT, "Chanting,Exercise");
        values.put(HabitEntry.COLUMN_EVENING_HABIT, "Studying");
        values.put(HabitEntry.COLUMN_TOTAL_HABITS, 3);
        // Insert the new row, returning the primary key value of the new row
        db.insert(HabitEntry.TABLE_NAME, null, values);

    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the habits database.
     */
    public Cursor read() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new HabitDbHelper(this);
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {HabitEntry._ID,
                HabitEntry.COLUMN_PERSON_NAME,
                HabitEntry.COLUMN_MORNING_HABIT,
                HabitEntry.COLUMN_EVENING_HABIT,
                HabitEntry.COLUMN_TOTAL_HABITS};
        // Retrieving all the columns of the habit table
        Cursor cursor = db.query(HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
        return cursor;
    }

    public void display() {
        // Display the number of rows in the Cursor (which reflects the number of rows in the
        // habits table in the database).
        Cursor cursor = read();
        TextView displayText = (TextView) findViewById(R.id.text);
        displayText.setText("No of Rows " + cursor.getCount());

        int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
        int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_PERSON_NAME);
        int morningHabitColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_MORNING_HABIT);
        int eveningHabitColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_EVENING_HABIT);
        int totalHabitsColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_TOTAL_HABITS);

        while (cursor.moveToNext()) {
            int currentId = cursor.getInt(idColumnIndex);
            String name = cursor.getString(nameColumnIndex);
            String morningHabit = cursor.getString(morningHabitColumnIndex);
            String eveningHabit = cursor.getString(eveningHabitColumnIndex);
            int totalHabits = cursor.getInt(totalHabitsColumnIndex);

            displayText.append("\n" + currentId + "-" +
                    name + "-" +
                    morningHabit + "-" +
                    eveningHabit + "-" +
                    totalHabits);
        }
        // Always close the cursor when you're done reading from it. This releases all its
        // resources and makes it invalid.
        cursor.close();
    }
}
