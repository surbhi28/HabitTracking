package com.example.android.habittracking.Data;

import android.provider.BaseColumns;

/**
 * Created by nalin on 27-Apr-17.
 */

public final class HabitContract {

    private HabitContract() {
    }

    public static abstract class HabitEntry implements BaseColumns {

        public static final String TABLE_NAME = "habits";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PERSON_NAME = "Name";
        public static final String COLUMN_MORNING_HABIT = "[Morning Habits]";
        public static final String COLUMN_EVENING_HABIT = "[Evening Habits]";
        public static final String COLUMN_TOTAL_HABITS = "[Total Habits]";

    }
}
