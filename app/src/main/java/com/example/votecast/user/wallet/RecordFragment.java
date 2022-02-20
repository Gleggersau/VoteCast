package com.example.votecast.user.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.votecast.R;
import com.example.votecast.databinding.FragmentMyRecordBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Random;


public class RecordFragment extends Fragment {


    FragmentMyRecordBinding binding;
    BottomSheetDialog bottomSheetDialog;

    private int selectedType = 1;
    private String selectedDate = "";

    public RecordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_record, container, false);
        initMain();
        initDatePiker();
        initListner();


        binding.tvDateRcoin.setText(selectedDate);
        binding.tvDateDimonds.setText(selectedDate);

        binding.lytDiamonds.setOnClickListener(v -> startActivity(new Intent(getActivity(), HistoryActivity.class)));
        binding.lytRcoins.setOnClickListener(v -> startActivity(new Intent(getActivity(), HistoryActivity.class)));


        return binding.getRoot();
    }

    private void initDatePiker() {
        binding.lytDatePicker.lytDatePicker.setVisibility(View.GONE);

        binding.lytDatePicker.tvCancel.setOnClickListener(v -> binding.lytDatePicker.lytDatePicker.setVisibility(View.GONE));
        binding.lytDatePicker.tvConfirm.setOnClickListener(v -> {
            binding.lytDatePicker.lytDatePicker.setVisibility(View.GONE);
            if (selectedType==1) {
                binding.tvDateDimonds.setText(selectedDate);
            } else {
                binding.tvDateRcoin.setText(selectedDate);
            }

        });


    }

    private void initListner() {
        binding.lytDateDimonds.setOnClickListener(v -> {
            selectedType = 1;
            binding.lytDatePicker.lytDatePicker.setVisibility(View.VISIBLE);
        });
        binding.lytDateRcoins.setOnClickListener(v -> {
            selectedType = 2;
            binding.lytDatePicker.lytDatePicker.setVisibility(View.VISIBLE);
        });

    }

    private void initMain() {

        Random random = new Random();
        binding.tvRcoinIncome.setText(String.valueOf(random.nextInt(999)));
        binding.tvRcoinOutcome.setText(String.valueOf(random.nextInt(999)));
        binding.tvDimondsIncome.setText(String.valueOf(random.nextInt(999)));
        binding.tvDiamondsOutcome.setText(String.valueOf(random.nextInt(999)));
    }
}