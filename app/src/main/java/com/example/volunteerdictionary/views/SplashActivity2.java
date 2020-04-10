package com.example.volunteerdictionary.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.volunteerdictionary.R;

public class SplashActivity2 extends AppCompatActivity {

    private final int SPLASH_DISPLAY_DURATION = 2000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refreshment_splash2);
        new Handler().postDelayed(() -> {
            Intent mainIntent = new Intent(SplashActivity2.this,MainActivity.class);
            SplashActivity2.this.startActivity(mainIntent);
            SplashActivity2.this.finish();
        }, SPLASH_DISPLAY_DURATION);
    }
}
