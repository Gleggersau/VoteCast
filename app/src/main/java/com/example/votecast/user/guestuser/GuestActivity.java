package com.example.votecast.user.guestuser;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.votecast.R;
import com.example.votecast.activity.BaseActivity;
import com.example.votecast.databinding.ActivityGuestBinding;
import com.example.votecast.models.User;
import com.example.votecast.posts.FeedGridActivity;
import com.example.votecast.reels.VideoListGridActivity;
import com.example.votecast.retrofit.Const;
import com.example.votecast.user.FollowrsListActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

public class GuestActivity extends BaseActivity {
    ActivityGuestBinding binding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_guest);

        Intent intent = getIntent();
        String userStr = intent.getStringExtra(Const.USER_STR);
        if (userStr!=null && !userStr.isEmpty()) {
            user = new Gson().fromJson(userStr, User.class);

            initView();
            initListner();
        }


    }

    private void initListner() {
        binding.lytFollowrs.setOnClickListener(v -> startActivity(new Intent(this, FollowrsListActivity.class).putExtra(Const.DATA, new Gson().toJson(user))));
        binding.lytMyVideos2.setOnClickListener(v -> startActivity(new Intent(this, VideoListGridActivity.class).putExtra(Const.DATA, new Gson().toJson(user))));
        binding.lytMyPost2.setOnClickListener(v -> startActivity(new Intent(this, FeedGridActivity.class).putExtra(Const.DATA, new Gson().toJson(user))));


        binding.pdFollow.setVisibility(View.GONE);
        binding.lytFollowUnfollow.setOnClickListener(v -> {
            binding.lytFollowUnfollow.setEnabled(false);
            binding.pdFollow.setVisibility(View.VISIBLE);
            binding.tvFollowStatus.setVisibility(View.INVISIBLE);

            new Handler(Looper.myLooper()).postDelayed(() -> {
                if (binding.tvFollowStatus.getText().toString().equalsIgnoreCase("follow")) {
                    binding.tvFollowStatus.setText("UNFOLLOW");
                    binding.lytFollowUnfollow.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.graylight));

                } else {
                    binding.lytFollowUnfollow.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.pink));
                    binding.tvFollowStatus.setText("FOLLOW");
                }
                binding.pdFollow.setVisibility(View.GONE);
                binding.tvFollowStatus.setVisibility(View.VISIBLE);
                binding.lytFollowUnfollow.setEnabled(true);

            }, 1000);
        });


    }

    private void initView() {
        Glide.with(this).load(user.getImage()).circleCrop().into(binding.imgUser);
        binding.tvName.setText(user.getName());
        binding.tvAge.setText(String.valueOf(user.getAge()));
        binding.tvBio.setText(user.getBio());
        binding.tvCountry.setText(user.getCountry());
        binding.tvFollowrs.setText(String.valueOf(user.getFollowres()));
        binding.tvLevel.setText("Lv. " + user.getLevel());
        binding.tvPosts.setText(String.valueOf(user.getPosts()));
        binding.tvVideos.setText(String.valueOf(user.getVideos()));
        binding.tvUserId.setText("ID: " + user.getId());

        if (user.getGender().equalsIgnoreCase("male")) {
            binding.imgGender.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.male));
        } else {
            binding.imgGender.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.female));
        }
        initTabLayout();
    }

    private void initTabLayout() {
        binding.viewPager.setAdapter(new GuestUserProfileViewPagerAdapter(getSupportFragmentManager(), user));
        binding.tablayout1.setupWithViewPager(binding.viewPager);
        binding.tablayout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //ll

                View v = tab.getCustomView();
                if (v!=null) {
                    TextView tv = (TextView) v.findViewById(R.id.tvTab);
                    tv.setTextColor(ContextCompat.getColor(GuestActivity.this, R.color.white));

                    View indicator = (View) v.findViewById(R.id.indicator);
                    indicator.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //ll
                View v = tab.getCustomView();
                if (v!=null) {
                    TextView tv = (TextView) v.findViewById(R.id.tvTab);
                    tv.setTextColor(ContextCompat.getColor(GuestActivity.this, R.color.graylight));

                    View indicator = (View) v.findViewById(R.id.indicator);
                    indicator.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
//ll
            }
        });
        settab(new String[]{"Posts", "Videos"});
    }

    private void settab(String[] contry) {
        binding.tablayout1.setTabGravity(TabLayout.GRAVITY_FILL);
        binding.tablayout1.removeAllTabs();
        for (int i = 0; i < contry.length; i++) {
            binding.tablayout1.addTab(binding.tablayout1.newTab().setCustomView(createCustomView(i, contry[i])));
        }

    }

    private View createCustomView(int i, String s) {

        View v = LayoutInflater.from(GuestActivity.this).inflate(R.layout.custom_tabhorizontol2, null);
        TextView tv = (TextView) v.findViewById(R.id.tvTab);
        tv.setText(s);
        tv.setTextColor(ContextCompat.getColor(GuestActivity.this, R.color.white));
        View indicator = (View) v.findViewById(R.id.indicator);
        if (i==0) {
            indicator.setVisibility(View.VISIBLE);
        } else {
            indicator.setVisibility(View.GONE);
        }
        return v;

    }
}