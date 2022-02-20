package com.example.votecast.user.guestuser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.votecast.R;
import com.example.votecast.databinding.FragmentGuestUserReelsBinding;
import com.example.votecast.models.Reels;
import com.example.votecast.models.User;
import com.example.votecast.reels.ProfileVideoGridAdapter;
import com.example.votecast.z_demo.Demo_contents;

import java.util.ArrayList;
import java.util.List;


public class GuestUserReelsFragment extends Fragment {

    FragmentGuestUserReelsBinding binding;
    ProfileVideoGridAdapter profileVideoGridAdapter = new ProfileVideoGridAdapter();
    private User user;

    public GuestUserReelsFragment(User user) {
        // Required empty public constructor
        this.user = user;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_guest_user_reels, container, false);

        initMain();
        return binding.getRoot();
    }

    private void initMain() {

        List<Reels> list = new ArrayList<>();

        for (int i = 0; i < user.getPosts(); i++) {
            list.add(Demo_contents.getReels().get(0));
        }
        profileVideoGridAdapter.addData(list);
        binding.rvFeed.setAdapter(profileVideoGridAdapter);

    }
}