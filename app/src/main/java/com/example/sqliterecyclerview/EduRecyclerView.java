package com.example.sqliterecyclerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EduRecyclerView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu_recycler_view);


        ImageView eduDeleteAll = findViewById(R.id.edu_delete_all);
        ImageView eduBackArrow = findViewById(R.id.back_arrow_eduHistory);
        ImageView emptyImage = findViewById(R.id.emptyImage);
        TextView noData = findViewById(R.id.noData);

        //to avoid NPE
        ArrayList<ModelEducation> eduDataHolder = new ArrayList<>();

        androidx.recyclerview.widget.RecyclerView recyclerView = findViewById(R.id.edu_recycler_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        EduAdapter adapter = new EduAdapter(eduDataHolder, this,EduRecyclerView.this);
        recyclerView.setAdapter(adapter);
        //for updating data in model class as well
        adapter.notifyDataSetChanged();

        Cursor cursor = new EduDatabaseHelper( this).EduReadData();
        if(cursor.getCount() == 0)
        {
            emptyImage.setVisibility(View.VISIBLE);
            noData.setVisibility(View.VISIBLE);
        }
        else{
            while(cursor.moveToNext())
            {
                // creating instance of model type
                ModelEducation eduObject = new ModelEducation(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3), cursor.getString(4));
                Log.e("Class values",": "+eduObject);
                eduDataHolder.add(eduObject);
            }
            emptyImage.setVisibility(View.INVISIBLE);
            noData.setVisibility(View.INVISIBLE);
        }

        //setting listener for back button
        eduBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //setting listener on deleteAll
        eduDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDeleteDialog();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    //alert box for deleteAll
    void confirmDeleteDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All");
        builder.setMessage("Are you sure you want to delete all data"+ "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EduDatabaseHelper myDB = new EduDatabaseHelper(EduRecyclerView.this);
                myDB.EduDeleteAllData();
                Toast.makeText(getApplicationContext(),"Deleted Successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (EduRecyclerView.this, EduRecyclerView.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }


}
