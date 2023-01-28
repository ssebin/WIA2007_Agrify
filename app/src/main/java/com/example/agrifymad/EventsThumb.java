package com.example.agrifymad;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.agrifymad.adapters.EventsRecyclerViewAdapter;

public class EventsThumb extends AppCompatActivity {

    private EventsRecyclerViewAdapter.RecyclerViewClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_thumb);
    }
}