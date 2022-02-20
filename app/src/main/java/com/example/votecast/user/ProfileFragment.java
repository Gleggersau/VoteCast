package com.example.votecast.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.votecast.R;
import com.example.votecast.SessionManager;
import com.example.votecast.activity.SettingActivity;
import com.example.votecast.databinding.FragmentProfileBinding;
import com.example.votecast.models.User;
import com.example.votecast.posts.FeedGridActivity;
import com.example.votecast.reels.VideoListGridActivity;
import com.example.votecast.retrofit.Const;
import com.example.votecast.user.freecoins.FreeDimondsActivity;
import com.example.votecast.user.vip.VipPlanActivity;
import com.example.votecast.user.wallet.MyWalletActivity;
import com.google.gson.Gson;


public class ProfileFragment extends Fragment {


    FragmentProfileBinding binding;
    SessionManager sessionManager;
    private User user;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        sessionManager = new SessionManager(getActivity());
        initView();
        initListner();
        return binding.getRoot();
    }

    private void initListner() {
        binding.btnSetting.setOnClickListener(v -> startActivity(new Intent(getActivity(), SettingActivity.class)));
        binding.lytMyPost.setOnClickListener(v -> startActivity(new Intent(getActivity(), FeedGridActivity.class).putExtra(Const.DATA, new Gson().toJson(user))));
        binding.lytMyPost2.setOnClickListener(v -> startActivity(new Intent(getActivity(), FeedGridActivity.class).putExtra(Const.DATA, new Gson().toJson(user))));
        binding.lytMyVideos.setOnClickListener(v -> startActivity(new Intent(getActivity(), VideoListGridActivity.class).putExtra(Const.DATA, new Gson().toJson(user))));
        binding.lytMyVideos2.setOnClickListener(v -> startActivity(new Intent(getActivity(), VideoListGridActivity.class).putExtra(Const.DATA, new Gson().toJson(user))));
        binding.lytFollowrs.setOnClickListener(v -> startActivity(new Intent(getActivity(), FollowrsListActivity.class).putExtra(Const.DATA, new Gson().toJson(user))));
        binding.btnEditProfile.setOnClickListener(v -> startActivity(new Intent(getActivity(), EditProfileActivity.class)));
        binding.tvLevel.setOnClickListener(v -> startActivity(new Intent(getActivity(), MyLevelListActivity.class)));
        binding.lytVIP.setOnClickListener(v -> startActivity(new Intent(getActivity(), VipPlanActivity.class)));
        binding.lytWallet.setOnClickListener(v -> startActivity(new Intent(getActivity(), MyWalletActivity.class)));

    }

    private void initView() {

        user = sessionManager.getUser();
        Glide.with(getActivity()).load(user.getImage()).circleCrop().into(binding.imgUser);
        binding.tvUserName.setText(user.getName());
        binding.tvAge.setText(String.valueOf(user.getAge()));

        binding.tvFollowrs.setText(String.valueOf(user.getFollowres()));
        binding.tvLevel.setText("Lv. " + user.getLevel());
        binding.tvPosts.setText(String.valueOf(user.getPosts()));
        binding.tvVideos.setText(String.valueOf(user.getVideos()));
        binding.tvUserId.setText("ID: " + user.getId());

        if (user.getGender().equalsIgnoreCase("male")) {
            binding.imgGender.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.male));
        } else {
            binding.imgGender.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.female));
        }
    }
}