package com.example.votecast.chat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.votecast.R;
import com.example.votecast.SessionManager;
import com.example.votecast.databinding.ItemChatusersBinding;
import com.example.votecast.models.ChatUser;
import com.example.votecast.retrofit.Const;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ChatUserAdapter extends RecyclerView.Adapter<ChatUserAdapter.ChatUserViewHolder> {

    private Context context;
    private List<ChatUser> chatUsers = new ArrayList<>();

    @Override
    public ChatUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ChatUserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chatusers, parent, false));
    }

    @Override
    public void onBindViewHolder(ChatUserAdapter.ChatUserViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return chatUsers.size();
    }

    public void addData(List<ChatUser> chatUsers) {

        this.chatUsers.addAll(chatUsers);
        notifyItemRangeInserted(this.chatUsers.size(), chatUsers.size());
    }

    public class ChatUserViewHolder extends RecyclerView.ViewHolder {
        ItemChatusersBinding binding;

        public ChatUserViewHolder(View itemView) {
            super(itemView);
            binding = ItemChatusersBinding.bind(itemView);
        }

        public void setData(int position) {
            ChatUser chatUser = chatUsers.get(position);
            Glide.with(itemView).load(chatUser.getUser().getImage()).circleCrop().into(binding.imguser);
            binding.tvusername.setText(chatUser.getUser().getName());
            if (chatUser.getMessage().isEmpty()) {
                chatUser.setMessage("Hello " + new SessionManager(context).getUser().getName());
            }
            binding.tvlastchet.setText(chatUser.getMessage());
            binding.tvtime.setText(chatUser.getTime());
            binding.tvcountry.setText(chatUser.getUser().getCountry());
            binding.getRoot().setOnClickListener(v -> context.startActivity(new Intent(context, ChatActivity.class).putExtra(Const.USER_STR, new Gson().toJson(chatUser))));
        }
    }
}
