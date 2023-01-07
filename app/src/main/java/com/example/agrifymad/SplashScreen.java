package com.example.agrifymad;

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

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;

    Animation bottom;
    ImageView image;
    TextView slogan;

    ProgressBar progressBar;
    //FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);

        bottom = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        image = findViewById(R.id.logo);
        slogan = findViewById(R.id.textView2);

        image.setAnimation(bottom);
        slogan.setAnimation(bottom);

        //auth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        /*
        if (auth.getCurrentUser() != null){
            progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(this,"Please wait for your login", Toast.LENGTH_SHORT).show();

        }*/

        new Handler().postDelayed(() -> {

            /*firsTime only allow onBoarding

            SharedPreferences onBoardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);

            boolean isFirstTime = onBoardingScreen.getBoolean("firstTime",true);


            if(isFirstTime){

                SharedPreferences.Editor editor = onBoardingScreen.edit();
                editor.putBoolean("firstTime",false);
                editor.commit();

                Intent intent = new Intent(getApplicationContext(),Onboard.class);
                startActivity(intent);
                finish();

            }else{
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
            */
            Intent intent = new Intent(getApplicationContext(),Onboard.class);
            startActivity(intent);
            finish();

        },SPLASH_SCREEN);


    }


}