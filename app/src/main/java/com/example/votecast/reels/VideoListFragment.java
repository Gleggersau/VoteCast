package com.example.votecast.reels;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.PagerSnapHelper;

import com.example.votecast.R;
import com.example.votecast.databinding.FragmentVideoListBinding;
import com.example.votecast.databinding.ItemReelsBinding;
import com.example.votecast.home.HomeFragment;
import com.example.votecast.livestreamming.LiveFragmentMain;
import com.example.votecast.viewmodel.ReelsViewModel;
import com.example.votecast.viewmodel.ViewModelFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class VideoListFragment extends Fragment implements Player.EventListener {


    private static final String TAG = "videolistfragment";

    FragmentVideoListBinding binding;
    private SimpleExoPlayer player;
    ItemReelsBinding playerBinding;
    private int lastPosition = -1;
    private Animation animation;
    private ReelsViewModel viewModel;

    public VideoListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_video_list, container, false);
        viewModel = ViewModelProviders.of(this, new ViewModelFactory(new ReelsViewModel()).createFor()).get(ReelsViewModel.class);
        initVIew();
        return binding.getRoot();
    }

    private void callFragment(Fragment fragment) {
        if (getParentFragment() != null) {
            ((HomeFragment) getParentFragment()).openFragmet(fragment);
        }
    }

    private void initVIew() {

        binding.tvLive.setOnClickListener(v -> callFragment(new LiveFragmentMain()));
        binding.tvVideo.setOnClickListener(v -> callFragment(new VideoListFragment()));


        animation = AnimationUtils.loadAnimation(binding.getRoot().getContext(), R.anim.slow_rotate);


        new PagerSnapHelper().attachToRecyclerView(binding.rvReels);

        Log.d(TAG, "initVIew: ll " + lastPosition);



    }

    private void openFramgnet(BottomSheetDialogFragment bottomSheetDialogFragment) {
        bottomSheetDialogFragment.show(getChildFragmentManager(), bottomSheetDialogFragment.getClass().getSimpleName());
    }



    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if (playbackState == Player.STATE_BUFFERING) {
            binding.pd.setVisibility(View.VISIBLE);
        } else if (playbackState == Player.STATE_READY) {
            binding.pd.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        if (player != null) {
            player.setPlayWhenReady(true);
        }
        super.onResume();
    }

    @Override
    public void onStop() {
        if (player != null) {
            player.setPlayWhenReady(false);
        }
        super.onStop();
    }

    @Override
    public void onPause() {
        if (player != null) {
            player.setPlayWhenReady(false);
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (player != null) {
            player.setPlayWhenReady(false);
            player.stop();
            player.release();
        }
        super.onDestroy();
    }
}