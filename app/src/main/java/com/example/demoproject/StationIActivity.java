package com.example.demoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StationIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_iactivity);

        Button Btn_go_Rev = findViewById(R.id.btn_go_reservation);

        Btn_go_Rev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(StationIActivity.this,ReservationActivity.class);
                startActivity(it);
            }
        });
    }
}