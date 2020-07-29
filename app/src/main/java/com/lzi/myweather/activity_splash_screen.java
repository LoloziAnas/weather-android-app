package com.lzi.myweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class activity_splash_screen extends AppCompatActivity {

    private Animation top_anim, bottom_anim;
    private ImageView img_logo;
    private TextView tv_logo;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(getWindowManager().LayoutParams.FLAG_FULLSCREEN,getWindowManager().LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_splash_screen);

        img_logo = findViewById(R.id.logo);
        tv_logo = findViewById(R.id.tv_logo);

        // Animation initialized
        top_anim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom_anim = AnimationUtils.loadAnimation(this,R.anim.buttom_animation);

        // Set animation
        img_logo.setAnimation(top_anim);
        tv_logo.setAnimation(bottom_anim);

        //handler
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(activity_splash_screen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);

    }

    private void animate(){

    }
}
