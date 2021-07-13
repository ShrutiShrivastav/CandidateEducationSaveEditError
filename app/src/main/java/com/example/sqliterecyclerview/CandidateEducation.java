package com.example.sqliterecyclerview;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class CandidateEducation extends AppCompatActivity {


Button visibility_btn,edu_visibility_btn, cancel_btn, btn_save, btn_recycler ,edu_recycler, edu_save,edu_cancel;
    ImageView back_btn_edu;
    TextView tv_showHistory1,tv_showHistory2, tv_showHistory3;
RelativeLayout relativeLayout,edu_relativeLayout;


// declaring variables
    EditText new_Title,new_Name,new_dateFrom,new_dateTo;
    EditText new_Skill,new_Study,new_instName,new_dateEnd;

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
        tv_showHistory1 = findViewById(R.id.tv_showHistory1);
        tv_showHistory2 = findViewById(R.id.tv_showHistory2);


        //getting variables
        new_Title = findViewById(R.id.et_Title);
        new_Name = findViewById(R.id.et_Name);
        new_dateFrom = findViewById(R.id.et_DateFrom);
        new_dateTo = findViewById(R.id.et_DateTo);

        //calling viewAll methods
        viewAll1();


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
                startActivity(new Intent(getApplicationContext(), RecyclerView.class));
            }
        });


        //EDUCATION HISTORY PART
        edu_relativeLayout = findViewById(R.id.Edu_Visibility_layout);
        edu_visibility_btn = findViewById(R.id.edu_visibility_layout_button);
        edu_save = findViewById(R.id.edu_save);
        edu_cancel = findViewById(R.id.edu_cancel);
        tv_showHistory3 = findViewById(R.id.tv_showHistory3);
        edu_recycler = findViewById(R.id.edu_recycler_button);

        //getting variables
        new_Skill = findViewById(R.id.et_skill);
        new_Study = findViewById(R.id.et_study);
        new_instName = findViewById(R.id.et_instName);
        new_dateEnd = findViewById(R.id.et_DateEnd);

        //calling viewAll methods
      viewAll2();


        //setting visibility
        edu_visibility_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edu_relativeLayout.setVisibility(View.VISIBLE);
            }
        });

        //setting invisibility
        edu_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edu_relativeLayout.setVisibility(View.GONE);
            }
        });

        //setting listener on SAVE BUTTON
        edu_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EduProcessInsert(new_Skill.getText().toString().trim(), new_Study.getText().toString().trim(), new_instName.getText().toString().trim(), new_dateEnd.getText().toString().trim());
            }
        });

        //redirecting to recycler view
        edu_recycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EduRecyclerView.class));
            }
        });


    }

    //METHOD FOR VIEWDATA
       public void viewAll1() {
           //setting listener for showDataButton 1
           tv_showHistory1.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   MyDatabaseHelper myDB = new MyDatabaseHelper(CandidateEducation.this);
                   Cursor cursor = myDB.readTop1();
                   assert cursor != null;
                   cursor.moveToFirst();

                   if (cursor.getCount() == 0) {
                       //show messages
                       Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
                   }

                   while (cursor.moveToNext()) {
                       tv_showHistory1.setText("Title : " + cursor.getString(1)+" \n" + "Company Name : " +cursor.getString(2)+" \n"+ "Date From : " +cursor.getString(3)+" \n" + "Date To : " +cursor.getString(4));
                   }
               }
           });

        //setting listener for showDataButton 2
        tv_showHistory2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(CandidateEducation.this);
                Cursor cursor = myDB.readTop2();

                if (cursor.getCount() == 0) {
                    //show messages
                    Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
                }
                if(cursor.moveToFirst()){
                    do {
                        tv_showHistory2.setText("Title : " + cursor.getString(1) + " \n" + "Company Name : " + cursor.getString(2) + " \n" + "Date From : " + cursor.getString(3) + " \n" + "Date To : " + cursor.getString(4));
                 }
                    while(cursor.moveToNext());
            }}

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

// for EDUCATION HISTORY

    //METHOD FOR VIEWDATA

    public void viewAll2() {

        //setting listener for showDataButton 3
        tv_showHistory3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EduDatabaseHelper myDB = new EduDatabaseHelper(CandidateEducation.this);
                Cursor cursor = myDB.readTop3();

                if (cursor.getCount() == 0) {
                    //show messages
                    Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
                }
                if(cursor.moveToFirst()){
                    do {
                        tv_showHistory3.setText("Skill: " + cursor.getString(1) + " \n" + "Field of Study/Topic : " + cursor.getString(2) + " \n" + "Institution Name : " + cursor.getString(3) + " \n" + "Date Completion : " + cursor.getString(4));
                    }
                    while(cursor.moveToNext());
                }}

        });
    }


    //method for inserting data into database
    private void EduProcessInsert(String skill, String study, String instName, String dateEnd) {

        EduDatabaseHelper myDB = new EduDatabaseHelper(CandidateEducation.this);
        myDB.EduAddData(skill,study,instName,dateEnd);
        new_Skill.setText("");
        new_Study.setText("");
        new_instName.setText("");
        new_dateEnd.setText("");

    }

}
