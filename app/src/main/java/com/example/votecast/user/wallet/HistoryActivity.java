package com.example.votecast.user.wallet;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.example.votecast.R;
import com.example.votecast.activity.BaseActivity;
import com.example.votecast.databinding.ActivityHistoryBinding;

import java.util.Random;

public class HistoryActivity extends BaseActivity {
    ActivityHistoryBinding binding;

    String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_history);

        Random random = new Random();
        binding.tvIncome.setText(String.valueOf(random.nextInt(999)));
        binding.tvOutcome.setText(String.valueOf(random.nextInt(999)));

        binding.rvHistory.setAdapter(new CoinHistoryAdapter());
        initDatePiker();



        binding.tvDateDimonds.setText(selectedDate);
    }

    private void initDatePiker() {
        binding.lytDatePicker.lytDatePicker.setVisibility(View.GONE);

        binding.lytDateDimonds.setOnClickListener(v -> binding.lytDatePicker.lytDatePicker.setVisibility(View.VISIBLE));

    }

}