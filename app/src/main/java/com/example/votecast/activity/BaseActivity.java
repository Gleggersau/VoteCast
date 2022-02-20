package com.example.votecast.activity;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {


    public void onClickBack(View view) {
        onBackPressed();
    }
}
