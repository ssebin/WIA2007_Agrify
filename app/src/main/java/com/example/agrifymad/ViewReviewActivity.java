package com.example.agrifymad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.agrifymad.adapters.ViewReviewAdapter;
import com.example.agrifymad.models.ViewReviewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewReviewActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    ViewReviewAdapter viewReviewAdapter;
    List<ViewReviewModel> viewReviewModelList;
    Toolbar toolbar;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_review);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled( true );
        getSupportActionBar().setTitle("Review Details");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        firestore = FirebaseFirestore.getInstance();
        String type= getIntent().getStringExtra("type");
        recyclerView = findViewById(R.id.view_review_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setVisibility(View.GONE);

        viewReviewModelList = new ArrayList<>();
        viewReviewAdapter = new ViewReviewAdapter(this,viewReviewModelList);
        recyclerView.setAdapter(viewReviewAdapter);

        //Getting fruit reviews .whereEqualTo("type","fruit") .whereEqualTo("type","fruit")
        //if (type != null && type.equalsIgnoreCase("fruit")){
            firestore.collection("AllReviews").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewReviewModel viewReviewModel = documentSnapshot.toObject(ViewReviewModel.class);
                        viewReviewModelList.add(viewReviewModel);
                        viewReviewAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }


                }
            });
        //}

        //Getting mushroom reviews
        if (type != null && type.equalsIgnoreCase("mushroom")){
            firestore.collection("AllReviews").whereEqualTo("type","mushroom").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewReviewModel viewReviewModel = documentSnapshot.toObject(ViewReviewModel.class);
                        viewReviewModelList.add(viewReviewModel);
                        viewReviewAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }


                }
            });
        }

        //Getting chili reviews
        if (type != null && type.equalsIgnoreCase("chili")){
            firestore.collection("AllReviews").whereEqualTo("type","chili").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewReviewModel viewReviewModel = documentSnapshot.toObject(ViewReviewModel.class);
                        viewReviewModelList.add(viewReviewModel);
                        viewReviewAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }


                }
            });
        }




}}