package com.example.agrifymad.activities;

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

import com.example.agrifymad.NavDrawer;
import com.example.agrifymad.R;
import com.example.agrifymad.Registration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    Button signIn;
    EditText email,password;
    TextView signUpForLogin;

    FirebaseAuth auth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        signIn = findViewById(R.id.login);
        email = findViewById(R.id.email_login);
        password = findViewById(R.id.password_login);
        signUpForLogin = findViewById(R.id.register);

        signUpForLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Registration.class));
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {

        final String userEmail = email.getText().toString();
        final String userPassword = password.getText().toString();

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

        auth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.VISIBLE);
                    startActivity(new Intent(Login.this, NavDrawer.class));
                    //finish();
                }
                else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Login.this,"Authentication Failed.",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}