package com.example.agrifymad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agrifymad.activities.Login;
import com.example.agrifymad.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {


    Button register;
    EditText name,email,password;
    TextView login;
    FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        progressBar=findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        register = findViewById(R.id.signUp_btn);
        name = findViewById(R.id.name_register);
        email = findViewById(R.id.email_register);
        password = findViewById(R.id.password_register);
        login = findViewById(R.id.sign_in);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registration.this, Login.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    createUser();
                    progressBar.setVisibility(View.VISIBLE);
                    startActivity(new Intent(Registration.this, Login.class));
            }
        });


    }

    private void createUser() {
        String userName = name.getText().toString();
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();

        if(TextUtils.isEmpty(userName)){
            Toast.makeText(this,"Name is Empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(userEmail)){
            Toast.makeText(this,"Email is Empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(userPassword)){
            Toast.makeText(this,"Password is Empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(userPassword.length()<6){
            Toast.makeText(this,"Password Length must be greater than 6 letter", Toast.LENGTH_SHORT).show();
            return;
        }

        //Create User
        auth.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    UserModel userModel = new UserModel(userName,userEmail,userPassword);
                    String id = task.getResult().getUser().getUid();
                    database.getReference().child("Users").child(id).setValue(userModel);
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Registration.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                }
                else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Registration.this,"Error, Please try again "+ task.getException(),Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}