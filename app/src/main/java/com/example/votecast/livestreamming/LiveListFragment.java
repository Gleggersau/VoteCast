package com.example.votecast.livestreamming;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.votecast.R;
import com.example.votecast.databinding.FragmentLiveListBinding;
import com.example.votecast.z_demo.Demo_contents;


public class LiveListFragment extends Fragment {

    VideoListAdapter videoListAdapter = new VideoListAdapter();
    FragmentLiveListBinding binding;

    public LiveListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_live_list, container, false);
        initView();
        return binding.getRoot();

    }

    private void initView() {
        binding.rvFeed.setAdapter(videoListAdapter);

        videoListAdapter.addData(Demo_contents.getUsers(true));
    }
}