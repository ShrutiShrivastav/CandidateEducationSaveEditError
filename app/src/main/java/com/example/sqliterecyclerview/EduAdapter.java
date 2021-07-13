package com.example.sqliterecyclerview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EduAdapter extends RecyclerView.Adapter<EduAdapter.EduRecyclerViewHolder> {
    //arraylist of model class type
    ArrayList<ModelEducation> eduDataHolder;
    Context context;
    private Activity activity;


    public EduAdapter(ArrayList<ModelEducation> eduDataHolder, Context context, Activity activity) {
        this.eduDataHolder = eduDataHolder;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public EduRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //converting xml file  layout into view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.candidate_education_row, parent, false);
        return new EduAdapter.EduRecyclerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull EduRecyclerViewHolder holder, int position) {
        ModelEducation m=eduDataHolder.get(position);
        //setting data from model class getter methods
        holder.tv_skill.setText(m.getSkill());
        holder.tv_study.setText(m.getStudy());
        holder.tv_instName.setText(m.getInstName());
        holder.tv_dateEnd.setText(m.getDateEnd());

        //setting listener for edit
        holder.im_edu_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EduUpdateActivity.class);
                //putting data
                intent.putExtra("Skill",(m.getSkill()));
                intent.putExtra("Study",(m.getStudy()));
                intent.putExtra("instName",(m.getInstName()));
                intent.putExtra("dateEnd",(m.getDateEnd()));
                intent.putExtra("_id",(m.getEduId()));
                Log.e("id of object is: ","->>"+m.getEduId());
                //start activityForResult to refresh the activity after updation
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eduDataHolder.size();
    }

    public class EduRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView tv_skill, tv_study, tv_instName, tv_dateEnd;
        ImageView im_edu_edit;

        public EduRecyclerViewHolder(View itemView) {
            super(itemView);

            tv_skill = itemView.findViewById(R.id.row_skill);
            tv_study = itemView.findViewById(R.id.row_study);
            tv_instName = itemView.findViewById(R.id.row_instName);
            tv_dateEnd = itemView.findViewById(R.id.row_date);

            im_edu_edit = itemView.findViewById(R.id.edu_edit);


        }
    }
}
