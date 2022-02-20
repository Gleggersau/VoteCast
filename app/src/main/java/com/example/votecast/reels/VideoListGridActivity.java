package com.example.votecast.reels;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.example.votecast.R;
import com.example.votecast.SessionManager;
import com.example.votecast.activity.BaseActivity;
import com.example.votecast.databinding.ActivityVideoListGridBinding;
import com.example.votecast.models.Reels;
import com.example.votecast.models.User;
import com.example.votecast.retrofit.Const;
import com.example.votecast.z_demo.Demo_contents;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class VideoListGridActivity extends BaseActivity {
    ActivityVideoListGridBinding binding;
    ProfileVideoGridAdapter profileVideoGridAdapter = new ProfileVideoGridAdapter();
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_video_list_grid);
        sessionManager = new SessionManager(this);
        Intent intent = getIntent();
        User user = new Gson().fromJson(intent.getStringExtra(Const.DATA), User.class);
        binding.rvFeed.setAdapter(profileVideoGridAdapter);

        List<Reels> list = new ArrayList<>();

        for (int i = 0; i < user.getVideos(); i++) {
            list.add(Demo_contents.getReels().get(0));
        }

        profileVideoGridAdapter.addData(list);

    }
}