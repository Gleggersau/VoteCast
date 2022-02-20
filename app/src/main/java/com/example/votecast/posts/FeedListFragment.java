package com.example.votecast.posts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.votecast.R;
import com.example.votecast.comments.CommentFragment;
import com.example.votecast.databinding.FragmentFeedListBinding;
import com.example.votecast.databinding.ItemFeedBinding;
import com.example.votecast.models.Post;
import com.example.votecast.z_demo.Demo_contents;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class FeedListFragment extends Fragment implements FeedAdapter.OnPostClickListner {


    FragmentFeedListBinding binding;
    FeedAdapter feedAdapter = new FeedAdapter();

    public FeedListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed_list, container, false);
        initView();
        Log.d("TAG", "onCreateView: feefvff");
        return binding.getRoot();
    }

    private void initView() {
        binding.rvFeed.setAdapter(feedAdapter);
        feedAdapter.addData(Demo_contents.getPosts());


        feedAdapter.setOnPostClickListner(this);
    }

    @Override
    public void onLikeClick(Post post, ItemFeedBinding binding) {
        Log.d("TAG", "onLikeClick: ");
    }

    @Override
    public void onCommentClick(Post post) {
        openFramgnet(new CommentFragment(post));

    }

    private void openFramgnet(BottomSheetDialogFragment bottomSheetDialogFragment) {
        bottomSheetDialogFragment.show(getChildFragmentManager(), bottomSheetDialogFragment.getClass().getSimpleName());
    }

    @Override
    public void onShareClick(Post post) {
        Log.d("TAG", "onShareClick: ");
    }
}