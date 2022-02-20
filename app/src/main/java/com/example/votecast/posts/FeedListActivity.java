package com.example.votecast.posts;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.databinding.DataBindingUtil;

import com.example.votecast.R;
import com.example.votecast.activity.BaseActivity;
import com.example.votecast.comments.CommentFragment;
import com.example.votecast.databinding.ActivityFeedListBinding;
import com.example.votecast.databinding.ItemFeedBinding;
import com.example.votecast.models.Post;
import com.example.votecast.retrofit.Const;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class FeedListActivity extends BaseActivity implements FeedAdapter.OnPostClickListner {
    ActivityFeedListBinding binding;
    FeedAdapter feedAdapter = new FeedAdapter();
    int position;
    private List<Post> posts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feed_list);

        Intent intent = getIntent();
        position = intent.getIntExtra(Const.POSITION, 0);
        String data = intent.getStringExtra(Const.DATA);
        if (data!=null && !data.isEmpty()) {
            posts = new Gson().fromJson(data, new TypeToken<ArrayList<Post>>() {
            }.getType());
        }

        binding.rvFeed.setAdapter(feedAdapter);
        feedAdapter.addData(posts);
        binding.rvFeed.scrollToPosition(position);


        feedAdapter.setOnPostClickListner(this);
    }

    @Override
    public void onLikeClick(Post post, ItemFeedBinding binding) {
        Log.d("TAG", "onLikeClick: ");
    }

    @Override
    public void onCommentClick(Post post) {
        new CommentFragment().show(getSupportFragmentManager(), CommentFragment.class.getSimpleName());
    }

    @Override
    public void onShareClick(Post post) {
        Log.d("TAG", "onShareClick: ");

    }
}