package com.example.agrifymad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.agrifymad.activities.Login;
import com.google.firebase.auth.FirebaseAuth;

public class Welcome extends AppCompatActivity {

    ProgressBar progressBar;
    Animation bottom;
    ImageView welcome;
    TextView login,registration,explore,welcome_title,welcome_desc;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        auth = FirebaseAuth.getInstance();

        bottom = AnimationUtils.loadAnimation(this,R.anim.side_anim);

        welcome = findViewById(R.id.imageView);
        login = findViewById(R.id.textView7);
        registration = findViewById(R.id.textView5);
        welcome_title = findViewById(R.id.textView);
        welcome_desc = findViewById(R.id.textView6);
        explore = findViewById(R.id.textView4);

        welcome.setAnimation(bottom);
        login.setAnimation(bottom);
        registration.setAnimation(bottom);
        welcome_desc.setAnimation(bottom);
        welcome_title.setAnimation(bottom);
        explore.setAnimation(bottom);

        progressBar = findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.GONE);
    }

    public void login(View view){
        startActivity(new Intent(Welcome.this, Login.class));
    }

    public void registration(View view){
        startActivity(new Intent(Welcome.this, Registration.class));
    }
}