package com.example.votecast.user.guestuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.votecast.R;
import com.example.votecast.databinding.FragmentGuestUserPostsBinding;
import com.example.votecast.models.Post;
import com.example.votecast.models.User;
import com.example.votecast.posts.FeedGridAdapter;
import com.example.votecast.posts.FeedListActivity;
import com.example.votecast.retrofit.Const;
import com.example.votecast.z_demo.Demo_contents;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class GuestUserPostsFragment extends Fragment {


    FragmentGuestUserPostsBinding binding;
    FeedGridAdapter feedGridAdapter = new FeedGridAdapter();
    private User user;

    public GuestUserPostsFragment(User user) {
        // Required empty public constructor
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_guest_user_posts, container, false);
        initMain();
        return binding.getRoot();
    }

    private void initMain() {
        List<Post> list = new ArrayList<>();

        for (int i = 0; i < user.getPosts(); i++) {
            list.add(Demo_contents.getPosts().get(0));
        }
        feedGridAdapter.addData(list);
        binding.rvFeed.setAdapter(feedGridAdapter);

        feedGridAdapter.setOnFeedGridAdapterClickLisnter(new FeedGridAdapter.OnFeedGridAdapterClickLisnter() {
            @Override
            public void onFeedClick(int position) {
                startActivity(new Intent(getActivity(), FeedListActivity.class).putExtra(Const.POSITION, position).putExtra(Const.DATA, new Gson().toJson(feedGridAdapter.getList())));

            }
        });
    }
}