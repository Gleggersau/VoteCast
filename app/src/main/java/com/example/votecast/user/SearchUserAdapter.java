package com.example.votecast.user;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.votecast.R;
import com.example.votecast.databinding.ItemSearchUsersBinding;
import com.example.votecast.models.User;
import com.example.votecast.retrofit.Const;
import com.example.votecast.user.guestuser.GuestActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SearchUserAdapter extends RecyclerView.Adapter<SearchUserAdapter.SearchUserViewHolder> {

    private Context context;
    private List<User> users = new ArrayList<>();

    @Override
    public SearchUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new SearchUserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_users, parent, false));
    }

    @Override
    public void onBindViewHolder(SearchUserViewHolder holder, int position) {
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

    public void clear() {
        users.clear();
        notifyDataSetChanged();
    }

    public class SearchUserViewHolder extends RecyclerView.ViewHolder {
        ItemSearchUsersBinding binding;

        public SearchUserViewHolder(View itemView) {
            super(itemView);
            binding = ItemSearchUsersBinding.bind(itemView);
        }

        public void setData(int position) {
            User user = users.get(position);
            Glide.with(itemView).load(user.getImage()).circleCrop().into(binding.imguser);
            binding.tvusername.setText(user.getName());
            binding.tvBio.setText(user.getBio());
            binding.getRoot().setOnClickListener(v -> context.startActivity(new Intent(context, GuestActivity.class).putExtra(Const.USER_STR, new Gson().toJson(user))));
        }
    }
}
