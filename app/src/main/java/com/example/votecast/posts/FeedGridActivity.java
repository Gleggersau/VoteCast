package com.example.votecast.posts;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.example.votecast.R;
import com.example.votecast.SessionManager;
import com.example.votecast.activity.BaseActivity;
import com.example.votecast.databinding.ActivityMyFeedBinding;
import com.example.votecast.models.Post;
import com.example.votecast.models.User;
import com.example.votecast.retrofit.Const;
import com.example.votecast.z_demo.Demo_contents;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class FeedGridActivity extends BaseActivity {
    ActivityMyFeedBinding binding;
    FeedGridAdapter feedGridAdapter = new FeedGridAdapter();
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_feed);
        sessionManager = new SessionManager(this);

        Intent intent = getIntent();
        User user = new Gson().fromJson(intent.getStringExtra(Const.DATA), User.class);
        binding.rvFeed.setAdapter(feedGridAdapter);

        List<Post> list = new ArrayList<>();

        for (int i = 0; i < user.getPosts(); i++) {
            list.add(Demo_contents.getPosts().get(0));
        }

        feedGridAdapter.addData(list);
        feedGridAdapter.setOnFeedGridAdapterClickLisnter(position -> startActivity(new Intent(this, FeedListActivity.class).putExtra(Const.POSITION, position).putExtra(Const.DATA, new Gson().toJson(feedGridAdapter.getList()))));
    }


}