package com.example.votecast.videocall;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.votecast.R;
import com.example.votecast.activity.BaseActivity;
import com.example.votecast.databinding.ActivityRandomMatchBinding;

public class RandomMatchActivity extends BaseActivity {
    ActivityRandomMatchBinding binding;
    Handler handler = new Handler(Looper.getMainLooper());
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            binding.ivUser.clearAnimation();
            binding.ivUser2.setVisibility(View.VISIBLE);
            binding.btnMatch.setVisibility(View.VISIBLE);
            binding.btnCall.setVisibility(View.VISIBLE);
            binding.ivMatch.setVisibility(View.GONE);
            binding.lytStatus.setText("Matched with Sofia adams");
        }
    };
    Animation zoomin;
    private Animation animZoomin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_random_match);


        Glide.with(this.getApplicationContext())
                .load(R.drawable.girl)
                .circleCrop()
                .into(binding.ivUser);
        Glide.with(this).load(R.drawable.dummy_user).circleCrop().into(binding.ivUser2);

        zoomin = AnimationUtils.loadAnimation(this, R.anim.zoomin);
        animZoomin = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoomin);


        matchAgain();


        binding.btnMatch.setOnClickListener(v -> matchAgain());
        binding.btnCall.setOnClickListener(v -> makeACall());
    }

    private void makeACall() {
        onBackPressed();
        startActivity(new Intent(this, CallRequestActivity.class));
    }

    private void matchAgain() {
        binding.lytStatus.setText("Searching for new Friends...");
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 3000);
        binding.ivUser2.setVisibility(View.GONE);
        binding.btnCall.setVisibility(View.GONE);
        binding.btnMatch.setVisibility(View.GONE);
        binding.ivUser.startAnimation(animZoomin);
        binding.ivMatch.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handler.removeCallbacks(runnable);
    }
}