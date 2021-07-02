package com.example.sqliterecyclerview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, "MyDatabase",null,3);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE my_Example (ID integer primary key autoincrement, Title text, Name text, dateFrom text, dateTo text)";
      db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    String query = "DROP TABLE IF EXISTS my_Example";
    db.execSQL(query);
      onCreate(db);
    }
    //method for inserting data into database
    void addData(String Title, String Name, String dateFrom, String dateTo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Title", Title);
        cv.put("Name", Name);
        cv.put("dateFrom", dateFrom);
        cv.put("dateTo", dateTo);
        long result = db.insert("my_Example",null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    //for fetching the data
    public Cursor readData() {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from my_Example order by dateFrom, dateTo ASC";
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }

    //for updating the data
    void updateData(String Title, String Name, String dateFrom, String dateTo,String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Title", Title);
        cv.put("Name", Name);
        cv.put("dateFrom", dateFrom);
        cv.put("dateTo", dateTo);
        //New Part
        Cursor cursor=db.rawQuery("select * from my_Example where ID = ?",new String[]{id});
        if(cursor.getCount()>0){
            Log.e("Found: ","values");
            long result = db.update("my_Example", cv, "ID = ?",new String[]{id});
            if(result == -1){
                Log.e("error","1"+ result);
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            }else {
                Log.e("Updated","0 "+ cv);
                Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
            }
        }
      else{
            Log.e("NotFound: ","values");
        }

        db.close();

    }

    //for deleting a row
    public void deleteData(String Title)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("my_Example", "Title=?", new String[]{Title});
        if(result == -1){
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    //for deleting all data
    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL ("DELETE FROM " + "my_Example");
    }
}
