package com.example.sqliterecyclerview;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.RecyclerViewHolder> {
    //arraylist of model class type
    ArrayList<Model> dataholder;

     Context context;
    private Activity activity;



    //constructor
    public MyAdapter(ArrayList<Model> dataholder,Context context,Activity activity) {
        this.dataholder = dataholder;

        this.context = context;
        this.activity=activity;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //converting xml file  layout into view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return new RecyclerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyAdapter.RecyclerViewHolder holder, int position) {

        Model m=dataholder.get(position);
        //setting data from model class getter methods
        holder.tv_title.setText(m.getTitle());
        holder.tv_compName.setText(m.getName());
        holder.tv_dateFrom.setText(m.getDateFrom());
        holder.tv_dateTo.setText(m.getDateTo());

        //setting listener for edit
        holder.im_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                //putting data
                intent.putExtra("Title",(m.getTitle()));
                intent.putExtra("Name",(m.getName()));
                intent.putExtra("dateFrom",(m.getDateFrom()));
                intent.putExtra("dateTo",(m.getDateTo()));
                intent.putExtra("_id",(m.getId()));
                Log.e("id of object is: ","->>"+m.getId());
                //start activityForResult to refresh the activity after updation
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title, tv_compName, tv_dateFrom, tv_dateTo;
        ImageView im_edit;

        public RecyclerViewHolder(View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.row_title);
            tv_compName = itemView.findViewById(R.id.row_compName);
            tv_dateFrom = itemView.findViewById(R.id.row_dateFrom);
            tv_dateTo = itemView.findViewById(R.id.row_dateTo);

            im_edit = itemView.findViewById(R.id.edit);


        }
    }
}
