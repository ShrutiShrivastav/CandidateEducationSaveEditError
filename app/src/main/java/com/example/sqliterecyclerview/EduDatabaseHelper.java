package com.example.sqliterecyclerview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class EduDatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    public EduDatabaseHelper(@Nullable Context context) {
        super(context, "EducationDatabase",null,3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE my_Education (ID integer primary key autoincrement, Skill text, Study text, instName text, dateEnd text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS my_Education";
        db.execSQL(query);
        onCreate(db);
    }

    //method for inserting data into database
    void EduAddData(String Skill, String Study, String instName, String dateEnd){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Skill", Skill);
        cv.put("Study", Study);
        cv.put("instName", instName);
        cv.put("dateEnd", dateEnd);
        long result = db.insert("my_Education",null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    //for fetching the data
    public Cursor EduReadData() {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from my_Education order by dateEnd ASC";
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }

    //for fetching top two updated data
    public Cursor readTop3() {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from (select * from my_Education  ORDER BY ID desc LIMIT 2) order by ID";
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }

    //for updating the data
    void EduUpdateData(String Skill, String Study, String instName, String dateEnd,String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Skill", Skill);
        cv.put("Study", Study);
        cv.put("instName", instName);
        cv.put("dateEnd", dateEnd);
        //New Part
        Cursor cursor=db.rawQuery("select * from my_Education where ID = ?",new String[]{id});
        if(cursor.getCount()>0){
            Log.e("Found: ","values");
            long result = db.update("my_Education", cv, "ID = ?",new String[]{id});
            if(result == -1){
                Log.e("error","1"+ result);
                Toast.makeText(context.getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }else {
                Log.e("Updated","0 "+ cv);
                Toast.makeText(context.getApplicationContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Log.e("NotFound: ","values");
        }

        db.close();

    }

    //for deleting a row
    public void EduDeleteData(String Skill)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("my_Education", "Skill=?", new String[]{Skill});
        if(result == -1){
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    //for deleting all data
    void EduDeleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL ("DELETE FROM " + "my_Education");
    }

}
