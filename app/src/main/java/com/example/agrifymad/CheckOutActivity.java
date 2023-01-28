package com.example.agrifymad;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agrifymad.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class CheckOutActivity extends AppCompatActivity {

    ImageView image ;
    TextView amount;
    FirebaseFirestore db;
    FirebaseAuth auth;
    List<MyCartModel> cartModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_check_out);

        image = findViewById(R.id.pay_gpay);
        amount = findViewById(R.id.textView11);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        cartModelList = new ArrayList<>();

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        amount.setText(totalAmount);
    }
}