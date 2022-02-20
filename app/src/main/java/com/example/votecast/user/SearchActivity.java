package com.example.votecast.user;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.databinding.DataBindingUtil;

import com.example.votecast.R;
import com.example.votecast.activity.BaseActivity;
import com.example.votecast.databinding.ActivitySearchBinding;
import com.example.votecast.models.User;
import com.example.votecast.z_demo.Demo_contents;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity {
    ActivitySearchBinding binding;
    SearchUserAdapter searchUserAdapter = new SearchUserAdapter();
    private List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);


        binding.rvMessage.setAdapter(searchUserAdapter);

        users = Demo_contents.getUsers(true);

        searchUserAdapter.addData(users);

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("TAG", "beforeTextChanged: ");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty()) {
                    searchUser(s.toString());
                } else {
                    searchUserAdapter.clear();
                    searchUserAdapter.addData(users);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("TAG", "afterTextChanged: ");
            }
        });
    }

    private void searchUser(String toString) {
        searchUserAdapter.clear();
        List<User> finelUser = new ArrayList<>();
        for (User u : users
        ) {
            if (u.getName().toLowerCase().contains(toString.toLowerCase())) {
                finelUser.add(u);
            }

        }
        searchUserAdapter.addData(finelUser);
    }
}