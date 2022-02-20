package com.example.votecast.comments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.votecast.R;
import com.example.votecast.databinding.ItemCommentBinding;
import com.example.votecast.models.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Comment> comments = new ArrayList<>();


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentViewHOlder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false));


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((CommentViewHOlder) holder).setData(position);

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public void addData(List<Comment> comments) {

        this.comments.addAll(comments);
        notifyItemRangeInserted(this.comments.size(), comments.size());
    }

    public void addSingleComment(Comment comment) {
        this.comments.add(comment);
        notifyItemInserted(comments.size() - 1);
    }

    public class CommentViewHOlder extends RecyclerView.ViewHolder {
        ItemCommentBinding binding;

        public CommentViewHOlder(@NonNull View itemView) {
            super(itemView);
            binding = ItemCommentBinding.bind(itemView);

        }

        public void setData(int position) {
            Comment comment = comments.get(position);
            Glide.with(binding.getRoot()).load(comment.getUser().getImage()).circleCrop().into(binding.imgUser);
            binding.tvComment.setText(comment.getComment());
            binding.tvDate.setText(comment.getDate());
            binding.tvUserName.setText(comment.getUser().getName());
        }
    }
}
