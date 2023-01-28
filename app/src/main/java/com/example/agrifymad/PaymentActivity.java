package com.example.agrifymad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.agrifymad.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseAuth auth;
    TextView totalAmount,payCash,payCard,payPrice;
    List<MyCartModel> cartModelList;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Payment Method");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        cartModelList = new ArrayList<>();

        totalAmount =findViewById(R.id.pay_price);
        payCard =findViewById(R.id.pay_card);
        payCash =findViewById(R.id.pay_cash);
        payPrice =findViewById(R.id.pay_price);

        payCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentActivity.this,PlacedOrderActivity.class);
                intent.putExtra("itemList", (Serializable) cartModelList);
                startActivity(intent);

            }
        });

        payCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentActivity.this,PlacedOrderActivity.class);
                intent.putExtra("itemList", (Serializable) cartModelList);
                startActivity(intent);

            }
        });

        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                                String documentId = documentSnapshot.getId();

                                MyCartModel cartModel = documentSnapshot.toObject(MyCartModel.class);

                                cartModel.setDocumentId(documentId);

                                cartModelList.add(cartModel);
                                //cartAdapter.notifyDataSetChanged();
                               //progressBar.setVisibility(View.GONE);
                               //recyclerView.setVisibility(View.VISIBLE);
                            }

                            calculateTotalAmount(cartModelList);

                        }
                    }
                });
    }


    private void calculateTotalAmount(List<MyCartModel> cartModelList) {

        int totalAmount = 0;
        for(MyCartModel myCartModel : cartModelList){
            totalAmount += myCartModel.getTotalPrice();
        }
        payPrice.setText("" + totalAmount);
    }
}