package com.example.votecast.chat;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.votecast.R;
import com.example.votecast.databinding.ItemChatImageBinding;
import com.example.votecast.databinding.ItemChatStikerBinding;
import com.example.votecast.databinding.ItemChatTextBinding;
import com.example.votecast.models.Chat;
import com.example.votecast.models.User;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TEXT_TYPE = 3;
    private static final int PHOTO_TYPE = 1;

    private Context context;
    private List<Chat> chatList = new ArrayList<>();
    private User guestUser;
    private User user;

    @Override
    public int getItemViewType(int position) {
        Log.d("TAG", "getItemViewType: " + chatList.get(position).getType());
        return chatList.get(position).getType();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        if (viewType==TEXT_TYPE) {
            return new ChatTextViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_text, parent, false));
        } else if (viewType==PHOTO_TYPE) {
            return new ChatImageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_image, parent, false));
        } else {
            return new ChatStikerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_stiker, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==TEXT_TYPE) {
            ((ChatTextViewHolder) holder).setData(position);
        } else if (getItemViewType(position)==PHOTO_TYPE) {
            ((ChatImageViewHolder) holder).setData(position);
        } else {
            ((ChatStikerViewHolder) holder).setData(position);
        }
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public void addData(List<Chat> chatList) {

        this.chatList.addAll(chatList);
        notifyItemRangeInserted(this.chatList.size(), chatList.size());
    }

    public void initGuestUser(User guestUser) {

        this.guestUser = guestUser;
    }

    public void initLocalUser(User user) {

        this.user = user;
    }

    public class ChatTextViewHolder extends RecyclerView.ViewHolder {
        ItemChatTextBinding binding;

        public ChatTextViewHolder(View itemView) {
            super(itemView);
            binding = ItemChatTextBinding.bind(itemView);
        }

        public void setData(int position) {
            Chat chat = chatList.get(position);
            Glide.with(itemView).load(guestUser.getImage()).circleCrop().into(binding.imgUser1);
            Glide.with(itemView).load(user.getImage()).circleCrop().into(binding.imgUser2);

            if (chat.getSENDER()==Chat.ME) {
                binding.imgUser1.setVisibility(View.INVISIBLE);
                binding.imgUser2.setVisibility(View.VISIBLE);
                binding.space2.setVisibility(View.GONE);
                binding.space1.setVisibility(View.VISIBLE);
                binding.tvText.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_chat_right));
                binding.tvText.setTextColor(ContextCompat.getColor(context, R.color.white));
                binding.tvText.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.pink));
            } else {
                binding.imgUser2.setVisibility(View.INVISIBLE);
                binding.imgUser1.setVisibility(View.VISIBLE);
                binding.space1.setVisibility(View.GONE);
                binding.space2.setVisibility(View.VISIBLE);
                binding.tvText.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_chat_left));
            }
            binding.tvText.setText(chat.getData());
        }
    }

    public class ChatImageViewHolder extends RecyclerView.ViewHolder {
        ItemChatImageBinding binding;

        public ChatImageViewHolder(View itemView) {
            super(itemView);
            binding = ItemChatImageBinding.bind(itemView);
        }

        public void setData(int position) {
            Chat chat = chatList.get(position);
            Glide.with(itemView).load(guestUser.getImage()).circleCrop().into(binding.imgUser1);
            Glide.with(itemView).load(user.getImage()).circleCrop().into(binding.imgUser2);
            Glide.with(itemView).load(chat.getData()).into(binding.mainImage);


            if (chat.getSENDER()==Chat.ME) {
                binding.imgUser1.setVisibility(View.INVISIBLE);
                binding.imgUser2.setVisibility(View.VISIBLE);
                binding.space2.setVisibility(View.GONE);
                binding.space1.setVisibility(View.VISIBLE);
                binding.mainImage.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_chat_right));
                binding.lytMain.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.pink));
            } else {
                binding.imgUser2.setVisibility(View.INVISIBLE);
                binding.imgUser1.setVisibility(View.VISIBLE);
                binding.space1.setVisibility(View.GONE);
                binding.space2.setVisibility(View.VISIBLE);
                binding.mainImage.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_chat_left));
            }

        }
    }

    public class ChatStikerViewHolder extends RecyclerView.ViewHolder {
        ItemChatStikerBinding binding;

        public ChatStikerViewHolder(View itemView) {
            super(itemView);
            binding = ItemChatStikerBinding.bind(itemView);
        }

        public void setData(int position) {
            Chat chat = chatList.get(position);
            Glide.with(itemView).load(guestUser.getImage()).circleCrop().into(binding.imgUser1);
            Glide.with(itemView).load(user.getImage()).circleCrop().into(binding.imgUser2);
            Glide.with(itemView).load(chat.getData()).into(binding.tvImage);


            if (chat.getSENDER()==Chat.ME) {
                binding.imgUser1.setVisibility(View.INVISIBLE);
                binding.imgUser2.setVisibility(View.VISIBLE);
                binding.space2.setVisibility(View.GONE);
                binding.space1.setVisibility(View.VISIBLE);
                binding.tvImage.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_chat_right));
                binding.tvImage.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.pink));

            } else {
                binding.imgUser2.setVisibility(View.INVISIBLE);
                binding.imgUser1.setVisibility(View.VISIBLE);
                binding.space1.setVisibility(View.GONE);
                binding.space2.setVisibility(View.VISIBLE);
                binding.tvImage.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_chat_left));
            }
        }
    }
}
