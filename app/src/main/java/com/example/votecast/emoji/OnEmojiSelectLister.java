package com.example.votecast.emoji;

import com.example.votecast.databinding.ItemEmojiGridBinding;
import com.example.votecast.models.GiftRoot;

public interface OnEmojiSelectLister {
    void onEmojiSelect(ItemEmojiGridBinding binding, GiftRoot giftRoot);
}
