package com.example.agrifymad;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.agrifymad.adapters.ShopAdapter;
import com.example.agrifymad.models.PopularModel;
import com.example.agrifymad.models.ShopModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ShopFragment extends AppCompatActivity {

    FirebaseFirestore db;
    RecyclerView recyclerView;
    ArrayList<ShopModel> shopModelList;
    ArrayList<PopularModel> popularModelList;
    ShopAdapter shopAdapter;
    ProgressBar progressBar;
    Toolbar toolbar;

    //Search View
    EditText search_box;
    private ArrayList<ShopModel> list;
    private RecyclerView recyclerViewSearch;
    private ShopAdapter shopAdapters;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_shop);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled( true );
        getSupportActionBar().setTitle("Nearby Farms");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.shop_rec);

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        shopModelList = new ArrayList<>();
        shopAdapter = new ShopAdapter(this, shopModelList, popularModelList);
        recyclerView.setAdapter(shopAdapter);

        db.collection("Farm")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                ShopModel shopModel = document.toObject(ShopModel.class);
                                shopModelList.add(shopModel);
                                shopAdapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);

                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Error"+task.getException(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                    }
                });

        //Search View
        recyclerViewSearch = findViewById(R.id.shop_search_rec);
        search_box = findViewById(R.id.shop_search_box);
        list = new ArrayList<>();
        shopAdapters = new ShopAdapter(getApplicationContext(),list,popularModelList);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerViewSearch.setAdapter(shopAdapters);
        recyclerViewSearch.setHasFixedSize(true);
        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(editable.toString().isEmpty()){
                    list.clear();
                    shopAdapters.notifyDataSetChanged();

                }else{
                    searchProduct(editable.toString());
                }
            }
        });

    }

    private void searchProduct(String type) {

        if(!type.isEmpty()){

            db.collection("Farm").whereEqualTo("farmName", type).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.isSuccessful() && task.getResult() != null){
                                list.clear();
                                shopAdapters.notifyDataSetChanged();
                                for(DocumentSnapshot doc: task.getResult().getDocuments()){
                                    ShopModel shopModel = doc.toObject(ShopModel.class);
                                    list.add(shopModel);
                                    shopAdapters.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }
    }
}