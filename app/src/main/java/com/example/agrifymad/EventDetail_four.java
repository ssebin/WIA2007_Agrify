package com.example.agrifymad;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class EventDetail_four extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_detail_four);

        getWindow().setStatusBarColor(Color.GRAY);

        Intent intent = getIntent();

        ImageButton ImButton = findViewById(R.id.BtnCancel);
        ImButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                onBackPressed();
//                Intent i = new Intent(v.getContext(), EventsHome.class);
//                startActivity(i);
            }
        });

    }
}