package com.example.sqliterecyclerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText new_Title,new_Name,new_dateFrom,new_dateTo;
ImageView back_update;
Button btn_update;

String title,name,dateFrom,dateTo;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        new_Title =findViewById(R.id.new_Title);
        new_Name =findViewById(R.id.new_Name);
        new_dateFrom =findViewById(R.id.new_DateFrom);
        new_dateTo =findViewById(R.id.new_DateTo);
        back_update = findViewById(R.id.back_arrow_update);
        btn_update = findViewById(R.id.update);

        //setting listener for back button

        back_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), RecyclerView.class);
                startActivity(intent);
            }
        });

    //First we call this
    getAndSetIntentData();

        //setting listener for update button
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                title = new_Title.getText().toString().trim();
               name = new_Name.getText().toString().trim();
               dateFrom = new_dateFrom.getText().toString().trim();
              dateTo =  new_dateTo.getText().toString().trim();
              myDB.updateData(title,name,dateFrom,dateTo);
            }
        });

        //setting listener for delete button
        ImageView im_delete = findViewById(R.id.delete);
        im_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }
  //method for getting and setting intent data
    void getAndSetIntentData(){
        if(getIntent().hasExtra("title") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("dateFrom") && getIntent().hasExtra("dateTo")){
            //Getting Data from Intent

            title = getIntent().getStringExtra("title");
            name = getIntent().getStringExtra("name");
            dateFrom = getIntent().getStringExtra("dateFrom");
            dateTo = getIntent().getStringExtra("dateTo");

            //Setting Intent Data
            new_Title.setText(title);
            new_Name.setText(name);
            new_dateFrom.setText(dateFrom);
            new_dateTo.setText(dateTo);

            Log.d("", title+" "+name+" "+dateFrom+""+dateTo);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

      //method for dialog box
    void confirmDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteData(title);
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