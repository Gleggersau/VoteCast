
package com.example.votecast.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.votecast.R;
import com.example.votecast.chat.MessageFragment;
import com.example.votecast.databinding.ActivityMainBinding;
import com.example.votecast.databinding.BottomSheetChoicesBinding;
import com.example.votecast.home.HomeFragment;
import com.example.votecast.livestreamming.GotoLiveActivity;
import com.example.votecast.posts.FeedFragmentMain;
import com.example.votecast.posts.UploadPostActivity;
import com.example.votecast.user.ProfileFragment;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends BaseActivity {
    ActivityMainBinding binding;
    private BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        setDefaultBottomBar();
        setUpFragment(new HomeFragment(), binding.animHome);
        initBottomBar();
        binding.imgBall.setOnClickListener(v -> {
            bottomSheetDialog = new BottomSheetDialog(this, R.style.CustomBottomSheetDialogTheme);
            bottomSheetDialog.setOnShowListener(dialog -> {
                BottomSheetDialog d = (BottomSheetDialog) dialog;
                FrameLayout bottomSheet = d.findViewById(R.id.design_bottom_sheet);
                if (bottomSheet != null) {
                    BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            });

            BottomSheetChoicesBinding bottomSheetChoicesBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.bottom_sheet_choices, null, false);
            bottomSheetDialog.setContentView(bottomSheetChoicesBinding.getRoot());
            bottomSheetDialog.show();
            bottomSheetChoicesBinding.imgClose.setOnClickListener(v1 -> bottomSheetDialog.dismiss());
            bottomSheetChoicesBinding.lytLive.setOnClickListener(v1 -> {
                bottomSheetDialog.dismiss();
                startActivity(new Intent(this, GotoLiveActivity.class));
            });

            bottomSheetChoicesBinding.lytVideos.setOnClickListener(v1 -> {
                bottomSheetDialog.dismiss();

            });

            bottomSheetChoicesBinding.lytMoments.setOnClickListener(v1 -> {
                bottomSheetDialog.dismiss();
                startActivity(new Intent(this, UploadPostActivity.class));
            });


        });
    }

    private void setDefaultBottomBar() {
        binding.animHome.setImageTintList(ContextCompat.getColorStateList(this, R.color.gray_icon));
        binding.animDiscover.setImageTintList(ContextCompat.getColorStateList(this, R.color.gray_icon));
        binding.animChat.setImageTintList(ContextCompat.getColorStateList(this, R.color.gray_icon));
        binding.animProfile.setImageTintList(ContextCompat.getColorStateList(this, R.color.gray_icon));
    }

    private void setUpFragment(Fragment fragment, ImageView animHome) {
        setDefaultBottomBar();
        animHome.setImageTintList(ContextCompat.getColorStateList(this, R.color.offwhite));
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();
    }

    private void initBottomBar() {
        binding.lytHome.setOnClickListener(v -> setUpFragment(new HomeFragment(), binding.animHome));
        binding.lytDiscover.setOnClickListener(v -> setUpFragment(new FeedFragmentMain(), binding.animDiscover));
        binding.lytChat.setOnClickListener(v -> setUpFragment(new MessageFragment(), binding.animChat));
        binding.lytProfile.setOnClickListener(v -> setUpFragment(new ProfileFragment(), binding.animProfile));
    }


}