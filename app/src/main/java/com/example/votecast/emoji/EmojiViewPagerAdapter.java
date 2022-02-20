package com.example.votecast.emoji;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.votecast.models.GiftCategory;

import java.util.List;

public class EmojiViewPagerAdapter extends FragmentPagerAdapter {


    private List<GiftCategory> giftCategories;

    public EmojiViewPagerAdapter(FragmentManager fm, List<GiftCategory> giftCategories) {
        super(fm);
        this.giftCategories = giftCategories;
    }

    private OnEmojiSelectLister onEmojiSelectLister;

    public OnEmojiSelectLister getOnEmojiSelectLister() {
        return onEmojiSelectLister;
    }

    public void setOnEmojiSelectLister(OnEmojiSelectLister onEmojiSelectLister) {
        this.onEmojiSelectLister = onEmojiSelectLister;
    }

    @Override
    public Fragment getItem(int position) {
        EmojiFragment emojiFragment = new EmojiFragment(giftCategories.get(position).getGiftRoot());
        emojiFragment.setOnEmojiSelectLister((binding, giftRoot) -> onEmojiSelectLister.onEmojiSelect(binding, giftRoot));
        return emojiFragment;
    }

    @Override
    public int getCount() {
        return giftCategories.size();
    }
}
