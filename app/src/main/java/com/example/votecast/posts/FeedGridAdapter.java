package com.example.votecast.posts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.votecast.R;
import com.example.votecast.databinding.ItemFeedGridListBinding;
import com.example.votecast.models.Post;

import java.util.ArrayList;
import java.util.List;


public class FeedGridAdapter extends RecyclerView.Adapter<FeedGridAdapter.FeedViewHolder> {

    private Context context;
    OnFeedGridAdapterClickLisnter onFeedGridAdapterClickLisnter;
    private List<Post> posts = new ArrayList<>();

    public OnFeedGridAdapterClickLisnter getOnFeedGridAdapterClickLisnter() {
        return onFeedGridAdapterClickLisnter;
    }

    public void setOnFeedGridAdapterClickLisnter(OnFeedGridAdapterClickLisnter onFeedGridAdapterClickLisnter) {
        this.onFeedGridAdapterClickLisnter = onFeedGridAdapterClickLisnter;
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new FeedViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_grid_list, parent, false));

    }

    @Override
    public void onBindViewHolder(FeedGridAdapter.FeedViewHolder holder, int position) {


        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void addData(List<Post> posts) {

        this.posts.addAll(posts);
        notifyItemRangeInserted(this.posts.size(), posts.size());
    }

    public List<Post> getList() {
        return posts;
    }

    public interface OnFeedGridAdapterClickLisnter {
        void onFeedClick(int position);
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder {
        ItemFeedGridListBinding binding;

        public FeedViewHolder(View itemView) {
            super(itemView);
            binding = ItemFeedGridListBinding.bind(itemView);
        }

        public void setData(int position) {
            Post post = posts.get(position);

            Glide.with(context).load(post.getImage()).centerCrop().into(binding.imagepost);
            binding.imagepost.setAdjustViewBounds(true);

            binding.imagepost.setOnClickListener(v -> onFeedGridAdapterClickLisnter.onFeedClick(position));
        }
    }
}
