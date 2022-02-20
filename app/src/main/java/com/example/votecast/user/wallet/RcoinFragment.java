package com.example.votecast.user.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.votecast.R;
import com.example.votecast.databinding.FragmentRcoinBinding;
import com.example.votecast.popups.PopupBuilder;

public class RcoinFragment extends Fragment {


    int myRcoin = 300;
    FragmentRcoinBinding binding;

    public RcoinFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rcoin, container, false);

        initMain();
        return binding.getRoot();
    }

    private void initMain() {
        binding.tvRcoin.setText(String.valueOf(myRcoin));
        binding.btnConvert.setOnClickListener(v -> {
            double dimonds = myRcoin / 100;
            PopupBuilder popupBuilder = new PopupBuilder(getActivity());
            String s = "Your " + myRcoin + " Rcoin Successfully Converted into " + dimonds + " Diamonds";
            popupBuilder.showSimplePopup(s, "Continue");
        });
        binding.btnCashout.setOnClickListener(v -> startActivity(new Intent(getActivity(), CashOutActivity.class)));

    }
}