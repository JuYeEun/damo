package com.example.demoproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView title;
    TextView grade;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageView2);
        title = itemView.findViewById(R.id.title);
        grade = itemView.findViewById(R.id.grade);
    }
}
