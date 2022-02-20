package com.example.votecast.chat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.votecast.R;
import com.example.votecast.SessionManager;
import com.example.votecast.activity.BaseActivity;
import com.example.votecast.databinding.ActivityChatBinding;
import com.example.votecast.models.Chat;
import com.example.votecast.models.ChatUser;
import com.example.votecast.retrofit.Const;
import com.example.votecast.user.guestuser.GuestActivity;
import com.example.votecast.videocall.CallRequestActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends BaseActivity {
    ActivityChatBinding binding;
    ChatAdapter chatAdapter = new ChatAdapter();
    SessionManager sessionManager;
    private ChatUser chatUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat);
        sessionManager = new SessionManager(this);
        Intent intent = getIntent();
        String userStr = intent.getStringExtra(Const.USER_STR);
        if (userStr!=null && !userStr.isEmpty()) {
            chatUser = new Gson().fromJson(userStr, ChatUser.class);
            chatAdapter.initGuestUser(chatUser.getUser());
            chatAdapter.initLocalUser(sessionManager.getUser());
            initView();
        }
    }

    private void initView() {
        Glide.with(this).load(chatUser.getUser().getImage()).circleCrop().into(binding.imgUser);
        binding.tvUserNamew.setText(chatUser.getUser().getName());
        binding.rvChat.setAdapter(chatAdapter);


        chatAdapter.addData(getDemoChat());
    }

    private List<Chat> getDemoChat() {
        List<Chat> chats = new ArrayList<>();

        chats.add(new Chat("Hii", Chat.TEXT, Chat.USER1));
        chats.add(new Chat("Hello", Chat.TEXT, Chat.ME));
        chats.add(new Chat("I am " + chatUser.getUser().getName(), Chat.TEXT, Chat.USER1));
        chats.add(new Chat(sessionManager.getUser().getName() + " here", Chat.TEXT, Chat.ME));
        chats.add(new Chat(chatUser.getUser().getImage(), Chat.PHOTO, Chat.USER1));
        chats.add(new Chat("Ohh You Are Looking Nice 💕💖", Chat.TEXT, Chat.ME));
        if (chatUser.getUser().getGender().equalsIgnoreCase("male")) {
            chats.add(new Chat("https://images.unsplash.com/photo-1481437642641-2f0ae875f836?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1170&q=80", Chat.PHOTO, Chat.ME));
        } else {
            chats.add(new Chat("https://images.unsplash.com/uploads/14110635637836178f553/dcc2ccd9?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1170&q=80", Chat.PHOTO, Chat.ME));
        }
        chats.add(new Chat("You are so sweet 😘😘", Chat.TEXT, Chat.USER1));
        chats.add(new Chat(chatUser.getMessage(), Chat.TEXT, Chat.USER1));

        return chats;
    }

    public void onClickVideoCall(View view) {
        startActivity(new Intent(this, CallRequestActivity.class));
    }

    public void onClickUser(View view) {
        startActivity(new Intent(this, GuestActivity.class).putExtra(Const.USER_STR, new Gson().toJson(chatUser)));

    }

    public void onClickCamara(View view) {
        Log.d("TAG", "onClickCamara: ");
    }
}