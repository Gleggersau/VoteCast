package com.example.votecast.posts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.votecast.R;
import com.example.votecast.databinding.ItemHashtagsBinding;

import java.util.ArrayList;
import java.util.List;


public class HashtagsAdapter extends RecyclerView.Adapter<HashtagsAdapter.HashtagViewHolder> {
     Context context;
    OnHashtagsClickLisnter onHashtagsClickLisnter;
    private List<String> hashtags = new ArrayList<>();

    public OnHashtagsClickLisnter getOnHashtagsClickLisnter() {
        return onHashtagsClickLisnter;
    }

    public void setOnHashtagsClickLisnter(OnHashtagsClickLisnter onHashtagsClickLisnter) {
        this.onHashtagsClickLisnter = onHashtagsClickLisnter;
    }

    @NonNull
    @Override
    public HashtagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new HashtagViewHolder(LayoutInflater.from(context).inflate(R.layout.item_hashtags, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HashtagViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return hashtags.size();
    }

    public void addData(List<String> hashtags) {
        this.hashtags.addAll(hashtags);
        notifyItemRangeInserted(this.hashtags.size(), hashtags.size());
    }

    public void clear() {
        this.hashtags.clear();
        notifyDataSetChanged();
    }

    public interface OnHashtagsClickLisnter {
        void onHashtagClick(String hashtag);
    }

    public class HashtagViewHolder extends RecyclerView.ViewHolder {
        ItemHashtagsBinding binding;

        public HashtagViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemHashtagsBinding.bind(itemView);

        }

        public void setData(int position) {
            binding.tvHashtag.setText(hashtags.get(position));
            binding.getRoot().setOnClickListener(v -> onHashtagsClickLisnter.onHashtagClick(hashtags.get(position)));
        }
    }
}
