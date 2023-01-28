package com.example.agrifymad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.agrifymad.adapters.MyCartAdapter;
import com.example.agrifymad.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlacedOrderActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore firestore;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed_order);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled( true );
        getSupportActionBar().setTitle("Order Placed");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlacedOrderActivity.this, NavDrawer.class);
                startActivity(intent);;
            }
        });

        List<MyCartModel> list = (ArrayList<MyCartModel>) getIntent().getSerializableExtra("itemList");

        if(list != null && list.size() > 0){
            for(MyCartModel model : list){
                final HashMap<String,Object> cartMap = new HashMap<>();

                cartMap.put("productName",model.getProductName());
                cartMap.put("productPrice",model.getProductPrice());
                cartMap.put("currentDate",model.getCurrentDate());
                cartMap.put("currentTime",model.getCurrentTime());
                cartMap.put("totalQuantity",model.getTotalQuantity());
                cartMap.put("totalPrice",model.getTotalPrice());

                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("MyOrder").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                Toast.makeText(PlacedOrderActivity.this, "Your order has been placed", Toast.LENGTH_SHORT).show();

                                for(int i=0; i<list.size(); i++) {
                                    firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                                            .collection("AddToCart").document(list.get(i).getDocumentId()).delete();
                                }
                            }
                        });
            }
        }
    }
}