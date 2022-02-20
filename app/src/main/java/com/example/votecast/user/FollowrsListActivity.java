package com.example.votecast.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.example.votecast.R;
import com.example.votecast.activity.BaseActivity;
import com.example.votecast.databinding.ActivityFollowrsListBinding;
import com.example.votecast.models.User;
import com.example.votecast.retrofit.Const;
import com.example.votecast.z_demo.Demo_contents;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class FollowrsListActivity extends BaseActivity {
    ActivityFollowrsListBinding binding;
    FollowrsUsersAdapter followrsUsersAdapter = new FollowrsUsersAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_followrs_list);
        Intent intent = getIntent();
        User user = new Gson().fromJson(intent.getStringExtra(Const.DATA), User.class);

        List<User> list = new ArrayList<>();

        for (int i = 0; i < user.getPosts(); i++) {
            list.add(Demo_contents.getUsers(true).get(0));
        }

        binding.rvFeed.setAdapter(followrsUsersAdapter);
        followrsUsersAdapter.addData(list);
    }
}