package com.example.agrifymad;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

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


public class ShopFragment extends Fragment {

    FirebaseFirestore db;
    RecyclerView recyclerView;
    List<ShopModel> shopModelList;
    ShopAdapter shopAdapter;
    ProgressBar progressBar;

    //Search View
    EditText search_box;
    private List<ShopModel> list;
    private RecyclerView recyclerViewSearch;
    private ShopAdapter shopAdapters;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shop, container, false);

        db = FirebaseFirestore.getInstance();
        recyclerView = root.findViewById(R.id.shop_rec);

        progressBar = root.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        shopModelList = new ArrayList<>();
        shopAdapter = new ShopAdapter(getActivity(),shopModelList);
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
                            Toast.makeText(getActivity(), "Error"+task.getException(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                    }
                });
        ///////Search View
        recyclerViewSearch = root.findViewById(R.id.shop_search_rec);
        search_box = root.findViewById(R.id.shop_search_box);
        list = new ArrayList<>();
        shopAdapters = new ShopAdapter(getContext(),list);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getContext()));
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

        return root;
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