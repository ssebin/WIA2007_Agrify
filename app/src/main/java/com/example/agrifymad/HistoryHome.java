package com.example.agrifymad;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agrifymad.adapters.HistoryRecyclerViewAdapter;
import com.example.agrifymad.Repository.HistoryRepo;
import com.example.agrifymad.databinding.ActivityNavDrawerBinding;
import com.google.android.material.navigation.NavigationView;

public class HistoryHome extends AppCompatActivity{

    RecyclerView recyclerView;
    HistoryRecyclerViewAdapter historyRecyclerViewAdapter;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_home);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled( true );
        getSupportActionBar().setTitle("Order History");

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
        historyRecyclerViewAdapter = new HistoryRecyclerViewAdapter(HistoryRepo.getHistoryRepo().getHistoryModelList());
        Log.i("data-->",""+HistoryRepo.getHistoryRepo().getHistoryModelList().size());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(historyRecyclerViewAdapter);
        historyRecyclerViewAdapter.notifyDataSetChanged();

    }
}