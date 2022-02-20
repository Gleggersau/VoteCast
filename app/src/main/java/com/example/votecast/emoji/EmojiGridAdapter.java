package com.example.votecast.emoji;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.votecast.R;
import com.example.votecast.databinding.ItemEmojiGridBinding;
import com.example.votecast.models.GiftRoot;

import java.util.ArrayList;
import java.util.List;

public class EmojiGridAdapter extends RecyclerView.Adapter<EmojiGridAdapter.EmojiViewHolder> {

     Context context;
    OnEmojiSelectLister onEmojiSelectLister;
    List<GiftRoot> giftRoots = new ArrayList<>();
    public OnEmojiSelectLister getOnEmojiSelectLister() {
        return onEmojiSelectLister;
    }

    public void setOnEmojiSelectLister(OnEmojiSelectLister onEmojiSelectLister) {
        this.onEmojiSelectLister = onEmojiSelectLister;
    }

    @Override
    public EmojiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new EmojiViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_emoji_grid, parent, false));
    }

    @Override
    public void onBindViewHolder(EmojiViewHolder holder, int position) {

        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return giftRoots.size();
    }

    public void addData(List<GiftRoot> giftRoot) {
        this.giftRoots.addAll(giftRoot);
        notifyItemRangeInserted(this.giftRoots.size(), giftRoot.size());
    }

    public class EmojiViewHolder extends RecyclerView.ViewHolder {
        ItemEmojiGridBinding binding;

        public EmojiViewHolder(View itemView) {
            super(itemView);
            binding = ItemEmojiGridBinding.bind(itemView);
        }

        public void setData(int position) {
            Glide.with(binding.getRoot()).load(giftRoots.get(position).getUrl()).into(binding.imgEmoji);
            binding.tvCoin.setText(String.valueOf(giftRoots.get(position).getId()));
            binding.getRoot().setOnClickListener(v -> onEmojiSelectLister.onEmojiSelect(binding, giftRoots.get(position)));
        }
    }
}
