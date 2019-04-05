package com.example.sabuj.ael.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.sabuj.ael.R;
import com.example.sabuj.ael.utils.SharedPreferenceManager;

public class SecondarySplashScreenActivity extends AppCompatActivity {
    private SharedPreferenceManager preferenceManager;
    private ImageView ivSplash;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary_splash_screen);
        preferenceManager = new SharedPreferenceManager(this);

        
    }
}
