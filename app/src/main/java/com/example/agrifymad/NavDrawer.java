package com.example.agrifymad;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.agrifymad.models.UserModel;
import com.example.agrifymad.ui.category.CategoryFragment;
import com.example.agrifymad.ui.profile.ProfileFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agrifymad.databinding.ActivityNavDrawerBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import de.hdodenhof.circleimageview.CircleImageView;

public class NavDrawer extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavDrawerBinding binding;
    DrawerLayout mDrawerLayout;
    FirebaseAuth auth;
    FirebaseDatabase database;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setNavigationViewListener();

        //setContentView(R.layout.activity_main);
        // Add your parameters
        // ShopFragment fragment = ShopFragment.newInstance(100);
        // R.id.container - the id of a view that will hold your fragment; usually a FrameLayout


        binding = ActivityNavDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarNavDrawer.toolbar);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_profile, R.id.nav_category,R.id.nav_offers
                ,R.id.nav_farm,R.id.nav_my_orders,R.id.nav_my_carts)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_drawer);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View headerView = navigationView.getHeaderView(0);
        TextView headerName = headerView.findViewById(R.id.nav_header_name);
        TextView headerEmail = headerView.findViewById(R.id.nav_header_email);
        CircleImageView headerImg = headerView.findViewById(R.id.nav_header_img);

        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        UserModel userModel = snapshot.getValue(UserModel.class);

                        headerName.setText(userModel.getName());
                        headerEmail.setText(userModel.getEmail());

                        Glide.with(NavDrawer.this).load(userModel.getProfileImg()).into(headerImg);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                if (id == R.id.nav_home){
                    Intent newIntent = new Intent(getApplicationContext(), NavDrawer.class);
                    startActivity(newIntent);
                }
                if (id == R.id.nav_category){
                    Intent newIntent = new Intent(getApplicationContext(), CategoryFragment.class);
                    startActivity(newIntent);
                }
                if (id == R.id.nav_offers){
                    Intent newIntent = new Intent(getApplicationContext(), EventsHome.class);
                    startActivity(newIntent);
                }
                if (id == R.id.nav_farm){
                    Intent newIntent = new Intent(getApplicationContext(), ShopFragment.class);
                    startActivity(newIntent);
                }
                if (id == R.id.nav_my_orders){
                    Intent newIntent = new Intent(getApplicationContext(), HistoryHome.class);
                    startActivity(newIntent);
                }
                if (id == R.id.nav_my_carts){
                    Intent newIntent = new Intent(getApplicationContext(), MyCartsFragment.class);
                    startActivity(newIntent);
                }
                if (id == R.id.nav_review){
                    Intent newIntent = new Intent(getApplicationContext(), ReviewFragment.class);
                    startActivity(newIntent);
                }
                if (id == R.id.nav_profile){
                    Intent newIntent = new Intent(getApplicationContext(), ProfileFragment.class);
                    startActivity(newIntent);
                }
                if (id == R.id.nav_map){
                    Intent newIntent = new Intent(getApplicationContext(), MapsActivity.class);
                    startActivity(newIntent);
                }
                if (id == R.id.nav_logout){
                    signOutUser();
                }
                return true;
            }
        });
    }

    private void signOutUser() {
        Intent i = new Intent(NavDrawer.this, Welcome.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        auth.signOut();
        startActivity(i);
        finish();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_drawer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /*
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {

            case R.id.nav_my_orders: {
                // do something
                Intent intent = new Intent(this, HistoryHome.class);
                startActivity(intent);
                break;
            }
        }
        // close navigation drawer
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

     */



}