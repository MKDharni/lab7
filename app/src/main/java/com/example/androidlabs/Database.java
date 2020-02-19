package com.example.androidlabs;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.Arrays;

public class Database extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "MyDatabase";
    public static final int VERSION_NUM = 2;
    public static final String TABLE_NAME = "MESSAGETABLE";
    public static final String COLUMN_ID = "D_id";
    public static final String COLUMN_SEND = "DisSend";
    public static final String COLUMN_MESSAGE = "Dmessage";

    public Database(Context ctx)
    {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }
     public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("CREATE TABLE " + TABLE_NAME + "( "+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_SEND + " INTEGER , " + COLUMN_MESSAGE + " TEXT)");
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(Database.class.getName(),"The database is upgraded from "
                + oldVersion + " to "+ newVersion + " .");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(Database.class.getName(),"The database is upgraded from "
                + newVersion + " to "+ oldVersion + " .");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void printCursor(Cursor c)
    {
        Log.d("printCursor", "The version of Datebase is : " + getReadableDatabase().getVersion());
        Log.d("printCursor", "The total number of rows in the cursor: " + c.getCount());
        Log.d("printCursor", "The number of columns in the cursor : " + c.getColumnCount());
        Log.d("printCursor", "Name of the columns in the cursor: " + Arrays.toString(c.getColumnNames()));
        Log.d("printCursor", "Each row of results in the cursor: " + DatabaseUtils.dumpCursorToString(c));
    }
}
