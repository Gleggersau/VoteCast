package com.example.votecast.posts;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.databinding.DataBindingUtil;

import com.example.votecast.R;
import com.example.votecast.activity.BaseActivity;
import com.example.votecast.databinding.ActivityLocationChooseBinding;
import com.example.votecast.retrofit.Const;
import com.example.votecast.z_demo.Demo_contents;

import java.util.ArrayList;
import java.util.List;

public class LocationChooseActivity extends BaseActivity {
    ActivityLocationChooseBinding binding;
    LocationAdapter locationAdapter = new LocationAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_location_choose);
        Intent intent = getIntent();
        String location = intent.getStringExtra(Const.DATA);
        if (location!=null && !location.isEmpty()) {
            //   binding.etLocation.setText(location);
        }

        binding.btnDone.setOnClickListener(v -> {
            Intent i = getIntent();
            i.putExtra(Const.DATA, binding.etLocation.getText().toString().trim());
            setResult(RESULT_OK, i);
            finish();
        });
        binding.rvLocation.setAdapter(locationAdapter);

        locationAdapter.addData(Demo_contents.getLocations());
        locationAdapter.setOnLocationClickLisnter(hashtag1 -> {
            Intent i = getIntent();

            i.putExtra(Const.DATA, hashtag1);
            setResult(RESULT_OK, i);
            finish();
        });

        binding.etLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                locationAdapter.clear();
                if (s.toString().isEmpty()) {
                    locationAdapter.addData(Demo_contents.getLocations());
                }
                List<String> searchedHashtag = new ArrayList<>();
                for (String h : Demo_contents.getLocations()) {
                    if (h.toLowerCase().contains(s.toString().toLowerCase())) {
                        searchedHashtag.add(h);
                    }

                }
                locationAdapter.addData(searchedHashtag);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}