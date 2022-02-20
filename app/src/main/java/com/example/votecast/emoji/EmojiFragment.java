package com.example.votecast.emoji;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.votecast.R;
import com.example.votecast.databinding.FragmentEmojiBinding;
import com.example.votecast.models.GiftRoot;

import java.util.ArrayList;
import java.util.List;


public class EmojiFragment extends Fragment {


    FragmentEmojiBinding binding;


    EmojiGridAdapter emojiGridAdapter = new EmojiGridAdapter();
    private OnEmojiSelectLister onEmojiSelectLister;
    private List<GiftRoot> giftRoot = new ArrayList<>();

    public EmojiFragment(List<GiftRoot> giftRoot) {
        // Required empty public constructor
        this.giftRoot = giftRoot;
    }

    public OnEmojiSelectLister getOnEmojiSelectLister() {
        return onEmojiSelectLister;
    }

    public void setOnEmojiSelectLister(OnEmojiSelectLister onEmojiSelectLister) {
        this.onEmojiSelectLister = onEmojiSelectLister;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_emoji, container, false);
        initMain();
        return binding.getRoot();
    }

    private void initMain() {

        emojiGridAdapter.addData(giftRoot);
        binding.rvEmoji.setAdapter(emojiGridAdapter);
        emojiGridAdapter.setOnEmojiSelectLister((binding1, giftRoot1) -> onEmojiSelectLister.onEmojiSelect(binding1, giftRoot1));

    }
}