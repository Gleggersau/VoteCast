package com.example.votecast.user.wallet;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.example.votecast.R;
import com.example.votecast.activity.BaseActivity;
import com.example.votecast.databinding.ActivityCashOutBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CashOutActivity extends BaseActivity {
    ActivityCashOutBinding binding;

    List<String> paymentGateways = new ArrayList<>(Arrays.asList("PAYTM", "UPI", "BANK"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cash_out);
        changeDetails(paymentGateways.get(0));
        binding.rvReedemMethods.setAdapter(new ReedemMethodAdapter(paymentGateways, s -> changeDetails(s)));
        binding.rvHistory.setAdapter(new ReedemHistoryAdapter());


    }

    private void changeDetails(String s) {
        if (s.equalsIgnoreCase("PAYTM")) {
            binding.etDetails.setText("Enter your paytm mobile number");
        } else if (s.equalsIgnoreCase("upi")) {
            binding.etDetails.setText("Enter your UPI id:\n Ex. xxxx123@upi");
        } else {
            binding.etDetails.setText("Enter your Bank Details");
        }
    }
}