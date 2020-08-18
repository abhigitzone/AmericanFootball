package com.example.scorekeep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splash_screen extends AppCompatActivity {
    TextView app_name;
    ImageView imgLoad;
    final int splashTimeOut = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        app_name = findViewById(R.id.splash_txt);
        imgLoad = findViewById(R.id.loadImg);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash_screen.this, MainActivity.class);
                startActivity(intent);
            }
        }, splashTimeOut);

        //Handling Animation here..
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_anim);
        app_name.startAnimation(animation);

        Animation img = AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise);
        imgLoad.startAnimation(img);
    }
}