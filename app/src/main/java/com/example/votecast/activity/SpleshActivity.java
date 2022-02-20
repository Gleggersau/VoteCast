package com.example.votecast.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.example.votecast.R;
import com.example.votecast.SessionManager;
import com.example.votecast.retrofit.Const;

public class SpleshActivity extends AppCompatActivity {
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splesh);
        sessionManager = new SessionManager(this);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.splesh);
        anim.setFillAfter(true);
        findViewById(R.id.tv1).setVisibility(View.VISIBLE);
        findViewById(R.id.tv1).startAnimation(anim);
        new Handler(Looper.myLooper()).postDelayed(() -> {

            if (sessionManager.getBooleanValue(Const.ISLOGIN)) {
                startActivity(new Intent(SpleshActivity.this, MainActivity.class));
            } else {
                startActivity(new Intent(SpleshActivity.this, LoginActivityActivity.class));
            }
        }, 1500);
    }
}