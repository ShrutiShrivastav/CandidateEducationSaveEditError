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
Button visibility_btn, cancel_btn, btn_save;
    ImageView back_btn_edu;
RelativeLayout relativeLayout;


// declaring variables
    EditText getName,getTitle,getDateFrom,getDateTo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_candidate_education);

        ImageView back_btn_edu = findViewById(R.id.back_arrow_edu);

        cancel_btn = findViewById(R.id.cancel);
        relativeLayout = findViewById(R.id.Visibility_layout);
        visibility_btn = findViewById(R.id.visibility_layout_button);
        btn_save = findViewById(R.id.button_save);

        //getting variables
        getTitle =findViewById(R.id.et_getTitle);
        getName = findViewById(R.id.et_getName);
        getDateFrom = findViewById(R.id.et_getDateFrom);
        getDateTo = findViewById(R.id.et_getDateTo);


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
               MyDatabaseHelper myDB = new MyDatabaseHelper(CandidateEducation.this);
                myDB.addData( getTitle.getText().toString().trim(),
               getName.getText().toString().trim(),
               getDateFrom.getText().toString().trim(),
               getDateTo.getText().toString().trim());
           }
       });
    }
}
