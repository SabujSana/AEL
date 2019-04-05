package com.example.sabuj.ael.activities;

import android.content.Intent;
import android.net.ConnectivityManager;
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
    private ConnectivityManager connectivityManager;

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
        ivSplash.setImageDrawable(getResources().getDrawable(R.drawable.splash_anim));
//        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
//                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
//            Ion.with(ivSplash).load("http://supdogs.com/wp-content/themes/resto/assets/images/load.gif");
//        } else {
//
//        }


        if (!preferenceManager.isFirstTimeLaunch()) {
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (preferenceManager.getIsLoggedIn()) {
                        startActivity(new Intent(SecondarySplashScreenActivity.this, HomeActivity.class));
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
