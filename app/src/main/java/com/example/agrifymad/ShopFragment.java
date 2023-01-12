package com.example.agrifymad;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.agrifymad.Repository.HistoryRepo;
import com.example.agrifymad.adapters.HistoryRecyclerViewAdapter;
import com.example.agrifymad.adapters.ShopAdapter;
import com.example.agrifymad.adapters.ViewAllAdapter;
import com.example.agrifymad.models.NavCategoryModel;
import com.example.agrifymad.models.ShopModel;
import com.example.agrifymad.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class ShopFragment extends AppCompatActivity {

    FirebaseFirestore db;
    RecyclerView recyclerView;
    ArrayList<ShopModel> shopModelList;
    ShopAdapter shopAdapter;
    ProgressBar progressBar;
    Toolbar toolbar;

    //Search View
    EditText search_box;
    private ArrayList<ShopModel> list;
    private RecyclerView recyclerViewSearch;
    private ShopAdapter shopAdapters;


/*
    public static final String TAG = "MyFragment";

    private int position;

    // You can add other parameters here
    public static ShopFragment newInstance(int position) {
        Bundle args = new Bundle();
        // Pass all the parameters to your bundle
        args.putInt("pos", position);
        ShopFragment fragment = new ShopFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.position = getArguments().getInt("pos");
    } */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //View root = inflater.inflate(R.layout.fragment_shop, container, false);

        setContentView(R.layout.fragment_shop);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled( true );
        getSupportActionBar().setTitle("Farms");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.shop_rec);
        //recyclerView = root.findViewById(R.id.shop_rec);

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        shopModelList = new ArrayList<>();
        shopAdapter = new ShopAdapter(this, shopModelList);
        recyclerView.setAdapter(shopAdapter);

//        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
//        getActionBar().setDisplayShowHomeEnabled(true);
//
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });

        /*LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        shopAdapter = new ShopAdapter(HistoryRepo.getHistoryRepo().getHistoryModelList());
        Log.i("data-->",""+HistoryRepo.getHistoryRepo().getHistoryModelList().size());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(historyRecyclerViewAdapter);
        historyRecyclerViewAdapter.notifyDataSetChanged();*/

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
        ///////Search View
        recyclerViewSearch = findViewById(R.id.shop_search_rec);
        search_box = findViewById(R.id.shop_search_box);
        list = new ArrayList<>();
        shopAdapters = new ShopAdapter(getApplicationContext(),list);
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

        // return this;
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