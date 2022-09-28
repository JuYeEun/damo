package com.example.demoproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Movie> myDataList = null;

    public MyAdapter(ArrayList<Movie> myDataList) {

        this.myDataList = myDataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_list, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolder  viewHolder = null;
        viewHolder.imageView.setImageResource( myDataList.get(position).getImageResourceID());
        viewHolder.title.setText(myDataList.get(position).getMovieTitle());
        viewHolder.grade.setText(myDataList.get(position).getMovieGrade());
    }

    @Override
    public int getItemCount() {
        return myDataList.size();
    }
}
