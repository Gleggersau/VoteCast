package com.example.votecast.livestreamming;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.votecast.R;
import com.example.votecast.databinding.ItemViewUsersBinding;
import com.example.votecast.models.User;
import com.example.votecast.z_demo.Demo_contents;

import java.util.List;

public class LiveViewUserAdapter extends RecyclerView.Adapter<LiveViewUserAdapter.ChatUserViewHolder> {


    private List<User> viewers = Demo_contents.getUsers(true);

    @Override
    public ChatUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChatUserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_users, parent, false));
    }

    OnLiveUserAdapterClickLisnter onLiveUserAdapterClickLisnter;

    public OnLiveUserAdapterClickLisnter getOnLiveUserAdapterClickLisnter() {
        return onLiveUserAdapterClickLisnter;
    }

    public void setOnLiveUserAdapterClickLisnter(OnLiveUserAdapterClickLisnter onLiveUserAdapterClickLisnter) {
        this.onLiveUserAdapterClickLisnter = onLiveUserAdapterClickLisnter;
    }

    @Override
    public void onBindViewHolder(LiveViewUserAdapter.ChatUserViewHolder holder, int position) {
        holder.setData(position);

    }

    @Override
    public int getItemCount() {
        return viewers.size();
    }

    public interface OnLiveUserAdapterClickLisnter {
        void onUserClick(User user);
    }

    public class ChatUserViewHolder extends RecyclerView.ViewHolder {
        ItemViewUsersBinding binding;

        public ChatUserViewHolder(View itemView) {
            super(itemView);
            binding = ItemViewUsersBinding.bind(itemView);
        }

        public void setData(int position) {
            User user = viewers.get(position);
            Glide.with(itemView).load(user.getImage()).circleCrop().into(binding.imgview);
            binding.getRoot().setOnClickListener(v -> onLiveUserAdapterClickLisnter.onUserClick(user));
        }
    }
}
