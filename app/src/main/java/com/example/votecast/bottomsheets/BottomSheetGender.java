package com.example.votecast.bottomsheets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.example.votecast.R;
import com.example.votecast.activity.LoginActivityActivity;
import com.example.votecast.databinding.BottomSheetGenderBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class BottomSheetGender {

    private final BottomSheetDialog bottomSheetDialog;
    private String selected = "";
    boolean submitButtonEnable = false;

    public BottomSheetGender(LoginActivityActivity.LoginType loginType, Context context, OnGenderSelectListner onReportedListner) {
        bottomSheetDialog = new BottomSheetDialog(context, R.style.CustomBottomSheetDialogTheme);
        bottomSheetDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        bottomSheetDialog.setOnShowListener(dialog -> {
            BottomSheetDialog d = (BottomSheetDialog) dialog;
            FrameLayout bottomSheet = (FrameLayout) d.findViewById(R.id.design_bottom_sheet);
            BottomSheetBehavior.from(bottomSheet)
                    .setState(BottomSheetBehavior.STATE_EXPANDED);
        });


        BottomSheetGenderBinding sheetDilogBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.bottom_sheet_gender, null, false);
        bottomSheetDialog.setContentView(sheetDilogBinding.getRoot());
        bottomSheetDialog.show();
        sheetDilogBinding.male.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_stok_round));
        sheetDilogBinding.female.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_stok_round));
        sheetDilogBinding.male.setOnClickListener(v -> {
            selected = "MALE";
            sheetDilogBinding.male.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_stok_round_pink));
            sheetDilogBinding.female.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_stok_round));
        });
        sheetDilogBinding.female.setOnClickListener(v -> {
            selected = "FEMALE";
            sheetDilogBinding.female.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_stok_round_pink));
            sheetDilogBinding.male.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_stok_round));
        });
        if (loginType==LoginActivityActivity.LoginType.QUICK) {
            sheetDilogBinding.lytname.setVisibility(View.VISIBLE);
        } else {
            sheetDilogBinding.lytname.setVisibility(View.GONE);
        }

        sheetDilogBinding.btnSubmit.setOnClickListener(v -> {
            String name = sheetDilogBinding.etName.getText().toString();
            if (loginType==LoginActivityActivity.LoginType.QUICK && name.isEmpty()) {
                Toast.makeText(context, "Enter Name First", Toast.LENGTH_SHORT).show();
                return;
            }
            if (selected.isEmpty()) {
                Toast.makeText(context, "Select Gender First", Toast.LENGTH_SHORT).show();
                return;
            }
            onReportedListner.onSelect(selected, name);
            bottomSheetDialog.dismiss();
        });

    }

    public interface OnGenderSelectListner {
        void onSelect(String g, String name);

    }
}
