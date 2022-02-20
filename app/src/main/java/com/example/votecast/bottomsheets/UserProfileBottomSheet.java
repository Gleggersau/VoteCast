package com.example.votecast.bottomsheets;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.votecast.R;
import com.example.votecast.databinding.BottomSheetUserProfileBinding;
import com.example.votecast.models.User;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class UserProfileBottomSheet {
    private final BottomSheetDialog bottomSheetDialog;

    public UserProfileBottomSheet(Context context, boolean isHost, User user) {
        bottomSheetDialog = new BottomSheetDialog(context, R.style.CustomBottomSheetDialogTheme);
        bottomSheetDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        bottomSheetDialog.setOnShowListener(dialog -> {
            BottomSheetDialog d = (BottomSheetDialog) dialog;
            FrameLayout bottomSheet = (FrameLayout) d.findViewById(R.id.design_bottom_sheet);
            BottomSheetBehavior.from(bottomSheet)
                    .setState(BottomSheetBehavior.STATE_EXPANDED);
        });

        BottomSheetUserProfileBinding sheetDilogBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.bottom_sheet_user_profile, null, false);
        bottomSheetDialog.setContentView(sheetDilogBinding.getRoot());
        bottomSheetDialog.show();

        Glide.with(context).load(R.drawable.dummy_user).circleCrop().into(sheetDilogBinding.imgUser);
        sheetDilogBinding.btnClose.setOnClickListener(v -> bottomSheetDialog.dismiss());
        if (isHost) {
            sheetDilogBinding.btnMessage.setVisibility(View.GONE);
            sheetDilogBinding.btnBlock.setVisibility(View.VISIBLE);
        } else {
            sheetDilogBinding.btnMessage.setVisibility(View.VISIBLE);
            sheetDilogBinding.btnBlock.setVisibility(View.GONE);
        }

        if (user!=null) {
            Glide.with(context).load(user.getImage()).circleCrop().into(sheetDilogBinding.imgUser);
            sheetDilogBinding.tvUserId.setText("ID: " + user.getId());
            sheetDilogBinding.tvName.setText(user.getName());
            sheetDilogBinding.tvAge.setText(String.valueOf(user.getAge()));
            sheetDilogBinding.tvCountry.setText(user.getCountry());
            sheetDilogBinding.tvLevel.setText("LV. " + user.getLevel());
            sheetDilogBinding.tvPosts.setText(String.valueOf(user.getPosts()));
            sheetDilogBinding.tvFollowrs.setText(String.valueOf(user.getFollowres()));
            sheetDilogBinding.tvVideos.setText(String.valueOf(user.getVideos()));
        }


        sheetDilogBinding.pdFollow.setVisibility(View.GONE);


        sheetDilogBinding.lytFollowUnfollow.setOnClickListener(v -> {
            sheetDilogBinding.lytFollowUnfollow.setEnabled(false);
            sheetDilogBinding.pdFollow.setVisibility(View.VISIBLE);
            sheetDilogBinding.tvFollowStatus.setVisibility(View.INVISIBLE);

            new Handler(Looper.myLooper()).postDelayed(() -> {
                if (sheetDilogBinding.tvFollowStatus.getText().toString().equalsIgnoreCase("follow")) {
                    sheetDilogBinding.tvFollowStatus.setText("UNFOLLOW");
                    sheetDilogBinding.lytFollowUnfollow.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.graylight));
                } else {
                    sheetDilogBinding.tvFollowStatus.setText("FOLLOW");
                    sheetDilogBinding.lytFollowUnfollow.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.pink));
                }
                sheetDilogBinding.pdFollow.setVisibility(View.GONE);
                sheetDilogBinding.tvFollowStatus.setVisibility(View.VISIBLE);
                sheetDilogBinding.lytFollowUnfollow.setEnabled(true);
            }, 1000);
        });

    }
}
