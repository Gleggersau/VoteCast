package com.example.votecast.videocall;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.votecast.R;
import com.example.votecast.activity.BaseActivity;
import com.example.votecast.databinding.ActivityCallIncomeBinding;

public class CallIncomeActivity extends BaseActivity {
    ActivityCallIncomeBinding binding;
    private Vibrator v;
    private MediaPlayer player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_call_income);

        Glide.with(this).load(R.drawable.girl).circleCrop().into(binding.imgUser);



        initListner();
    }

    private void initListner() {
        binding.btnAccept.setOnClickListener(v1 -> {
            startActivity(new Intent(this, VideoCallActivity.class));
            finish();
        });
        binding.btnDecline.setOnClickListener(v1 -> onBackPressed());
    }


    @Override
    protected void onPause() {
        if (v != null) {
            v.cancel();
        }
        if (player2 != null) {
            player2.release();
        }

        super.onPause();

    }

}