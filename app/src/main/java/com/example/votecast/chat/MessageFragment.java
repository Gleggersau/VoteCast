package com.example.votecast.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.votecast.R;
import com.example.votecast.databinding.FragmentMessageBinding;
import com.example.votecast.z_demo.Demo_contents;


public class MessageFragment extends Fragment {
    FragmentMessageBinding binding;

    public MessageFragment() {
        // Required empty public constructor
    }

    ChatUserAdapter chatUserAdapter = new ChatUserAdapter();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_message, container, false);
        initView();

        return binding.getRoot();
    }

    private void initView() {

        binding.rvMessage.setAdapter(chatUserAdapter);
        chatUserAdapter.addData(Demo_contents.getChatUsers());
    }
}