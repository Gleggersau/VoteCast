package com.example.votecast.posts;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.votecast.R;
import com.example.votecast.databinding.ItemFeedBinding;
import com.example.votecast.models.Post;
import com.example.votecast.retrofit.Const;
import com.example.votecast.user.guestuser.GuestActivity;
import com.google.gson.Gson;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;
import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {

    private Context context;
    private List<Post> posts = new ArrayList<>();

    OnPostClickListner onPostClickListner;

    public OnPostClickListner getOnPostClickListner() {
        return onPostClickListner;
    }

    public void setOnPostClickListner(OnPostClickListner onPostClickListner) {
        this.onPostClickListner = onPostClickListner;
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new FeedViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed, parent, false));
    }

    @Override
    public void onBindViewHolder(FeedAdapter.FeedViewHolder holder, int position) {
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

    public interface OnPostClickListner {
        void onLikeClick(Post post, ItemFeedBinding binding);

        void onCommentClick(Post post);

        void onShareClick(Post post);
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder {
        ItemFeedBinding binding;

        public FeedViewHolder(View itemView) {
            super(itemView);
            binding = ItemFeedBinding.bind(itemView);
        }

        public void setData(int position) {
            Post post = posts.get(position);

            Glide.with(context).load(post.getImage()).into(binding.imagepost);
            binding.imagepost.setAdjustViewBounds(true);
            binding.tvCaption.setText(post.getCaption());
            binding.tvComments.setText(String.valueOf(post.getCommentCount()));
            binding.tvLikes.setText(String.valueOf(post.getLikeCount()));
            binding.tvusername.setText(post.getUser().getName());
            binding.tvtime.setText(post.getTime());
            binding.rvLocation.setText(post.getLication());
            binding.lytComments.setOnClickListener(v -> onPostClickListner.onCommentClick(post));
            binding.btnShare.setOnClickListener(v -> onPostClickListner.onShareClick(post));
            binding.likeButton.setOnLikeListener(new OnLikeListener() {
                @Override
                public void liked(LikeButton likeButton) {
                    int like = Integer.parseInt(binding.tvLikes.getText().toString()) + 1;
                    binding.tvLikes.setText(String.valueOf(like));
                    onPostClickListner.onLikeClick(post, binding);
                }

                @Override
                public void unLiked(LikeButton likeButton) {
                    int like = Integer.parseInt(binding.tvLikes.getText().toString()) - 1;
                    binding.tvLikes.setText(String.valueOf(like));
                    onPostClickListner.onLikeClick(post, binding);
                }
            });


        }
    }
}
