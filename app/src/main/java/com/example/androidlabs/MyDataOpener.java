package com.example.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.LocaleDisplayNames;
import android.util.Log;

import java.util.Arrays;

public class MyDataOpener extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyDatabase";
    public static final int VERSION_NUM = 2;
    public static final String TABLE_NAME = "MESSAGETABLE";
    public static final String COLUMN_ID = "D_id";
    public static final String COLUMN_SEND = "DisSend";
    public static final String COLUMN_MESSAGE = "Dmessage";

    public MyDataOpener(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("CREATE TABLE " + TABLE_NAME + "( "
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_SEND + " INTEGER , " + COLUMN_MESSAGE + " TEXT)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void printCursor(Cursor c) {
        Log.d("printCursor", "Datebase version Number : " + getReadableDatabase().getVersion());
        Log.d("printCursor", "Number of columns in cursor : " + c.getColumnCount());
        Log.d("printCursor", "Name of the columns in the cursor: " + Arrays.toString(c.getColumnNames()));
        Log.d("printCursor", "Number of results in the cursor: " + c.getCount());
        Log.d("printCursor", "Each row of results in the cursor: " + DatabaseUtils.dumpCursorToString(c));


    }
}
