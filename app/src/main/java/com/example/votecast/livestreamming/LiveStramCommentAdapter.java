package com.example.votecast.livestreamming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.votecast.R;
import com.example.votecast.databinding.ItemLivestramCommentBinding;
import com.example.votecast.models.LiveStramComment;
import com.example.votecast.models.User;

import java.util.ArrayList;
import java.util.List;

public class LiveStramCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW1 = 1;
    private static final int VIEW2 = 2;
    List<LiveStramComment> comments = new ArrayList<>();
    private Context context;

    @Override
    public int getItemViewType(int position) {
        if (position==0) return VIEW1;
        return VIEW2;
    }

    OnCommentClickListner onCommentClickListner;

    public OnCommentClickListner getOnCommentClickListner() {
        return onCommentClickListner;
    }

    public void setOnCommentClickListner(OnCommentClickListner onCommentClickListner) {
        this.onCommentClickListner = onCommentClickListner;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        if (viewType == VIEW1) {
            return new NoticeViewHOlder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_livestream_comment_1, parent, false));
        }
        return new CommentViewHOlder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_livestram_comment, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof CommentViewHOlder) {
            ((CommentViewHOlder) holder).setCommentData(position);
        }
    }


    @Override
    public int getItemCount() {
        return comments.size();
    }

    public void addSingleComment(LiveStramComment liveStramComment) {
        this.comments.add(liveStramComment);
        notifyItemInserted(this.comments.size());
    }

    public class NoticeViewHOlder extends RecyclerView.ViewHolder {

        public NoticeViewHOlder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface OnCommentClickListner {
        void onClickCommet(User user);
    }

    public class CommentViewHOlder extends RecyclerView.ViewHolder {
        ItemLivestramCommentBinding binding;

        public CommentViewHOlder(@NonNull View itemView) {
            super(itemView);
            binding = ItemLivestramCommentBinding.bind(itemView);

        }

        public void setCommentData(int position) {
            LiveStramComment comment = comments.get(position);

            setUserLevel(comment.getUser().getLevel(), binding.buttomLevel);
            if (comment.isJoined()) {
                binding.tvComment.setText(comment.getUser().getName());
                binding.tvJoined.setVisibility(View.VISIBLE);
            } else {
                binding.tvJoined.setVisibility(View.GONE);
                binding.tvComment.setText(comment.getComment());
            }
            Glide.with(itemView).load(comment.getUser().getImage()).circleCrop().into(binding.imgUser);
            binding.getRoot().setOnClickListener(v -> onCommentClickListner.onClickCommet(comment.getUser()));
        }

        private void setUserLevel(int level, ImageView buttomLevel) {
            if (level==5) {
                buttomLevel.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.lv5));
            } else if (level==4) {
                buttomLevel.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.lv4));
            } else if (level==3) {
                buttomLevel.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.lv3));
            } else if (level==2) {
                buttomLevel.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.lv2));
            } else {
                buttomLevel.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.lv1));
            }
        }
    }
}
