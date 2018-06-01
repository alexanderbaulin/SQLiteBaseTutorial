package com.example.alex.sqlitebasetutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Date;
import java.util.Arrays;
import java.util.LinkedList;


public class Base extends SQLiteOpenHelper {
    private static final String DATABASE_NAME  = "data.db";
    private static final int DATABASE_VERSION = 1;

    public Base(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Table.TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Table.TABLE_DROP);
        db.execSQL(Table.TABLE_CREATE);
    }

    private SQLiteDatabase getWritableBase() {
        return this.getWritableDatabase();
    }

    void create() {
        getWritableBase().execSQL(
            Table.TABLE_CREATE
        );
    }

    void drop() {
        getWritableBase().execSQL(
            Table.TABLE_DROP
        );
    }

    public long insert(Data data) {
        SQLiteDatabase dp = getWritableDatabase();
        ContentValues cv = getValues(data);
        long key = dp.insert(Table.TABLE_NAME, null, cv);
        dp.close();
        return key;
    }

    private ContentValues getValues(Data data) {
        ContentValues cv = new ContentValues();
        cv.put(Table.COLUMN_DESCRIPTION, data.description);
        cv.put(Table.COLUMN_TIME_BEGIN, Arrays.toString(data.timeBegin));
        cv.put(Table.COLUMN_TIME_END, Arrays.toString(data.timeEnd));
        cv.put(Table.COLUMN_CHECKED_DAYS, Arrays.toString(data.checkedDays));
        cv.put(Table.COLUMN_IS_ACTIVATED, boolToInt(data.isActivated));
        cv.put(Table.COLUMN_IS_VIBRATION_ALLOWED, boolToInt(data.isVibrationAllowed));
        return cv;
    }

    private int boolToInt(boolean value) {
        return value ? 1 : 0;
    }

    public LinkedList<Data> select() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(Table.TABLE_SELECT, null);
        LinkedList<Data> data = getDataFrom(c);
        db.close();
        return data;
    }

    private LinkedList<Data> getDataFrom(Cursor cursor) {
        cursor.moveToFirst();
        LinkedList<Data> data = new LinkedList<>();
        for(int i = 0; i < cursor.getCount(); i++) {
            Data dataItem = getDataItem(cursor);
            data.add(dataItem);
            Log.d("myLogs", data.toString());
            cursor.moveToNext();
        }
        cursor.close();
        return data;
    }

    private Data getDataItem(Cursor cursor) {
        Data data = new Data();
        data.id = cursor.getLong(cursor.getColumnIndex(Table.COLUMN_ID));
        data.description = cursor.getString(cursor.getColumnIndex(Table.COLUMN_DESCRIPTION));
        data.timeBegin = new int[] { 12, 12 };
        data.timeEnd = new int[] { 12, 12 };
        data.checkedDays = new boolean[]{false, false, false, false, true, true, true};
        data.isActivated = true;
        data.isVibrationAllowed = true;
        return data;
    }

}
