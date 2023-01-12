package com.example.agrifymad;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agrifymad.adapters.EventsRecyclerViewAdapter;
import com.example.agrifymad.Repository.EventsRepo;

public class EventsHome extends AppCompatActivity {
    RecyclerView recyclerView;
    EventsRecyclerViewAdapter eventsRecyclerViewAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_home);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled( true );
        getSupportActionBar().setTitle("Events");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        initRecyclerView();
    }
    void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        eventsRecyclerViewAdapter = new EventsRecyclerViewAdapter(EventsRepo.getEventsRepo().getEventModelList());
        Log.i("data-->",""+EventsRepo.getEventsRepo().getEventModelList().size());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(eventsRecyclerViewAdapter);
        eventsRecyclerViewAdapter.notifyDataSetChanged();

    }
}