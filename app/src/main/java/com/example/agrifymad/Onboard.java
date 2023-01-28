package com.example.agrifymad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.agrifymad.adapters.SliderAdapter;
import com.google.firebase.auth.FirebaseAuth;

public class Onboard extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout dotsLayout;
    SliderAdapter sliderAdapter;
    TextView[]  dots;
    Button getStarted;
    Animation animation;
    int currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);

        //Hooks
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        getStarted = findViewById(R.id.get_started_btn);

        //Call adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    public void skip(View view){
        startActivity(new Intent(Onboard.this, Welcome.class));
    }

    public void next(View view){
        viewPager.setCurrentItem(currentPos+1);
    }

    private void addDots(int position){

        dots = new TextView[3];
        dotsLayout.removeAllViews();

        for(int i=0; i<dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);
        }

        if(dots.length > 0 ){
            dots[position].setTextColor(getResources().getColor(R.color.color_tertiary));
        }
    }

    public void getStarted(View view){
        startActivity(new Intent(Onboard.this, Welcome.class));
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPos = position;

            if(position==0){
                getStarted.setVisibility(View.INVISIBLE);
            }else if(position==1){
                getStarted.setVisibility(View.INVISIBLE);
            }else{
                animation = AnimationUtils.loadAnimation(Onboard.this,R.anim.side_anim);
                getStarted.setAnimation(animation);
                getStarted.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}