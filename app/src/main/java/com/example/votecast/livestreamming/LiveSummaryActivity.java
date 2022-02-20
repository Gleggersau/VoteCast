package com.example.votecast.livestreamming;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.votecast.R;
import com.example.votecast.activity.BaseActivity;
import com.example.votecast.databinding.ActivityLiveSummaryBinding;

public class LiveSummaryActivity extends BaseActivity {
    ActivityLiveSummaryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_live_summary);


        Glide.with(this).load(R.drawable.girl).circleCrop().into(binding.imgUser);
        binding.btnHomePage.setOnClickListener(v -> onBackPressed());
    }
}