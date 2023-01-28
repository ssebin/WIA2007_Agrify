package com.example.agrifymad;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.agrifymad.adapters.ReviewAdapter;
import com.example.agrifymad.models.ReviewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ReviewFragment extends AppCompatActivity {

    FirebaseFirestore db;
    RecyclerView recyclerView;
    List<ReviewModel> reviewModelList;
    ReviewAdapter reviewAdapter;
    ProgressBar progressBar;
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_review);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled( true );
        getSupportActionBar().setTitle("Community Reviews");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.review_rec);

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewModelList = new ArrayList<>();
        reviewAdapter = new ReviewAdapter(this,reviewModelList);
        recyclerView.setAdapter(reviewAdapter);

        db.collection("Reviews")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                ReviewModel reviewModel = document.toObject(ReviewModel.class);
                                reviewModelList.add(reviewModel);
                                reviewAdapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);

                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Error"+task.getException(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }
                    }
                });
    }
}