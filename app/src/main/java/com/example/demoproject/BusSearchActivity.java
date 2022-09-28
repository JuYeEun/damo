package com.example.demoproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BusSearchActivity extends AppCompatActivity {

    private ArrayList<Movie> dataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_search);

        this.InitializeData();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new MyAdapter(dataList));
    }

    private void InitializeData() {
        dataList = new ArrayList<>();

        dataList.add(new Movie(R.drawable.bus,"버스번호","dddd"));
        dataList.add(new Movie(R.drawable.bus,"버스번호","dddd"));
        dataList.add(new Movie(R.drawable.bus,"버스번호","dddd"));
        dataList.add(new Movie(R.drawable.bus,"버스번호","dddd"));
        dataList.add(new Movie(R.drawable.bus,"버스번호","dddd"));
        dataList.add(new Movie(R.drawable.bus,"버스번호","dddd"));
        dataList.add(new Movie(R.drawable.bus,"버스번호","dddd"));
        dataList.add(new Movie(R.drawable.bus,"버스번호","dddd"));
        dataList.add(new Movie(R.drawable.bus,"버스번호","dddd"));
        dataList.add(new Movie(R.drawable.bus,"버스번호","dddd"));
        dataList.add(new Movie(R.drawable.bus,"버스번호","dddd"));
        dataList.add(new Movie(R.drawable.bus,"버스번호","dddd"));
    }


}