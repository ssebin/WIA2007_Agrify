package com.example.agrifymad;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agrifymad.adapters.HistoryRecyclerViewAdapter;

public class HistoryThumb extends AppCompatActivity {

    private HistoryRecyclerViewAdapter.RecyclerViewClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_thumb);
    }

    //public static void openInfo() {
    //    Intent intent = new Intent(this, EventsMainPage.class);
    //    startActivity(intent);
    //}
}