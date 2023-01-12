package com.example.agrifymad;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agrifymad.adapters.HistoryRecyclerViewAdapter;
import com.example.agrifymad.Repository.HistoryRepo;
import com.example.agrifymad.databinding.ActivityNavDrawerBinding;
import com.google.android.material.navigation.NavigationView;


public class HistoryHome extends AppCompatActivity{
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavDrawerBinding binding;
    RecyclerView recyclerView;
    HistoryRecyclerViewAdapter historyRecyclerViewAdapter;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle aToggle;
    Toolbar toolbar;
    RecyclerView.Adapter adapter;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_home);
        //setNavigationViewListener();

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

//        DrawerLayout drawer = binding.drawerLayout;
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_profile, R.id.nav_category,R.id.nav_offers
//                ,R.id.nav_farm,R.id.nav_my_orders,R.id.nav_my_carts)
//                .setOpenableLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_drawer);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
    }
    void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        historyRecyclerViewAdapter = new HistoryRecyclerViewAdapter(HistoryRepo.getHistoryRepo().getHistoryModelList());
        Log.i("data-->",""+HistoryRepo.getHistoryRepo().getHistoryModelList().size());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(historyRecyclerViewAdapter);
        historyRecyclerViewAdapter.notifyDataSetChanged();

    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_drawer);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
/*
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {

            case R.id.nav_my_orders: {
                // do something
                //Intent intent = new Intent(view.getContext(), HistoryHome.class);
                //view.getContext().startActivity(intent);
                break;
            }
        }
        // close navigation drawer
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }



    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_my_orders);
        navigationView.setNavigationItemSelectedListener(this);
    }

 */


}



/*
public class HistoryHome extends Fragment {

    // Add RecyclerView member
    RecyclerView recyclerView;
    List<HistoryModel> historyModelList;
    HistoryRecyclerViewAdapter historyAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.history_home, container, false);

        // Add the following lines to create RecyclerView
//        recyclerView = view.findViewById(R.id.recyclerview);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        recyclerView.setAdapter(new RandomNumListAdapter(1234));

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        historyModelList = new ArrayList<>();
        historyAdapter = new HistoryRecyclerViewAdapter(getActivity(), historyModelList);
        recyclerView.setAdapter(historyAdapter);

        return view;
    }
}

 */