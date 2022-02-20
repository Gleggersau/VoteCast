package com.example.votecast.user;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.votecast.R;
import com.example.votecast.databinding.ItemFollowrsBinding;
import com.example.votecast.models.User;
import com.example.votecast.retrofit.Const;
import com.example.votecast.user.guestuser.GuestActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class FollowrsUsersAdapter extends RecyclerView.Adapter<FollowrsUsersAdapter.FollowrsUserViewHolder> {

    private Context context;
    private List<User> users = new ArrayList<>();

    @Override
    public FollowrsUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new FollowrsUserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_followrs, parent, false));
    }

    @Override
    public void onBindViewHolder(FollowrsUserViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void addData(List<User> users) {

        this.users.addAll(users);
        notifyItemRangeInserted(this.users.size(), users.size());
    }

    public class FollowrsUserViewHolder extends RecyclerView.ViewHolder {
        ItemFollowrsBinding binding;

        public FollowrsUserViewHolder(View itemView) {
            super(itemView);
            binding = ItemFollowrsBinding.bind(itemView);
        }

        public void setData(int position) {


            User user = users.get(position);
            Glide.with(itemView).load(user.getImage()).circleCrop().into(binding.imguser);
            binding.tvusername.setText(user.getName());
            binding.tvBio.setText(user.getBio());
            binding.tvcountry.setText(user.getCountry());
            binding.getRoot().setOnClickListener(v -> context.startActivity(new Intent(context, GuestActivity.class).putExtra(Const.USER_STR, new Gson().toJson(user))));
        }
    }
}
