package com.example.votecast.user.freecoins;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.votecast.R;
import com.example.votecast.activity.BaseActivity;

public class FreeDimondsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_dimonds);
    }

    public void onClickCopy(View view) {
        Log.d("TAG", "onClickCopy: ");
    }

    public void onClickShare(View view) {
        Log.d("TAG", "onClickCopy: 1");
    }

    public void onClickSubmit(View view) {
        Log.d("TAG", "onClickCopy:w ");
    }
}