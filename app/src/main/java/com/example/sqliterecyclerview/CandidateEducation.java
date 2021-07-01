package com.example.sqliterecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class CandidateEducation extends AppCompatActivity {
Button visibility_btn, cancel_btn, btn_save, btn_recycler;
    ImageView back_btn_edu;
RelativeLayout relativeLayout;


// declaring variables
    EditText new_Title,new_Name,new_dateFrom,new_dateTo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_candidate_education);

       back_btn_edu = findViewById(R.id.back_arrow_edu);

        cancel_btn = findViewById(R.id.cancel);
        relativeLayout = findViewById(R.id.Visibility_layout);
        visibility_btn = findViewById(R.id.visibility_layout_button);
        btn_save = findViewById(R.id.button_save);
        btn_recycler = findViewById(R.id.recycler_button);

        //getting variables
        new_Title =findViewById(R.id.et_Title);
        new_Name = findViewById(R.id.et_Name);
        new_dateFrom = findViewById(R.id.et_DateFrom);
        new_dateTo = findViewById(R.id.et_DateTo);


        //setting visibility
        visibility_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setVisibility(View.VISIBLE);
            }
        });

        //setting invisibility
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setVisibility(View.GONE);
            }
        });

        //setting listener on SAVE BUTTON
       btn_save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               processInsert(new_Title.getText().toString().trim(), new_Name.getText().toString().trim(), new_dateFrom.getText().toString().trim(), new_dateTo.getText().toString().trim());
           }
       });

       //redirecting to recycler view
       btn_recycler.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(getApplicationContext(),RecyclerView.class));
           }
       });

    }
 //method for inserting data into database
    private void processInsert(String title, String name, String dateFrom, String dateTo) {

        MyDatabaseHelper myDB = new MyDatabaseHelper(CandidateEducation.this);
        myDB.addData(title,name,dateFrom,dateTo);
        new_Title.setText("");
        new_Name.setText("");
        new_dateFrom.setText("");
        new_dateTo.setText("");

    }


}
