package com.lzi.myweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class activity_splash_screen extends AppCompatActivity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(getWindowManager().LayoutParams.FLAG_FULLSCREEN,getWindowManager().LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_splash_screen);

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
}
