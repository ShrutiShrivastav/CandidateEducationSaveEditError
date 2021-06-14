package com.example.sqliterecyclerview;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "ExampleDatabase.db";
    private static final int DATABASE_VERSION = 3;

    private static final String TABLE_NAME = "my_library";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "Position_title";
    private static final String COLUMN_NAME = "Company_name";
    private static final String COLUMN_DATE_FROM = "dateFrom";
    private static final String COLUMN_DATE_TO = "date_To";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + "TEXT," +
                COLUMN_NAME + "TEXT," +
                COLUMN_DATE_FROM + "TEXT," +
                COLUMN_DATE_TO + "TEXT);";
      db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
      db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
      onCreate(db);
    }
    //method for inserting data into database
    void addData(String title, String name, String dateFrom, String dateTo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DATE_FROM, dateFrom);
        cv.put(COLUMN_DATE_TO, dateTo);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
}
