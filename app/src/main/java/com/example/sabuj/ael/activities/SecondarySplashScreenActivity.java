package com.example.sabuj.ael.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.sabuj.ael.MainActivity;
import com.example.sabuj.ael.R;
import com.example.sabuj.ael.utils.SharedPreferenceManager;
import com.koushikdutta.ion.Ion;

public class SecondarySplashScreenActivity extends AppCompatActivity {
    private SharedPreferenceManager preferenceManager;
    private ImageView ivSplash;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary_splash_screen);
        preferenceManager = new SharedPreferenceManager(this);

        if (preferenceManager.isFirstTimeLaunch()) {
            startActivity(new Intent(SecondarySplashScreenActivity.this, SplashScreenActivity.class));
            finish();
        }

        ivSplash = findViewById(R.id.iv_splash_gif);
        Ion.with(ivSplash).load("http://supdogs.com/wp-content/themes/resto/assets/images/load.gif");

        if (!preferenceManager.isFirstTimeLaunch()) {
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (preferenceManager.getIsLoggedIn()) {
                        startActivity(new Intent(SecondarySplashScreenActivity.this, MainActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(SecondarySplashScreenActivity.this, LoginActivity.class));
                        finish();
                    }
                }
            }, 3000);
        }

    }
}
