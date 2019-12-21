package com.example.newsapp;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_bar);
        textView = findViewById(R.id.loading_text);


        progressBar.setMax(100);
        progressBar.setScaleY(3f);

        progressAnimation();
    }

    public void progressAnimation() {
        SplashScreenActivity anim = new SplashScreenActivity(this,progressBar,textView, 0f, 100f);
        anim.setDuration(6000);
        progressBar.setAnimation(anim);
    }
}
