package com.example.alex.sqlitebasetutorial;

/**
 * Created by Alex on 31.05.2018.
 */

public class Table {
    public static final String TABLE_NAME = "dataItems";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_TIME_BEGIN = "timeBegin";
    public static final String COLUMN_TIME_END = "timeEnd";
    public static final String COLUMN_CHECKED_DAYS = "checkedDays";
    public static final String COLUMN_IS_ACTIVATED = "isActivated";
    public static final String COLUMN_IS_VIBRATION_ALLOWED = "isVibration";

   // public static final int BOOLEAN_TRUE = 1;
   // public static final int BOOLEAN_FALSE = 0;

    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_DESCRIPTION + " TEXT, "
                    + COLUMN_TIME_BEGIN + " TEXT, "
                    + COLUMN_TIME_END + " TEXT, "
                    + COLUMN_CHECKED_DAYS + " TEXT, "
                    + COLUMN_IS_VIBRATION_ALLOWED + " INTEGER, "
                    + COLUMN_IS_ACTIVATED + " INTEGER)";

    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
    public static final String TABLE_SELECT = "SELECT * FROM " + TABLE_NAME;
}
