package com.example.myfoodapplication.Activity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.example.myfoodapplication.Broadcast.InternetCheckService;
import com.example.myfoodapplication.R;

public class MainActivity extends AppCompatActivity {
    ImageView logo, back;
    TextView name_app;
    LottieAnimationView lottieAnimationView;
    private static int duree = 5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.logo);
        back = findViewById(R.id.back);
        lottieAnimationView = findViewById(R.id.json);
        name_app = findViewById(R.id.app_name);

      logo.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
        back.animate().translationY(1800).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        name_app.animate().translationY(1400).setDuration(1000).setStartDelay(4000);

       new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Login2.class);
                startActivity(intent);
                finish();
            }
        }, duree);
    }


}