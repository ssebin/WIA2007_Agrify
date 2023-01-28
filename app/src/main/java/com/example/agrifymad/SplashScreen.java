package com.example.agrifymad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;

    Animation bottom;
    ImageView image;
    TextView slogan;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);

        FirebaseMessaging.getInstance().subscribeToTopic("News")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Done";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }

                    }
                });

        bottom = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        image = findViewById(R.id.logo);
        slogan = findViewById(R.id.textView2);

        image.setAnimation(bottom);
        slogan.setAnimation(bottom);

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Toast.makeText(this,"Please wait for your login", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(() -> {
                // User is signed in
                Intent i = new Intent(SplashScreen.this, NavDrawer.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                    },SPLASH_SCREEN);
        } else {
            new Handler().postDelayed(() -> {
                Intent intent = new Intent(getApplicationContext(),Onboard.class);
                startActivity(intent);
                finish();

            },SPLASH_SCREEN);
        }
    }
}