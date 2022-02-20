package com.example.votecast.videocall;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.votecast.R;
import com.example.votecast.activity.BaseActivity;
import com.example.votecast.databinding.ActivityCallRequestBinding;

public class CallRequestActivity extends BaseActivity {

    ActivityCallRequestBinding binding;
    Handler handler = new Handler();
    private int sec = 0;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (sec >= 3) {
                binding.tvStatus.setText("Ringing...");
            }
            handler.postDelayed(this, 1000);
            if (sec >= 5) {

                handler.removeCallbacks(runnable);
                startActivity(new Intent(CallRequestActivity.this, VideoCallActivity.class));

            }
            sec++;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_call_request);

        Glide.with(this).load(R.drawable.girl).circleCrop().into(binding.imgUser);
        handler.postDelayed(runnable, 1000);
        binding.btnDecline.setOnClickListener(v -> onBackPressed());
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        handler.removeCallbacks(runnable);
        finish();
    }
}