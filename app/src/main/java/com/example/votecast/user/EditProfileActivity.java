package com.example.votecast.user;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.votecast.R;
import com.example.votecast.SessionManager;
import com.example.votecast.activity.BaseActivity;
import com.example.votecast.databinding.ActivityEditProfileBinding;
import com.example.votecast.models.User;

public class EditProfileActivity extends BaseActivity {
    ActivityEditProfileBinding binding;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);
        sessionManager = new SessionManager(this);
        User user = sessionManager.getUser();
        Glide.with(this).load(user.getImage()).circleCrop().into(binding.imgUser);
        binding.etName.setText(user.getName());
        binding.etBio.setText(user.getBio());
        binding.etEmail.setText(user.getEmail());
    }
}